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
@TableName("kmcs_user")
@Data
public class KmcsUser extends BaseEntity{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String username ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String password ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String token ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date regTime ;

}