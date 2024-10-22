package com.kingmed.immuno.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kingmed.immuno.entity.LabTestItemRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    List<LabTestItemRel> selectLabTestItemRelsByKmcsTestItemCode(@Param("KmcsTestItemCode") String KmcsTestItemCode, @Param("bizOrgCode") String bizOrgCode);
    //一个kmcsTaskCode 可能对应多个 labtestitemcode !!


    @Select("select * from lab_test_item")
    List<LabTestItemRel> findAll();

    @Select("select * from lab_test_item_rel where lab_test_item_code = #{LabTestItemCode} and biz_org_code = #{bizOrgCode}")
    List<LabTestItemRel> selectLabTestItemRelsByLabTestItemCode(
            @Param("LabTestItemCode") String LabTestItemCode,
            @Param("bizOrgCode") String bizOrgCode);
}