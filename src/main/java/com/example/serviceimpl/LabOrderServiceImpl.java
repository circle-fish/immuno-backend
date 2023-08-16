package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.LabOrder;
import com.example.mapper.LabOrderMapper;
import com.example.service.LabOrderService;
 /**
 * ;(lab_order)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabOrderServiceImpl implements LabOrderService{
    @Autowired
    private LabOrderMapper labOrderMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabOrder queryById(Long id){
        return labOrderMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param labOrder 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<LabOrder> paginQuery(LabOrder labOrder, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<LabOrder> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(labOrder.getWet())){
            queryWrapper.eq(LabOrder::getWet, labOrder.getWet());
        }
        if(StrUtil.isNotBlank(labOrder.getTemperature())){
            queryWrapper.eq(LabOrder::getTemperature, labOrder.getTemperature());
        }
        if(StrUtil.isNotBlank(labOrder.getBizOrgCode())){
            queryWrapper.eq(LabOrder::getBizOrgCode, labOrder.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labOrder.getCreatedBy())){
            queryWrapper.eq(LabOrder::getCreatedBy, labOrder.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labOrder.getUpdatedBy())){
            queryWrapper.eq(LabOrder::getUpdatedBy, labOrder.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<LabOrder> pagin = new Page<>(current , size , true);
        IPage<LabOrder> selectResult = labOrderMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    public LabOrder insert(LabOrder labOrder){
        labOrderMapper.insert(labOrder);
        return labOrder;
    }
    
    /** 
     * 更新数据
     *
     * @param labOrder 实例对象
     * @return 实例对象
     */
    public LabOrder update(LabOrder labOrder){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<LabOrder> chainWrapper = new LambdaUpdateChainWrapper<LabOrder>(labOrderMapper);
        if(StrUtil.isNotBlank(labOrder.getWet())){
            chainWrapper.eq(LabOrder::getWet, labOrder.getWet());
        }
        if(StrUtil.isNotBlank(labOrder.getTemperature())){
            chainWrapper.eq(LabOrder::getTemperature, labOrder.getTemperature());
        }
        if(StrUtil.isNotBlank(labOrder.getBizOrgCode())){
            chainWrapper.eq(LabOrder::getBizOrgCode, labOrder.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labOrder.getCreatedBy())){
            chainWrapper.eq(LabOrder::getCreatedBy, labOrder.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labOrder.getUpdatedBy())){
            chainWrapper.eq(LabOrder::getUpdatedBy, labOrder.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(LabOrder::getId, labOrder.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(labOrder.getId());
        }else{
            return labOrder;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = labOrderMapper.deleteById(id);
        return total > 0;
    }
}