package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 试剂的详细信息
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "")
@TableName("helios_reagent")
@Data
public class HeliosReagent extends BaseEntity{
    
    /** 试剂名称 */
    @ApiModelProperty(name = "",notes = "试剂名称")
    private String name ;
    /** 批次号 */
    @ApiModelProperty(name = "",notes = "批次号")
    private String batchNo ;
    /** 有效日期至 */
    @ApiModelProperty(name = "",notes = "有效至日期")
    private Date expireDate ;
    /** 玻片中孔的数目 */
    @ApiModelProperty(name = "",notes = "玻片中孔的数目")
    private Integer numWells ;
    /** 质控数 */
    @ApiModelProperty(name = "",notes = "质控数")
    private Integer numQc ;
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /**  */
    

}