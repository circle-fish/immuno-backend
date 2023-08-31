package com.kingmed.immuno.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.HeliosImage;
import com.kingmed.immuno.mapper.HeliosImageMapper;
import com.kingmed.immuno.service.HeliosImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 /**
 * ;(helios_image)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class HeliosImageServiceImpl implements HeliosImageService{
    @Autowired
    private HeliosImageMapper heliosImageMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public HeliosImage queryById(Integer id){
        return heliosImageMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param heliosImage 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<HeliosImage> paginQuery(HeliosImage heliosImage, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<HeliosImage> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(heliosImage.getCreatedBy())){
            queryWrapper.eq(HeliosImage::getCreatedBy, heliosImage.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosImage.getUpdatedBy())){
            queryWrapper.eq(HeliosImage::getUpdatedBy, heliosImage.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(heliosImage.getBizOrgCode())){
            queryWrapper.eq(HeliosImage::getBizOrgCode, heliosImage.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosImage.getDeviceName())){
            queryWrapper.eq(HeliosImage::getDeviceName, heliosImage.getDeviceName());
        }
        if(StrUtil.isNotBlank(heliosImage.getBarcode())){
            queryWrapper.eq(HeliosImage::getBarcode, heliosImage.getBarcode());
        }
        if(StrUtil.isNotBlank(heliosImage.getExperimentNo())){
            queryWrapper.eq(HeliosImage::getExperimentNo, heliosImage.getExperimentNo());
        }
        if(StrUtil.isNotBlank(heliosImage.getLabTestItemName())){
            queryWrapper.eq(HeliosImage::getLabTestItemName, heliosImage.getLabTestItemName());
        }
        if(StrUtil.isNotBlank(heliosImage.getKmcsTaskId())){
            queryWrapper.eq(HeliosImage::getKmcsTaskId, heliosImage.getKmcsTaskId());
        }
        if(StrUtil.isNotBlank(heliosImage.getFileName())){
            queryWrapper.eq(HeliosImage::getFileName, heliosImage.getFileName());
        }
        if(StrUtil.isNotBlank(heliosImage.getAttPurpose())){
            queryWrapper.eq(HeliosImage::getAttPurpose, heliosImage.getAttPurpose());
        }
        if(StrUtil.isNotBlank(heliosImage.getAttachmentId())){
            queryWrapper.eq(HeliosImage::getAttachmentId, heliosImage.getAttachmentId());
        }
        if(StrUtil.isNotBlank(heliosImage.getLabel())){
            queryWrapper.eq(HeliosImage::getLabel, heliosImage.getLabel());
        }
        if(StrUtil.isNotBlank(heliosImage.getInference())){
            queryWrapper.eq(HeliosImage::getInference, heliosImage.getInference());
        }
        if(StrUtil.isNotBlank(heliosImage.getRemark())){
            queryWrapper.eq(HeliosImage::getRemark, heliosImage.getRemark());
        }
        //2. 执行分页查询
        Page<HeliosImage> pagin = new Page<>(current , size , true);
        IPage<HeliosImage> selectResult = heliosImageMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    public HeliosImage insert(HeliosImage heliosImage){
        heliosImageMapper.insert(heliosImage);
        return heliosImage;
    }
    
    /** 
     * 更新数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    public HeliosImage update(HeliosImage heliosImage){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<HeliosImage> chainWrapper = new LambdaUpdateChainWrapper<HeliosImage>(heliosImageMapper);
        if(StrUtil.isNotBlank(heliosImage.getCreatedBy())){
            chainWrapper.eq(HeliosImage::getCreatedBy, heliosImage.getCreatedBy());
        }
        if(StrUtil.isNotBlank(heliosImage.getUpdatedBy())){
            chainWrapper.eq(HeliosImage::getUpdatedBy, heliosImage.getUpdatedBy());
        }
        if(StrUtil.isNotBlank(heliosImage.getBizOrgCode())){
            chainWrapper.eq(HeliosImage::getBizOrgCode, heliosImage.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(heliosImage.getDeviceName())){
            chainWrapper.eq(HeliosImage::getDeviceName, heliosImage.getDeviceName());
        }
        if(StrUtil.isNotBlank(heliosImage.getBarcode())){
            chainWrapper.eq(HeliosImage::getBarcode, heliosImage.getBarcode());
        }
        if(StrUtil.isNotBlank(heliosImage.getExperimentNo())){
            chainWrapper.eq(HeliosImage::getExperimentNo, heliosImage.getExperimentNo());
        }
        if(StrUtil.isNotBlank(heliosImage.getLabTestItemName())){
            chainWrapper.eq(HeliosImage::getLabTestItemName, heliosImage.getLabTestItemName());
        }
        if(StrUtil.isNotBlank(heliosImage.getKmcsTaskId())){
            chainWrapper.eq(HeliosImage::getKmcsTaskId, heliosImage.getKmcsTaskId());
        }
        if(StrUtil.isNotBlank(heliosImage.getFileName())){
            chainWrapper.eq(HeliosImage::getFileName, heliosImage.getFileName());
        }
        if(StrUtil.isNotBlank(heliosImage.getAttPurpose())){
            chainWrapper.eq(HeliosImage::getAttPurpose, heliosImage.getAttPurpose());
        }
        if(StrUtil.isNotBlank(heliosImage.getAttachmentId())){
            chainWrapper.eq(HeliosImage::getAttachmentId, heliosImage.getAttachmentId());
        }
        if(StrUtil.isNotBlank(heliosImage.getLabel())){
            chainWrapper.eq(HeliosImage::getLabel, heliosImage.getLabel());
        }
        if(StrUtil.isNotBlank(heliosImage.getInference())){
            chainWrapper.eq(HeliosImage::getInference, heliosImage.getInference());
        }
        if(StrUtil.isNotBlank(heliosImage.getRemark())){
            chainWrapper.eq(HeliosImage::getRemark, heliosImage.getRemark());
        }
        //2. 设置主键，并更新
        chainWrapper.set(HeliosImage::getId, heliosImage.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(heliosImage.getId());
        }else{
            return heliosImage;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = heliosImageMapper.deleteById(id);
        return total > 0;
    }
}