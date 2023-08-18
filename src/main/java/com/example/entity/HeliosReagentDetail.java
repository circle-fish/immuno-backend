package com.example.entity;

import com.example.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "",description = "")
@TableName("helios_reagent_detail")
@Data
public class HeliosReagentDetail extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String name ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String batchNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date expireDate ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer numWells ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer numQc ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;

}