package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.HeliosReagent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * ;(helios_reagent)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-9-22
 */
@Mapper
public interface HeliosReagentMapper  extends BaseMapper<HeliosReagent>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<HeliosReagent> selectByPage(IPage<HeliosReagent> page , @Param(Constants.WRAPPER) Wrapper<HeliosReagent> wrapper);
}