package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.KmcsTask;

 /**
 * ;(kmcs_task)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface KmcsTaskService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param taskId 主键
     * @return 实例对象
     */
    KmcsTask queryById(String taskId);
    
    /**
     * 分页查询
     *
     * @param kmcsTask 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<KmcsTask> paginQuery(KmcsTask kmcsTask, long current, long size);
    /** 
     * 新增数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    KmcsTask insert(KmcsTask kmcsTask);
    /** 
     * 更新数据
     *
     * @param kmcsTask 实例对象
     * @return 实例对象
     */
    KmcsTask update(KmcsTask kmcsTask);
    /** 
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    boolean deleteById(String taskId);
}