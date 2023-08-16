package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.KmcsTask;
import com.example.mapper.KmcsTaskMapper;
import com.example.service.KmcsTaskService;
 /**
 * ;(kmcs_task)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class KmcsTaskServiceImpl implements KmcsTaskService{
    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param taskId 主键
     * @return 实例对象
     */
    public KmcsTask queryById(String taskId){
        return kmcsTaskMapper.selectById(taskId);
    }
    
    /**
     * 分页查询
     *
     * @param kmcsTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<KmcsTask> paginQuery(KmcsTask kmcsTask, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<KmcsTask> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(kmcsTask.getAppId())){
            queryWrapper.eq(KmcsTask::getAppId, kmcsTask.getAppId());
        }
        if(StrUtil.isNotBlank(kmcsTask.getBarcode())){
            queryWrapper.eq(KmcsTask::getBarcode, kmcsTask.getBarcode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getExperimentNo())){
            queryWrapper.eq(KmcsTask::getExperimentNo, kmcsTask.getExperimentNo());
        }
        if(StrUtil.isNotBlank(kmcsTask.getTestItemCode())){
            queryWrapper.eq(KmcsTask::getTestItemCode, kmcsTask.getTestItemCode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getTestItemName())){
            queryWrapper.eq(KmcsTask::getTestItemName, kmcsTask.getTestItemName());
        }
        if(StrUtil.isNotBlank(kmcsTask.getBizOrgCode())){
            queryWrapper.eq(KmcsTask::getBizOrgCode, kmcsTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getCreatedBy())){
            queryWrapper.eq(KmcsTask::getCreatedBy, kmcsTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(kmcsTask.getUpdatedBy())){
            queryWrapper.eq(KmcsTask::getUpdatedBy, kmcsTask.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<KmcsTask> pagin = new Page<>(current , size , true);
        IPage<KmcsTask> selectResult = kmcsTaskMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    public KmcsTask insert(KmcsTask kmcsTask){
        kmcsTaskMapper.insert(kmcsTask);
        return kmcsTask;
    }
    
    /** 
     * 更新数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    public KmcsTask update(KmcsTask kmcsTask){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<KmcsTask> chainWrapper = new LambdaUpdateChainWrapper<KmcsTask>(kmcsTaskMapper);
        if(StrUtil.isNotBlank(kmcsTask.getAppId())){
            chainWrapper.eq(KmcsTask::getAppId, kmcsTask.getAppId());
        }
        if(StrUtil.isNotBlank(kmcsTask.getBarcode())){
            chainWrapper.eq(KmcsTask::getBarcode, kmcsTask.getBarcode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getExperimentNo())){
            chainWrapper.eq(KmcsTask::getExperimentNo, kmcsTask.getExperimentNo());
        }
        if(StrUtil.isNotBlank(kmcsTask.getTestItemCode())){
            chainWrapper.eq(KmcsTask::getTestItemCode, kmcsTask.getTestItemCode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getTestItemName())){
            chainWrapper.eq(KmcsTask::getTestItemName, kmcsTask.getTestItemName());
        }
        if(StrUtil.isNotBlank(kmcsTask.getBizOrgCode())){
            chainWrapper.eq(KmcsTask::getBizOrgCode, kmcsTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(kmcsTask.getCreatedBy())){
            chainWrapper.eq(KmcsTask::getCreatedBy, kmcsTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(kmcsTask.getUpdatedBy())){
            chainWrapper.eq(KmcsTask::getUpdatedBy, kmcsTask.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(KmcsTask::getTaskId, kmcsTask.getTaskId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(kmcsTask.getTaskId());
        }else{
            return kmcsTask;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    public boolean deleteById(String taskId){
        int total = kmcsTaskMapper.deleteById(taskId);
        return total > 0;
    }
}