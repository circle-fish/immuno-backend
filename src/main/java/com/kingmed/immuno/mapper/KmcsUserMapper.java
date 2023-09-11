package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.KmcsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ;(kmcs_user)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface KmcsUserMapper  extends BaseMapper<KmcsUser>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<KmcsUser> selectByPage(IPage<KmcsUser> page , @Param(Constants.WRAPPER) Wrapper<KmcsUser> wrapper);


    /**
    * 选取kmcs_user表中某子公司下的对应账号和密码的用户
    */
    @Select("select * from kmcs_user where " +
            "username = #{username} " +
            "and password = #{password}  " +
            "and biz_org_code = #{bizOrgCode} " +
            "LIMIT 1")
     KmcsUser selectByNameAndPwd(@Param("username") String username,
                                 @Param("password") String password,
                                 @Param("bizOrgCode") String bizOrgCode);
 }