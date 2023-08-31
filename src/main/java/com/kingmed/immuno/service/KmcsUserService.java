package com.kingmed.immuno.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.KmcsUser;

 /**
 * ;(kmcs_user)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
public interface KmcsUserService{
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    KmcsUser queryById(Integer id);
    
    /**
     * 分页查询
     *
     * @param kmcsUser 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<KmcsUser> paginQuery(KmcsUser kmcsUser, long current, long size);
    /** 
     * 新增数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    KmcsUser insert(KmcsUser kmcsUser);
    /** 
     * 更新数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    KmcsUser update(KmcsUser kmcsUser);
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}