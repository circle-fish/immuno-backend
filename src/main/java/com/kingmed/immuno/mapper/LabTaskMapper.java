package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.LabTask;
import org.apache.ibatis.annotations.*;

/**
 * ;(lab_task)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface LabTaskMapper  extends BaseMapper<LabTask> {
    /**
     * 分页查询指定行数据
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<LabTask> selectByPage(IPage<LabTask> page, @Param(Constants.WRAPPER) Wrapper<LabTask> wrapper);

    @Select("select * from lab_task where id = #{id}")
    LabTask selectById(@Param("id") Integer id);

    @Delete("DELETE FROM lab_task WHERE id = #{id}")
    Integer deleteByid(@Param("id") Integer id);

    @Delete("DELETE FROM lab_task")
    Integer deleteAllTasks();
}