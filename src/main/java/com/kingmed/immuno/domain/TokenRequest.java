package com.kingmed.immuno.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
* 用于封装调用外部Kmcs接口需要的request参数的数据类
 */
@Data
@AllArgsConstructor
public class TokenRequest {
    String username;
    String password;
    String bizOrgCode;
}
