package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.LabTestItem;

/**
 * ;(lab_test_item)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface LabTestItemService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    LabTestItem queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param labTestItem 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<LabTestItem> paginQuery(LabTestItem labTestItem, long current, long size);
    /** 
     * 新增数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    LabTestItem insert(LabTestItem labTestItem);
    /** 
     * 更新数据
     *
     * @param labTestItem 实例对象
     * @return 实例对象
     */
    LabTestItem update(LabTestItem labTestItem);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}