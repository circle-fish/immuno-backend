package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.KmcsTask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


     @Select("select * from kmcs_task ")
     List<KmcsTask> selectAllKmcsTasks();

     @Select("select * from kmcs_task where " +
             "test_item_code = #{TestItemCode} " +
             "AND biz_org_code = #{bizOrgCode}" +
             "LIMIT 1 ")
     //？？？仅返回一个可否
    KmcsTask selectByTestItemCode(@Param("TestItemCode") String TestItemCode, @Param("bizOrgCode")String bizOrgCode);
    @Select("select * from kmcs_task where status = 1 ")
    List<KmcsTask>  selectConvertedTasks();

    @Select("SELECT DISTINCT biz_org_code FROM kmcs_task ")
    List<String> selectALlByBizOrgCode();
}