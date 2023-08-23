package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;
import com.example.entity.KmcsTask;

import java.util.List;

/**
 * ;(kmcs_task)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface KmcsTaskMapper  extends BaseMapper<KmcsTask>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<KmcsTask> selectByPage(IPage<KmcsTask> page , @Param(Constants.WRAPPER) Wrapper<KmcsTask> wrapper);

    @Select("select * from kmcs_task where biz_org_code = #{bizOrgCode} AND status = 0")
    List<KmcsTask> selectKmcsTaskByBizOrgCode(String bizOrgCode);

     @Select("select * from kmcs_task where task_id = #{taskId}")
     KmcsTask selectById(@Param("taskId") String taskId);

     @Delete("DELETE FROM kmcs_task WHERE task_id = #{taskId}")
     Integer deleteByTaskId(@Param("taskId") String taskId);



     @Update("  UPDATE kmcs_task\n" +
             "        SET task_id = #{taskId},  app_id = #{appId},barcode = #{barcode}, experiment_no = #{experimentNo}\n" +
             "        ,test_item_code = #{testItemCode},test_item_name = #{testItemName},biz_org_code = #{bizOrgCode}\n" +
             "        WHERE task_id = #{taskId}"
     )
     Integer updateKmcsTask(KmcsTask kmcsTask);

     @Insert("INSERT INTO kmcs_task(task_id,app_id,barcode,experiment_no,test_item_code\n" +
             "        ,test_item_name,biz_org_code,lab_task_id,status,version,created_time,\n" +
             "        updated_time,created_by,updated_by )\n" +
             "        VALUES(#{taskId},\n" +
             "               #{appId},#{barcode},#{experimentNo},\n" +
             "               #{testItemCode},#{testItemName},#{bizOrgCode},#{labTaskId},#{status},#{version},#{createdTime}\n" +
             "               ,#{updatedTime},#{createdBy},#{updatedBy})")
     Integer insertKmcsTask(KmcsTask kmcsTask);

}