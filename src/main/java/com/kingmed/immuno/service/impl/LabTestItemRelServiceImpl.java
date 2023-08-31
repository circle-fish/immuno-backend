package com.kingmed.immuno.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.LabTestItemRel;
import com.kingmed.immuno.mapper.LabTestItemRelMapper;
import com.kingmed.immuno.service.LabTestItemRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 /**
 * ;(lab_test_item_rel)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class LabTestItemRelServiceImpl implements LabTestItemRelService{
    @Autowired
    private LabTestItemRelMapper labTestItemRelMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public LabTestItemRel queryById(Integer id){
        return labTestItemRelMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param labTestItemRel 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<LabTestItemRel> paginQuery(LabTestItemRel labTestItemRel, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<LabTestItemRel> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(labTestItemRel.getBizOrgCode())){
            queryWrapper.eq(LabTestItemRel::getBizOrgCode, labTestItemRel.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTestItemRel.getKmcsTestItemCode())){
            queryWrapper.eq(LabTestItemRel::getKmcsTestItemCode, labTestItemRel.getKmcsTestItemCode());
        }
        if(StrUtil.isNotBlank(labTestItemRel.getKmcsTestItemName())){
            queryWrapper.eq(LabTestItemRel::getKmcsTestItemName, labTestItemRel.getKmcsTestItemName());
        }
        //2. 执行分页查询
        Page<LabTestItemRel> pagin = new Page<>(current , size , true);
        IPage<LabTestItemRel> selectResult = labTestItemRelMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param labTestItemRel 实例对象
     * @return 实例对象
     */
    public LabTestItemRel insert(LabTestItemRel labTestItemRel){
        labTestItemRelMapper.insert(labTestItemRel);
        return labTestItemRel;
    }
    
    /** 
     * 更新数据
     *
     * @param labTestItemRel 实例对象
     * @return 实例对象
     */
    public LabTestItemRel update(LabTestItemRel labTestItemRel){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<LabTestItemRel> chainWrapper = new LambdaUpdateChainWrapper<LabTestItemRel>(labTestItemRelMapper);
        if(StrUtil.isNotBlank(labTestItemRel.getBizOrgCode())){
            chainWrapper.eq(LabTestItemRel::getBizOrgCode, labTestItemRel.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(labTestItemRel.getKmcsTestItemCode())){
            chainWrapper.eq(LabTestItemRel::getKmcsTestItemCode, labTestItemRel.getKmcsTestItemCode());
        }
        if(StrUtil.isNotBlank(labTestItemRel.getKmcsTestItemName())){
            chainWrapper.eq(LabTestItemRel::getKmcsTestItemName, labTestItemRel.getKmcsTestItemName());
        }
        //2. 设置主键，并更新
        chainWrapper.set(LabTestItemRel::getId, labTestItemRel.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(labTestItemRel.getId());
        }else{
            return labTestItemRel;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = labTestItemRelMapper.deleteById(id);
        return total > 0;
    }
}