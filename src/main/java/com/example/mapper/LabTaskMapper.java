package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.entity.LabTask;
import org.apache.ibatis.annotations.*;
import com.example.entity.LabTask;

/**
 * ;(lab_task)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface LabTaskMapper  extends BaseMapper<LabTask>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<LabTask> selectByPage(IPage<LabTask> page , @Param(Constants.WRAPPER) Wrapper<LabTask> wrapper);

    @Select("select * from lab_task where id = #{id}")
    LabTask selectById(@Param("id") Integer id);

    @Delete("DELETE FROM lab_task WHERE id = #{id}")
    Integer deleteByid(@Param("id") Integer id);

    @Update("  UPDATE lab_task\n" +
            "        SET id = #{id},  app_id = #{appId},barcode = #{barcode}, experiment_no = #{experimentNo}\n" +
            "        ,test_item_code = #{testItemCode},test_item_name = #{testItemName},biz_org_code = #{bizOrgCode}\n" +
            "        WHERE id = #{id}")
    Integer updateLabTask(LabTask labTask);

    @Insert("INSERT INTO lab_task (barcode, experiment_no, status, biz_org_code, " +
            "task_type, result_type, result_id, result, ai_result, lab_test_item_id," +
            "lab_test_item_name, lab_test_item_type, lab_order_id, device_type, device_id, " +
            "device_name, device_position, reagent_type, reagent_detail_id, reagent_lot, version, " +
            "created_time, updated_time, created_by, updated_by) " +
            "VALUES (#{barcode}, #{experimentNo}, #{status}, #{bizOrgCode}, #{taskType}, " +
            "#{resultType}, #{resultId}, #{result}, #{aiResult}, #{labTestItemId}, #{labTestItemName}, " +
            "#{labTestItemType}, #{labOrderId}, #{deviceType}, #{deviceId}, #{deviceName}, " +
            "#{devicePosition}, #{reagentType}, #{reagentDetailId}, #{reagentLot}, #{version}, " +
            "#{createdTime}, #{updatedTime}, #{createdBy}, #{updatedBy})")
    Integer insertLabTask(LabTask labTask);
}