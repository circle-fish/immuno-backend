package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * ;(device)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
@Repository
public interface DeviceMapper  extends BaseMapper<Device>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<Device> selectByPage(IPage<Device> page , @Param(Constants.WRAPPER) Wrapper<Device> wrapper);


}