package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.HeliosImage;

 /**
 * ;(helios_image)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface HeliosImageService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    HeliosImage queryById(Long id);
    
    /**
     * 分页查询
     *
     * @param heliosImage 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<HeliosImage> paginQuery(HeliosImage heliosImage, long current, long size);
    /** 
     * 新增数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    HeliosImage insert(HeliosImage heliosImage);
    /** 
     * 更新数据
     *
     * @param heliosImage 实例对象
     * @return 实例对象
     */
    HeliosImage update(HeliosImage heliosImage);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}