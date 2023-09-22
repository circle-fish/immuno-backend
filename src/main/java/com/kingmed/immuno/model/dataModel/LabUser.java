package com.kingmed.immuno.model.dataModel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于一般请求的req，包含子公司代码和操作者名称
 */
@Data
@AllArgsConstructor
public class LabUser {
    private String bizOrgCode;
    private String operatorName;
}
