package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "")
@TableName("lab_test_item_rel")
@Data
public class LabTestItemRel extends BaseEntity{

    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** KmcsTask对应的项目编号 */
    @ApiModelProperty(name = "",notes = "KmcsTask对应的项目编号")
    private String kmcsTestItemCode ;
    /** KmcsTask对应的项目名称 */
    @ApiModelProperty(name = "",notes = "KmcsTask对应的项目名称")
    private String kmcsTestItemName ;
    /** 该KmcsTask对应的LabTestItem项目编号 */
    @ApiModelProperty(name = "",notes = "该KmcsTask对应的LabTestItem项目编号")
    private String labTestItemCode ;

}