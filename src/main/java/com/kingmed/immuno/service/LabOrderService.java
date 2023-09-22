package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.LabOrder;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.LabUser;

import com.kingmed.immuno.model.dataModel.dto.LabOrderTaskDO;

import java.util.List;

/**
 * ;(lab_order)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */

public interface LabOrderService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    LabOrder queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param labOrder 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<LabOrder> paginQuery(LabOrder labOrder, long current, long size);
    /** 
     * 新增数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    LabOrder insert(LabOrder labOrder);
    /** 
     * 更新数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    LabOrder update(LabOrder labOrder);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * 获取到今天的lab_order,如果没有则创建一个
     * @param labUser
     * @return 今日任务批次
     */
    LabOrder checkTodayLabOrder(LabUser labUser);
    /**
     * 获取属于当前lab_order的所有的lab_task, 并把所有尚未处理的LabTask全部绑定到当前的lab_order
     *并返回被绑定后的LabTasks
     * @param labOrder
     * @param labUser
     * @return 绑定到批次的任务列表
     */
    List<LabTask> bindLabTaskToLabOrder(LabOrder labOrder, LabUser labUser);
    /**
     * 获取到今天的lab_order,如果没有则创建一个，并且根据创建情况对其进行LabTask进行绑定
     *
     * @param labUser
     * @return
     */
    LabOrderTaskDO getTodayLabOrder(LabUser labUser);
}