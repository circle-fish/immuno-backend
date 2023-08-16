package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.LabTestItem;
import com.example.mapper.LabTestItemMapper;
import com.example.service.LabTestItemService;
 /**
 * ;(lab_test_item)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabTestItemServiceImpl implements LabTestItemService{
    @Autowired
    private LabTestItemMapper labTestItemMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabTestItem queryById(Long id){
        return labTestItemMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param labTestItem 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<LabTestItem> paginQuery(LabTestItem labTestItem, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<LabTestItem> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(labTestItem.getName())){
            queryWrapper.eq(LabTestItem::getName, labTestItem.getName());
        }
        if(StrUtil.isNotBlank(labTestItem.getBizOrgCode())){
            queryWrapper.eq(LabTestItem::getBizOrgCode, labTestItem.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTestItem.getCreatedBy())){
            queryWrapper.eq(LabTestItem::getCreatedBy, labTestItem.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labTestItem.getUpdatedBy())){
            queryWrapper.eq(LabTestItem::getUpdatedBy, labTestItem.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<LabTestItem> pagin = new Page<>(current , size , true);
        IPage<LabTestItem> selectResult = labTestItemMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    public LabTestItem insert(LabTestItem labTestItem){
        labTestItemMapper.insert(labTestItem);
        return labTestItem;
    }
    
    /** 
     * 更新数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    public LabTestItem update(LabTestItem labTestItem){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<LabTestItem> chainWrapper = new LambdaUpdateChainWrapper<LabTestItem>(labTestItemMapper);
        if(StrUtil.isNotBlank(labTestItem.getName())){
            chainWrapper.eq(LabTestItem::getName, labTestItem.getName());
        }
        if(StrUtil.isNotBlank(labTestItem.getBizOrgCode())){
            chainWrapper.eq(LabTestItem::getBizOrgCode, labTestItem.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTestItem.getCreatedBy())){
            chainWrapper.eq(LabTestItem::getCreatedBy, labTestItem.getCreatedBy());
        }
        if(StrUtil.isNotBlank(labTestItem.getUpdatedBy())){
            chainWrapper.eq(LabTestItem::getUpdatedBy, labTestItem.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(LabTestItem::getId, labTestItem.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(labTestItem.getId());
        }else{
            return labTestItem;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = labTestItemMapper.deleteById(id);
        return total > 0;
    }
}