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

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     * */
    @ApiModelProperty(name = "用户名",notes = "")
    private String username ;
    /**  */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /**  */
    @ApiModelProperty(name = "所属子公司",notes = "")
    private String bizOrgCode ;
    /**
     * 令牌，用于登录验证
     *  */
    @ApiModelProperty(name = "令牌",notes = "")
    private String token ;
    /**  */
    @ApiModelProperty(name = "有效期至",notes = "")
    private Date regTime ;

}