package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.LabOrder;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * ;(lab_order)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface LabOrderMapper  extends BaseMapper<LabOrder>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<LabOrder> selectByPage(IPage<LabOrder> page , @Param(Constants.WRAPPER) Wrapper<LabOrder> wrapper);
    @Select("select * from lab_order where created_time <= #{endTime} and created_time >= #{startTime}")
    List<LabOrder> selectValidOrdersByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}