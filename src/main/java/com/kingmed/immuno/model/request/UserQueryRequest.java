package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQueryRequest {
    String username;
    String password;
    String bizOrgCode;
}
