package com.kingmed.immuno.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value="LoginUser对象", description="用户信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {

//    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    @ApiModelProperty(value = "用户唯一标识")
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "工号")
    private String userNo;

    /**
     * 归属子公司
     */
    @ApiModelProperty(value = "归属子公司")
    private String branchCode;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Long loginTime;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private Long expireTime;


}
