package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.entity.KmcsTask;
import com.example.entity.LabTestItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ;(lab_test_item)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@Mapper
public interface LabTestItemMapper  extends BaseMapper<LabTestItem>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<LabTestItem> selectByPage(IPage<LabTestItem> page , @Param(Constants.WRAPPER) Wrapper<LabTestItem> wrapper);

    @Select("select * from lab_test_item where id = #{id}")
    LabTestItem selectById(@Param("id") Integer id);

    @Delete("DELETE FROM lab_test_item WHERE id = #{id}")
    Integer deleteById(@Param("id") Integer id);

//    Integer updateLabTestItem(LabTestItem labTestItem);
//
//    Integer insertLabTestItem(LabTestItem labTestItem);

    @Select("select * from lab_test_item " +
            "where code = #{testItemCode} " +
            "and biz_org_code = #{bizOrgCode}" +
            "limit 1")
    LabTestItem selectLabTestItemByCode(@Param("testItemCode")String testItemCode, @Param("bizOrgCode") String bizOrgCode);
}