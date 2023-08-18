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
@TableName("helios_ai_task")
@Data
public class HeliosAiTask extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long deviceId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer slide ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer well ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer index ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String fileId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long heliosImageId ;

}