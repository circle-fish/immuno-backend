package com.kingmed.immuno.service;

import com.kingmed.immuno.model.dataModel.ConversionResult;
import com.kingmed.immuno.model.response.BaseResponse;

public interface TaskConversionService {
    BaseResponse<ConversionResult> convertKmTaskToLabTask(String bizOrgCode);
}
