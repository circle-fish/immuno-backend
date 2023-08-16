package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Device;
import com.example.mapper.DeviceMapper;
import com.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 /**
 * ;(device)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DeviceMapper deviceMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public Device queryById(Long id){
        return deviceMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param device 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<Device> paginQuery(Device device, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(device.getBizOrgCode())){
            queryWrapper.eq(Device::getBizOrgCode, device.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(device.getDeviceCode())){
            queryWrapper.eq(Device::getDeviceCode, device.getDeviceCode());
        }
        if(StrUtil.isNotBlank(device.getDeviceName())){
            queryWrapper.eq(Device::getDeviceName, device.getDeviceName());
        }
        if(StrUtil.isNotBlank(device.getLabTestItemIds())){
            queryWrapper.eq(Device::getLabTestItemIds, device.getLabTestItemIds());
        }
        if(StrUtil.isNotBlank(device.getCreatedBy())){
            queryWrapper.eq(Device::getCreatedBy, device.getCreatedBy());
        }
        if(StrUtil.isNotBlank(device.getUpdatedBy())){
            queryWrapper.eq(Device::getUpdatedBy, device.getUpdatedBy());
        }
        //2. 执行分页查询
        Page<Device> pagin = new Page<>(current , size , true);
        IPage<Device> selectResult = deviceMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    public Device insert(Device device){
        deviceMapper.insert(device);
        return device;
    }
    
    /** 
     * 更新数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    public Device update(Device device){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<Device> chainWrapper = new LambdaUpdateChainWrapper<Device>(deviceMapper);
        if(StrUtil.isNotBlank(device.getBizOrgCode())){
            chainWrapper.eq(Device::getBizOrgCode, device.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(device.getDeviceCode())){
            chainWrapper.eq(Device::getDeviceCode, device.getDeviceCode());
        }
        if(StrUtil.isNotBlank(device.getDeviceName())){
            chainWrapper.eq(Device::getDeviceName, device.getDeviceName());
        }
        if(StrUtil.isNotBlank(device.getLabTestItemIds())){
            chainWrapper.eq(Device::getLabTestItemIds, device.getLabTestItemIds());
        }
        if(StrUtil.isNotBlank(device.getCreatedBy())){
            chainWrapper.eq(Device::getCreatedBy, device.getCreatedBy());
        }
        if(StrUtil.isNotBlank(device.getUpdatedBy())){
            chainWrapper.eq(Device::getUpdatedBy, device.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(Device::getId, device.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(device.getId());
        }else{
            return device;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = deviceMapper.deleteById(id);
        return total > 0;
    }
}