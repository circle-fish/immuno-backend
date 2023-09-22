package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.HeliosReagent;

 /**
 * ;(helios_reagent)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-9-22
 */
public interface HeliosReagentService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    HeliosReagent queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param heliosReagent 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<HeliosReagent> paginQuery(HeliosReagent heliosReagent, long current, long size);
    /** 
     * 新增数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    HeliosReagent insert(HeliosReagent heliosReagent);
    /** 
     * 更新数据
     *
     * @param heliosReagent 实例对象
     * @return 实例对象
     */
    HeliosReagent update(HeliosReagent heliosReagent);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

     /**
      * 更新或插入数据
      *
      * @param heliosReagent 实例对象
      * @return 试剂对象
      */
    HeliosReagent upsert(HeliosReagent heliosReagent);
 }