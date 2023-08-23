package com.example.service;

import com.example.entity.LabTask;
import com.example.model.dataModel.ConversionResult;
import com.example.model.response.BaseResponse;

import java.util.List;

public interface TaskConversionService {
    BaseResponse<ConversionResult> convertKmTaskToLabTask(String bizOrgCode);
}
