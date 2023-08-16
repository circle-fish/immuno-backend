package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.HeliosReagentDetail;

 /**
 * ;(helios_reagent_detail)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface HeliosReagentDetailService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    HeliosReagentDetail queryById(Long id);
    
    /**
     * 分页查询
     *
     * @param heliosReagentDetail 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<HeliosReagentDetail> paginQuery(HeliosReagentDetail heliosReagentDetail, long current, long size);
    /** 
     * 新增数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    HeliosReagentDetail insert(HeliosReagentDetail heliosReagentDetail);
    /** 
     * 更新数据
     *
     * @param heliosReagentDetail 实例对象
     * @return 实例对象
     */
    HeliosReagentDetail update(HeliosReagentDetail heliosReagentDetail);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}