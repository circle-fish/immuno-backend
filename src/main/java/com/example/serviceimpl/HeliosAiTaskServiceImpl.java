package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.HeliosAiTask;
import com.example.mapper.HeliosAiTaskMapper;
import com.example.service.HeliosAiTaskService;
 /**
 * ;(helios_ai_task)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class HeliosAiTaskServiceImpl implements HeliosAiTaskService{
    @Autowired
    private HeliosAiTaskMapper heliosAiTaskMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public HeliosAiTask queryById(Long id){
        return heliosAiTaskMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param heliosAiTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<HeliosAiTask> paginQuery(HeliosAiTask heliosAiTask, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<HeliosAiTask> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(heliosAiTask.getCreatedBy())){
            queryWrapper.eq(HeliosAiTask::getCreatedBy, heliosAiTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getUpdatedBy())){
            queryWrapper.eq(HeliosAiTask::getUpdatedBy, heliosAiTask.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getBizOrgCode())){
            queryWrapper.eq(HeliosAiTask::getBizOrgCode, heliosAiTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getFileId())){
            queryWrapper.eq(HeliosAiTask::getFileId, heliosAiTask.getFileId());
        }
        //2. 执行分页查询
        Page<HeliosAiTask> pagin = new Page<>(current , size , true);
        IPage<HeliosAiTask> selectResult = heliosAiTaskMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    public HeliosAiTask insert(HeliosAiTask heliosAiTask){
        heliosAiTaskMapper.insert(heliosAiTask);
        return heliosAiTask;
    }
    
    /** 
     * 更新数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    public HeliosAiTask update(HeliosAiTask heliosAiTask){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<HeliosAiTask> chainWrapper = new LambdaUpdateChainWrapper<HeliosAiTask>(heliosAiTaskMapper);
        if(StrUtil.isNotBlank(heliosAiTask.getCreatedBy())){
            chainWrapper.eq(HeliosAiTask::getCreatedBy, heliosAiTask.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getUpdatedBy())){
            chainWrapper.eq(HeliosAiTask::getUpdatedBy, heliosAiTask.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getBizOrgCode())){
            chainWrapper.eq(HeliosAiTask::getBizOrgCode, heliosAiTask.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosAiTask.getFileId())){
            chainWrapper.eq(HeliosAiTask::getFileId, heliosAiTask.getFileId());
        }
        //2. 设置主键，并更新
        chainWrapper.set(HeliosAiTask::getId, heliosAiTask.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(heliosAiTask.getId());
        }else{
            return heliosAiTask;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = heliosAiTaskMapper.deleteById(id);
        return total > 0;
    }
}