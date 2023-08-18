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
@TableName("kmcs_task")
@Data
public class KmcsTask extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private String taskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String appId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;

}