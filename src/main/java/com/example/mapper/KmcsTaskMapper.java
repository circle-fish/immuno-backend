package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.entity.KmcsTask;

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


     KmcsTask selectById(@Param("taskId") String taskId);

     Integer deleteByTaskId(@Param("taskId") String taskId);

     Integer updateKmcsTask(@Param("kmscTask") KmcsTask kmcsTask);

     Integer insertKmcsTask(@Param("kmscTask") KmcsTask kmcsTask);

}