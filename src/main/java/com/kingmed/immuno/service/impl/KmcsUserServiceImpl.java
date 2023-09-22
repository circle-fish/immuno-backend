package com.kingmed.immuno.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.mapper.KmcsUserMapper;
import com.kingmed.immuno.model.request.UserQueryRequest;
import com.kingmed.immuno.service.KmcsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ;(kmcs_user)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Service
public class KmcsUserServiceImpl implements KmcsUserService {
    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    @Autowired
    private KmcsServiceImpl kmcsService;

    /**
     * 通过ID查询kmcsUser
     *
     * @param id 主键
     * @return 实例对象
     */
    public KmcsUser queryById(Integer id){
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

     /**
      * 登录验证
      *
      * @param userQueryRequest 用户查询请求
      * @return boolean 验证结果
      */
     @Override
     public KmcsUser LoginQuery(UserQueryRequest userQueryRequest) {
//         Map<> ??? selectByMap 方法代替

         KmcsUser kmcsUser = kmcsUserMapper.selectByNameAndPwd(userQueryRequest.getUsername(),
                 userQueryRequest.getPassword(),
                 userQueryRequest.getBizOrgCode()
         );

         String username = userQueryRequest.getUsername();
         if(kmcsUser == null) {
             throw new ServiceException("在数据库中不存在用户名为"+username+"的用户！");
         }
//         //检查用户有效期并设置token
         String token = kmcsService.getToken(userQueryRequest.getBizOrgCode());
         /*
         * 设计上先从一个子公司去取缓存用户所使用的token ???
          */
//         String accountId = kmcsService.getAccountIdFromKmcs(kmcsUser); ???接口返回登录失败相关函数
         if ( token != null) {
             return kmcsUser;
         } else {
             throw new ServiceException("未获取到对应用户的token 用户名: "+username);
         }
     }

 }