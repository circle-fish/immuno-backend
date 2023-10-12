package com.kingmed.immuno.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.mapper.HeliosReagentMapper;
import com.kingmed.immuno.service.HeliosReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 /**
 * ;(helios_reagent)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-9-22
 */
@Service
public class HeliosReagentServiceImpl implements HeliosReagentService{
    @Autowired
    private HeliosReagentMapper heliosReagentMapper;
    
    @Autowired
    private MapperHelpper mapperHelpper;
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public HeliosReagent queryById(Integer id){
        return heliosReagentMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param heliosReagent 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<HeliosReagent> paginQuery(HeliosReagent heliosReagent, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<HeliosReagent> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(heliosReagent.getName())){
            queryWrapper.eq(HeliosReagent::getName, heliosReagent.getName());
        }
        if(StrUtil.isNotBlank(heliosReagent.getBatchNo())){
            queryWrapper.eq(HeliosReagent::getBatchNo, heliosReagent.getBatchNo());
        }
        if(StrUtil.isNotBlank(heliosReagent.getBizOrgCode())){
            queryWrapper.eq(HeliosReagent::getBizOrgCode, heliosReagent.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosReagent.getCreatedBy())){
            queryWrapper.eq(HeliosReagent::getCreatedBy, heliosReagent.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosReagent.getUpdatedBy())){
            queryWrapper.eq(HeliosReagent::getUpdatedBy, heliosReagent.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<HeliosReagent> pagin = new Page<>(current , size , true);
        IPage<HeliosReagent> selectResult = heliosReagentMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    public HeliosReagent insert(HeliosReagent heliosReagent){
        heliosReagentMapper.insert(heliosReagent);
        return heliosReagent;
    }
    
    /** 
     * 更新数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    public HeliosReagent update(HeliosReagent heliosReagent){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<HeliosReagent> chainWrapper = new LambdaUpdateChainWrapper<HeliosReagent>(heliosReagentMapper);
        if(StrUtil.isNotBlank(heliosReagent.getName())){
            chainWrapper.eq(HeliosReagent::getName, heliosReagent.getName());
        }
        if(StrUtil.isNotBlank(heliosReagent.getBatchNo())){
            chainWrapper.eq(HeliosReagent::getBatchNo, heliosReagent.getBatchNo());
        }
        if(StrUtil.isNotBlank(heliosReagent.getBizOrgCode())){
            chainWrapper.eq(HeliosReagent::getBizOrgCode, heliosReagent.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosReagent.getCreatedBy())){
            chainWrapper.eq(HeliosReagent::getCreatedBy, heliosReagent.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosReagent.getUpdatedBy())){
            chainWrapper.eq(HeliosReagent::getUpdatedBy, heliosReagent.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(HeliosReagent::getId, heliosReagent.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(heliosReagent.getId());
        }else{
            return heliosReagent;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        int total = heliosReagentMapper.deleteById(id);
        return total > 0;
    }

     /**
      * 更新或插入数据
      *
      * @param heliosReagent 实例对象
      * @return 试剂对象
      */
     @Override
     public HeliosReagent upsert(HeliosReagent heliosReagent) {
         int res = mapperHelpper.upsert(heliosReagent,heliosReagentMapper);
         if(res > 0) {
             return heliosReagent;
         }else{
             throw new ServiceException("LabTask 的upsert失败！ id: "+ heliosReagent.getId());
         }
     }
 }