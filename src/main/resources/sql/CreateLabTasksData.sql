# ##构造异常值 :
# 1 . 多对多 / 一对多 / 多对一 T
# 2 . 不存在情况 则 返回 404
# 3 . 多个bigorzcode / 多个appID T
# 4 . LOOSE / STRICT 的限制


CREATE TABLE tmp_table(
                          `task_id` CHAR(36) NOT NULL   COMMENT 'KmcsTask的TaskID' ,
                          `status` INT    COMMENT '状态:      * 0 —— 初始化，待操作      * 1 —— 已转换为LabTask      * 2 —— 已保存      * 3 —— 已提交' ,
                          `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
                          `app_id` CHAR(36)    COMMENT '申请单Id' ,
                          `barcode` VARCHAR(32)    COMMENT '条码号' ,
                          `experiment_no` VARCHAR(32)    COMMENT '实验号' ,
                          `test_item_code` VARCHAR(16)    COMMENT '项目代码' ,
                          `test_item_name` VARCHAR(128)    COMMENT '项目名称' ,
                          `lab_task_id` BIGINT    COMMENT '与该对象绑定的LabTask的Id' ,
                          `version` INT    COMMENT '' ,
                          `created_time` DATETIME    COMMENT '' ,
                          `updated_time` DATETIME    COMMENT '' ,
                          `created_by` VARCHAR(32)    COMMENT '' ,
                          `updated_by` VARCHAR(32)    COMMENT '' ,
                          PRIMARY KEY (task_id)
)  COMMENT = '';


    INSERT INTO tmp_table
    (SELECT *
    FROM kmcs_task
    WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');

    SELECT * FROm tmp_table;

        drop procedure if exists loopTest;          #如果存在test存储过程则删除
        DELIMITER $$
        create procedure loopTest()                 #创建无参存储过程,名称为loopTest
            BEGIN
                SET @i  = 1;
#                 SET @bizOrgCode  = (SELECT biz_org_code from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788' );
#                 SET @appId  = (SELECT app_id from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');
                SET @barcode  = (SELECT barcode from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');
                SET @experimentNo  = (SELECT experiment_no from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');
#                 SET @testItemCode  = (SELECT test_item_code from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');
#                 SET @testItemName  = (SELECT test_item_name from tmp_table WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788');
                SET @labTestItemCode  = '@labTestItemCode';
                SET @labTestItemName  = '@labTestItemName';
                SET @conversionMode  = 'LOOSE';
                SET @sortedIndex  = 1;
                SET @deviceType  = 0;
                SET @reagentType  = 0;

                ## 注意下面next_loop的结果要在commit之后才会显示
                ## 但是对 kmcs_task 的修改立马显示，逻辑没问题先commit
                ## 涵盖多biz_org_code 情况 —— 每个big_org_code 下多个 appId 情况

                SET @n = 1 ;

                next_loop: LOOP
                    INSERT INTO lab_test_item (name,code, conversion_mode, biz_org_code,
                                               sorted_index, device_type, reagent_type,
                                               version,created_time,updated_time,created_by,updated_by)
                    VALUES (CONCAT(@labTestItemName,@n), CONCAT(@labTestItemCode,@n),@conversionMode,
                            CONCAT('-testing@bizOrgCode',@n), @sortedIndex, @deviceType, @reagentType,
                            1,NOW(),NOW(),'admin','admin');

                    INSERT INTO lab_test_item_rel(biz_org_code, kmcs_test_item_code, kmcs_test_item_name,
                                                  lab_test_item_code)
                    VALUES (CONCAT('-testing@bizOrgCode',@n),CONCAT('-@KmCode-',@n),CONCAT('-testingItemName',@n),CONCAT(@labTestItemCode,@n));

#                     4 . LOOSE / STRICT 的限制
                    if @n = 2 then
                        UPDATE lab_test_item SET conversion_mode = 'STRICT' where conversion_mode = 'LOOSE';
                    end if;
                    SET @n = @n + 1 ;
                    if @n > 3 then                    #结束循环的条件: 当i大于3时跳出loop循环
                        leave next_loop;
                    end if;

                END LOOP next_loop;

                my_loop: LOOP
                    INSERT INTO kmcs_task (task_id, status, biz_org_code, app_id,
                                   barcode, experiment_no, test_item_code,
                                   test_item_name, lab_task_id,version,created_time,updated_time,created_by,updated_by)
                    VALUES (CONCAT('testing@taskId--',@i), 0, CONCAT('-testing@bizOrgCode',@i%3),
                    CONCAT('testing@appid-biz-',@i%3), @barcode,
                    @experimentNo, CONCAT('-@KmCode-',@i%3),
                    CONCAT('-testingItemName',@i%3),
                    0,1,NOW(),NOW(),'admin','admin');
                    #初始化labTaskId和通用字段

                    # 3 . 多个bigorzcode / 多个appID T 不同子公司 appid 唯一
                    UPDATE kmcs_task SET app_id = CONCAT('testing@appid-biz-',@i%3,'-',(@i+1)%3) where biz_org_code =  CONCAT('-testing@bizOrgCode',@i%3) limit 5;

                    SET @i = @i + 1 ;
                    if @i > 100 then                    #结束循环的条件: 当i大于80时跳出loop循环
                        leave my_loop;
                    end if;

                END LOOP my_loop;
#                 select * from kmcs_task ORDER BY task_id DESC LIMIT 10  ;
            END
            $$

START TRANSACTION ;

    SAVEPOINT p2;

#     删除任务数据数据
    DELETE From kmcs_task where task_id like '%testing%' ;
    DELETE FROM lab_test_item where name like '@labTestItemName%' ;
    DELETE from  lab_test_item_rel where id > 5 ;
    DELETE FROM  lab_task where 1 = 1;


#     删除批次设备试剂等数据
    DELETE From lab_order where biz_org_code = '-testing@bizOrgCode1' ;
#   注每次创建labOrder都要绑定当天同批次的LabTask不然会报出异常-查询为空
    DELETE FROM device where biz_org_code  = '-testing@bizOrgCode1' ;
    DELETE FROM helios_reagent where biz_org_code  = '-testing@bizOrgCode1' ;

#     批量生成任务数据loopTest
    call loopTest();

    SELECT * FROM lab_task;

    rollback to p2;

commit;
SET @autocommit = 0;
# 生成数据注意 !!!:
## 不同子公司 appid 唯一 其他子公司不会有一样的appId
## barcode 条形码下面的实验号experimentNo全部相同
