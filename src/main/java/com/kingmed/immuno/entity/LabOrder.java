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
@TableName("lab_order")
@Data
public class LabOrder extends BaseEntity{
    
    /** 湿度 */
    @ApiModelProperty(name = "",notes = "湿度")
    private String wet ;
    /** 温度 */
    @ApiModelProperty(name = "",notes = "温度")
    private String temperature ;
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /**  */
    

}