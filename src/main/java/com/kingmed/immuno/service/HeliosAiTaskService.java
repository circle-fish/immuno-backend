package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.HeliosAiTask;
import com.kingmed.immuno.entity.HeliosImage;

/**
 * ;(helios_ai_task)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface HeliosAiTaskService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    HeliosAiTask queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param heliosAiTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<HeliosAiTask> paginQuery(HeliosAiTask heliosAiTask, long current, long size);
    /** 
     * 新增数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    HeliosAiTask insert(HeliosAiTask heliosAiTask);
    /** 
     * 更新数据
     *
     * @param heliosAiTask 实例对象
     * @return 实例对象
     */
    HeliosAiTask update(HeliosAiTask heliosAiTask);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 插入或更新HeliosAITask
     * @param heliosImage
     * @param operatorName
     * @return
     */
    HeliosAiTask upsertHeliosAiTask(HeliosImage heliosImage, String operatorName);
}