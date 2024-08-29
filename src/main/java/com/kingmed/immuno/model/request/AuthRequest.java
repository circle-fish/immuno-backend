package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 用于封装调用外部Kmcs第三方登录验证接口的参数数据类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    /**
     * 登录用户名
     * */
    String loginName;
    /**
     * 密码
     * */
    String password;
    /**
     * kmcs应用编码
     * */
    String appCode;

}
