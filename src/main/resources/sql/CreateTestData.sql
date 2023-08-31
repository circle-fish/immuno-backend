##构造异常值 :
1 . 多对多 / 一对多 / 多对一
2 . 不存在情况
3 . 多个bigorzcode / 多个appID

DELIMITER $$
CREATE PROCEDURE while_loop()
BEGIN
    SELECT *
    INTO #Temp
    FROM kmcs_task
    WHERE task_id = 'ef45d651-91bf-49fb-b8ea-f94700ad8788';
    DECLARE i INT = 1;

    DECLARE bizOrgCode VARCHAR = (SELECT biz_org_code from #Temp);
    DECLARE appId VARCHAR = (SELECT app_id from #Temp);
    DECLARE barcode VARCHAR = (SELECT barcode from #Temp);
    DECLARE experimentNo VARCHAR = (SELECT experiment_no from #Temp);
    DECLARE testItemCode VARCHAR = (SELECT test_item_code from #Temp);
    DECLARE testItemName VARCHAR = (SELECT test_item_name from #Temp);

    SAVEPOINT p1;
    WHILE i < 100 DO
        INSERT INTO kmcs_task (task_id, status, biz_org_code, app_id,
                       barcode, experiment_no, test_item_code,
                       test_item_name, lab_task_id)
        VALUES (CONCAT('testing',i), 0, CONCAT(bizOrgCode,'-testing',i%3),
        CONCAT(appId,'-testing',i%3), barcode,
        experimentNo, CONCAT('-testing',i%3),
        CONCAT('-testing',i%3),
        0)

        INSERT INTO lab_task (id, status, biz_org_code, barcode, experiment_no,
        task_type, result, ai_result, lab_test_item_id, lab_test_item_name, lab_order_id, 
        device_type, device_id, device_name, device_position, reagent_type, reagent_lot) VALUES 
        (id, status, bizOrgCode, barcode, experimentNo, taskType, result,
        aiResult, labTestItemId, labTestItemName, labOrderId, deviceType, 
        deviceId, deviceName, devicePosition, reagentType, reagentLot)

        SET i = i + 1 ;

    END WHILE ;

    SET i = 1;


    rollback to p1;


    
END $$





