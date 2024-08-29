package com.kingmed.immuno.model.dataModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于一般请求的req，包含子公司代码和操作者名称
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabUser {
    private String bizOrgCode;
    private String operatorName;
    /**
     * TODO：
     *  未实现的对token后续验证的一个想法：
     *  在LabUser加入Token值来验证用户有效期
     *  或者命名为accountId 通过调用kMcs接口得到
     */
    //    private String token;
}
