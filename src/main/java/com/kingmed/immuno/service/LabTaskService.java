package com.kingmed.immuno.service;

import cn.hutool.core.lang.Tuple;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.LabUser;

import java.util.List;

/**
 * ;(lab_task)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface LabTaskService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    LabTask queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param labTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<LabTask> paginQuery(LabTask labTask, long current, long size);
    /** 
     * 新增数据
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    LabTask insert(LabTask labTask);
    /** 
     * 更新数据
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    LabTask update(LabTask labTask);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 为界面初始化和刷新提供数据
     * @return 返回任务状态为inited和unhandled的LabTasks
     */
    Tuple initTasksForInterFace();

    /**
     * 搁置任务，修改选中的“LabTask”的“status”为“UNHANDLED”
     * ，并进行刷新操作
     * @return 搁置后返回任务状态为inited和unhandled的LabTasks
     */
    Tuple layAsideLabTask(List<LabTask> labTasks);

    /**
     * 修改选中的“LabTask”的“status”为“INITED”，并
     * 将OrderId修改成当天的OrderId，并进行刷新操作
     * @param labTasks
     * @return 纳入后返回任务状态为inited和unhandled的LabTasks
     */
    Tuple bringIntoLabTask(List<LabTask> labTasks, LabUser labUser);

    LabTask upsert(LabTask labTask);
}