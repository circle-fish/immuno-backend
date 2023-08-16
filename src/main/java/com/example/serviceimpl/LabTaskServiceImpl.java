package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.LabTask;
import com.example.mapper.LabTaskMapper;
import com.example.service.LabTaskService;
 /**
 * ;(lab_task)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabTaskServiceImpl implements LabTaskService{
    @Autowired
    private LabTaskMapper labTaskMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabTask queryById(Long id){
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
}