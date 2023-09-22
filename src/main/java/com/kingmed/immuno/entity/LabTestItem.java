package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.kingmed.immuno.common.EnumManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "")
@TableName("lab_test_item")
@Data
public class LabTestItem extends BaseEntity{

    /** 项目名称 */
    @ApiModelProperty(name = "",notes = "项目名称")
    private String name ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String code ;
    /** 转换模式：LOOSE 宽松转换；STRICT 严格转换 */
    @ApiModelProperty(name = "",notes = "转换模式：" +
            "LOOSE 宽松转换—— 实际的项目代码集合与需要的项目代码集合有交集即可 ; " +
            "STRICT 严格转换—— 实际的项目代码集合需要是需要的项目代码集合的父集  ")
    private EnumManager.LabTestItemMode conversionMode ;
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 排序权重 */
    @ApiModelProperty(name = "",notes = "排序权重")
    private Integer sortedIndex ;
    /** 设备类型 */
    @ApiModelProperty(name = "",notes = "设备类型")
    private Integer deviceType ;
    /** 试剂类型 */
    @ApiModelProperty(name = "",notes = "试剂类型")
    private Integer reagentType ;

}