package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeliosImageQueryBase extends  HeliosImagePosionInDevice{
    int labTaskId;
    String bizOrgCode;
    int deviceId;
    String operatorName;
}
