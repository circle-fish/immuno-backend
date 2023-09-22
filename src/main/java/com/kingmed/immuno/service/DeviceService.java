package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.Device;

import java.util.List;

/**
 * ;(device)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface DeviceService {
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param device 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<Device> paginQuery(Device device, long current, long size);
    /** 
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device insert(Device device);
    /** 
     * 更新数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device update(Device device);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<Device> getDeviceInfo(String bizOrgCode);

    Device upsert(Device device);
}