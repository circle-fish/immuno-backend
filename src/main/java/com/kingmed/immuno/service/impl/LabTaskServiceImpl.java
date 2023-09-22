package com.kingmed.immuno.service.impl;

import cn.hutool.core.lang.Tuple;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.model.dataModel.LabUser;
import com.kingmed.immuno.service.LabTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ;(lab_task)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabTaskServiceImpl implements LabTaskService{
    @Autowired
    private LabTaskMapper labTaskMapper;
    @Autowired
    private LabOrderServiceImpl labOrderService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabTask queryById(Integer id){
        return labTaskMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param labTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<LabTask> paginQuery(LabTask labTask, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<LabTask> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(labTask.getBarcode())){
            queryWrapper.eq(LabTask::getBarcode, labTask.getBarcode());
        }
        if(StrUtil.isNotBlank(labTask.getExperimentNo())){
            queryWrapper.eq(LabTask::getExperimentNo, labTask.getExperimentNo());
        }
        if(StrUtil.isNotBlank(labTask.getBizOrgCode())){
            queryWrapper.eq(LabTask::getBizOrgCode, labTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTask.getResult())){
            queryWrapper.eq(LabTask::getResult, labTask.getResult());
        }
        if(StrUtil.isNotBlank(labTask.getAiResult())){
            queryWrapper.eq(LabTask::getAiResult, labTask.getAiResult());
        }
        if(StrUtil.isNotBlank(labTask.getLabTestItemName())){
            queryWrapper.eq(LabTask::getLabTestItemName, labTask.getLabTestItemName());
        }
        if(StrUtil.isNotBlank(labTask.getDeviceName())){
            queryWrapper.eq(LabTask::getDeviceName, labTask.getDeviceName());
        }
        if(StrUtil.isNotBlank(labTask.getDevicePosition())){
            queryWrapper.eq(LabTask::getDevicePosition, labTask.getDevicePosition());
        }
        if(StrUtil.isNotBlank(labTask.getReagentLot())){
            queryWrapper.eq(LabTask::getReagentLot, labTask.getReagentLot());
        }
        if(StrUtil.isNotBlank(labTask.getCreatedBy())){
            queryWrapper.eq(LabTask::getCreatedBy, labTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labTask.getUpdatedBy())){
            queryWrapper.eq(LabTask::getUpdatedBy, labTask.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<LabTask> pagin = new Page<>(current , size , true);
        IPage<LabTask> selectResult = labTaskMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    public LabTask insert(LabTask labTask){
        labTaskMapper.insert(labTask);
        return labTask;
    }
    
    /** 
     * 更新数据
     *
     * @param labTask 实例对象
     * @return 实例对象
     */
    public LabTask update(LabTask labTask){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<LabTask> chainWrapper = new LambdaUpdateChainWrapper<LabTask>(labTaskMapper);
        if(StrUtil.isNotBlank(labTask.getBarcode())){
            chainWrapper.eq(LabTask::getBarcode, labTask.getBarcode());
        }
        if(StrUtil.isNotBlank(labTask.getExperimentNo())){
            chainWrapper.eq(LabTask::getExperimentNo, labTask.getExperimentNo());
        }
        if(StrUtil.isNotBlank(labTask.getBizOrgCode())){
            chainWrapper.eq(LabTask::getBizOrgCode, labTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTask.getResult())){
            chainWrapper.eq(LabTask::getResult, labTask.getResult());
        }
        if(StrUtil.isNotBlank(labTask.getAiResult())){
            chainWrapper.eq(LabTask::getAiResult, labTask.getAiResult());
        }
        if(StrUtil.isNotBlank(labTask.getLabTestItemName())){
            chainWrapper.eq(LabTask::getLabTestItemName, labTask.getLabTestItemName());
        }
        if(StrUtil.isNotBlank(labTask.getDeviceName())){
            chainWrapper.eq(LabTask::getDeviceName, labTask.getDeviceName());
        }
        if(StrUtil.isNotBlank(labTask.getDevicePosition())){
            chainWrapper.eq(LabTask::getDevicePosition, labTask.getDevicePosition());
        }
        if(StrUtil.isNotBlank(labTask.getReagentLot())){
            chainWrapper.eq(LabTask::getReagentLot, labTask.getReagentLot());
        }
        if(StrUtil.isNotBlank(labTask.getCreatedBy())){
            chainWrapper.eq(LabTask::getCreatedBy, labTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labTask.getUpdatedBy())){
            chainWrapper.eq(LabTask::getUpdatedBy, labTask.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(LabTask::getId, labTask.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(labTask.getId());
        }else{
            return labTask;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = labTaskMapper.deleteById(id);
        return total > 0;
    }

     /**
      * 为界面初始化和刷新提供数据
      * 返回任务状态为inited和unhandled的LabTasks
      *
      * @return 初始化和未处理的任务列表的元组
      */
     @Override
     public Tuple initTasksForInterFace() {
         QueryWrapper<LabTask> initQueryWrapper = new QueryWrapper<>();
         initQueryWrapper.eq("status", EnumManager.LabTaskStatus.inited.toString());
         QueryWrapper<LabTask> unhandledQueryWrapper = new QueryWrapper<>();
         unhandledQueryWrapper.eq("status",EnumManager.LabTaskStatus.unhandled.toString());

         List<LabTask> initLabTasks = labTaskMapper.selectList(initQueryWrapper);
         List<LabTask> unhanledLabTasks = labTaskMapper.selectList(unhandledQueryWrapper);

         return new Tuple(initLabTasks,unhanledLabTasks);
     }

    /**
     * 搁置任务，修改选中的“LabTask”的“status”为“UNHANDLED”
     * ，并进行刷新操作
     *
     * @param labTasks
     * @return 搁置后返回任务状态为inited和unhandled的LabTasks
     */
    @Override
    public Tuple layAsideLabTask(List<LabTask> labTasks) {
        for(LabTask labTask : labTasks){
            labTask.setStatus(EnumManager.LabTaskStatus.unhandled.toString());
            labTaskMapper.updateById(labTask);
        }
        return initTasksForInterFace();
    }

    /**
     * 修改选中的“LabTask”的“status”为“INITED”，并
     * 将OrderId修改成当天的OrderId，并进行刷新操作
     *
     * @param labTasks
     * @return 纳入后返回任务状态为inited和unhandled的LabTasks
     */
    @Override
    public Tuple bringIntoLabTask(List<LabTask> labTasks, LabUser labUser) {
        for(LabTask labTask : labTasks){
            labTask.setStatus(EnumManager.LabTaskStatus.inited.toString());
            labTask.setLabOrderId(labOrderService.checkTodayLabOrder(labUser).getId());
            labTaskMapper.updateById(labTask);
        }
        return initTasksForInterFace();
    }
}