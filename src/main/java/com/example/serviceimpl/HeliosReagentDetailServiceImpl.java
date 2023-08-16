package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.HeliosReagentDetail;
import com.example.mapper.HeliosReagentDetailMapper;
import com.example.service.HeliosReagentDetailService;
 /**
 * ;(helios_reagent_detail)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class HeliosReagentDetailServiceImpl implements HeliosReagentDetailService{
    @Autowired
    private HeliosReagentDetailMapper heliosReagentDetailMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public HeliosReagentDetail queryById(Long id){
        return heliosReagentDetailMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param heliosReagentDetail 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<HeliosReagentDetail> paginQuery(HeliosReagentDetail heliosReagentDetail, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<HeliosReagentDetail> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(heliosReagentDetail.getName())){
            queryWrapper.eq(HeliosReagentDetail::getName, heliosReagentDetail.getName());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getBatchNo())){
            queryWrapper.eq(HeliosReagentDetail::getBatchNo, heliosReagentDetail.getBatchNo());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getBizOrgCode())){
            queryWrapper.eq(HeliosReagentDetail::getBizOrgCode, heliosReagentDetail.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getCreatedBy())){
            queryWrapper.eq(HeliosReagentDetail::getCreatedBy, heliosReagentDetail.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getUpdatedBy())){
            queryWrapper.eq(HeliosReagentDetail::getUpdatedBy, heliosReagentDetail.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<HeliosReagentDetail> pagin = new Page<>(current , size , true);
        IPage<HeliosReagentDetail> selectResult = heliosReagentDetailMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    public HeliosReagentDetail insert(HeliosReagentDetail heliosReagentDetail){
        heliosReagentDetailMapper.insert(heliosReagentDetail);
        return heliosReagentDetail;
    }
    
    /** 
     * 更新数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    public HeliosReagentDetail update(HeliosReagentDetail heliosReagentDetail){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<HeliosReagentDetail> chainWrapper = new LambdaUpdateChainWrapper<HeliosReagentDetail>(heliosReagentDetailMapper);
        if(StrUtil.isNotBlank(heliosReagentDetail.getName())){
            chainWrapper.eq(HeliosReagentDetail::getName, heliosReagentDetail.getName());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getBatchNo())){
            chainWrapper.eq(HeliosReagentDetail::getBatchNo, heliosReagentDetail.getBatchNo());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getBizOrgCode())){
            chainWrapper.eq(HeliosReagentDetail::getBizOrgCode, heliosReagentDetail.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getCreatedBy())){
            chainWrapper.eq(HeliosReagentDetail::getCreatedBy, heliosReagentDetail.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosReagentDetail.getUpdatedBy())){
            chainWrapper.eq(HeliosReagentDetail::getUpdatedBy, heliosReagentDetail.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(HeliosReagentDetail::getId, heliosReagentDetail.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(heliosReagentDetail.getId());
        }else{
            return heliosReagentDetail;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = heliosReagentDetailMapper.deleteById(id);
        return total > 0;
    }
}