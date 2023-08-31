package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@ApiModel(value = "",description = "")
@TableName("kmcs_user")
@Data
public class KmcsUser extends BaseEntity{

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