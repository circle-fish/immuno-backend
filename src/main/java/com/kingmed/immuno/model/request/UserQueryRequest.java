package com.kingmed.immuno.model.request;

import lombok.Data;

@Data
public class UserQueryRequest {
    String username;
    String password;
    String bizOrgCode;
}
