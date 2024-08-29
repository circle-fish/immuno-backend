package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryRequest {
    String username;
    String password;
    String bizOrgCode;
}
