package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.entity.LabTestItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.entity.LabTestItemRel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ;(lab_test_item_rel)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface LabTestItemRelMapper  extends BaseMapper<LabTestItemRel>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<LabTestItemRel> selectByPage(IPage<LabTestItemRel> page , @Param(Constants.WRAPPER) Wrapper<LabTestItemRel> wrapper);
    @Select("select * from lab_test_item_rel where kmcs_test_item_code = #{KmcsTestItemCode} and biz_org_code = #{bizOrgCode}")
    LabTestItemRel selectLabTestItemRelByKmcsTestItemCode(@Param("KmcsTestItemCode") String KmcsTestItemCode, @Param("bizOrgCode") String bizOrgCode);
    //一个kmcsTaskCode 只返回一个LabTestItemRel ？？


    @Select("select * from lab_test_item")
    List<LabTestItemRel> findAll();
}