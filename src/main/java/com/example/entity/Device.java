package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "",description = "")
@TableName("device")
@Data
public class Device extends BaseEntity {
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
    private Integer deviceType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemIds ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer capacity ;


}

