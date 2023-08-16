package com.example.serviceimpl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.entity.KmcsUser;
import com.example.mapper.KmcsUserMapper;
import com.example.service.KmcsUserService;
 /**
 * ;(kmcs_user)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class KmcsUserServiceImpl implements KmcsUserService{
    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public KmcsUser queryById(Long id){
        return kmcsUserMapper.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param kmcsUser 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<KmcsUser> paginQuery(KmcsUser kmcsUser, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<KmcsUser> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(kmcsUser.getUsername())){
            queryWrapper.eq(KmcsUser::getUsername, kmcsUser.getUsername());
        }
        if(StrUtil.isNotBlank(kmcsUser.getPassword())){
            queryWrapper.eq(KmcsUser::getPassword, kmcsUser.getPassword());
        }
        if(StrUtil.isNotBlank(kmcsUser.getBizOrgCode())){
            queryWrapper.eq(KmcsUser::getBizOrgCode, kmcsUser.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(kmcsUser.getToken())){
            queryWrapper.eq(KmcsUser::getToken, kmcsUser.getToken());
        }
        //2. 执行分页查询
        Page<KmcsUser> pagin = new Page<>(current , size , true);
        IPage<KmcsUser> selectResult = kmcsUserMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
    
    /** 
     * 新增数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    public KmcsUser insert(KmcsUser kmcsUser){
        kmcsUserMapper.insert(kmcsUser);
        return kmcsUser;
    }
    
    /** 
     * 更新数据
     *
     * @param kmcsUser 实例对象
     * @return 实例对象
     */
    public KmcsUser update(KmcsUser kmcsUser){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<KmcsUser> chainWrapper = new LambdaUpdateChainWrapper<KmcsUser>(kmcsUserMapper);
        if(StrUtil.isNotBlank(kmcsUser.getUsername())){
            chainWrapper.eq(KmcsUser::getUsername, kmcsUser.getUsername());
        }
        if(StrUtil.isNotBlank(kmcsUser.getPassword())){
            chainWrapper.eq(KmcsUser::getPassword, kmcsUser.getPassword());
        }
        if(StrUtil.isNotBlank(kmcsUser.getBizOrgCode())){
            chainWrapper.eq(KmcsUser::getBizOrgCode, kmcsUser.getBizOrgCode());
        }
        if(StrUtil.isNotBlank(kmcsUser.getToken())){
            chainWrapper.eq(KmcsUser::getToken, kmcsUser.getToken());
        }
        //2. 设置主键，并更新
        chainWrapper.set(KmcsUser::getId, kmcsUser.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(kmcsUser.getId());
        }else{
            return kmcsUser;
        }
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = kmcsUserMapper.deleteById(id);
        return total > 0;
    }
}