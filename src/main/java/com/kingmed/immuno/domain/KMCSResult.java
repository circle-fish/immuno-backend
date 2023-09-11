package com.kingmed.immuno.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="KMCS接口返回对象", description="KMCS接口返回对象")
public class KMCSResult<T> {

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private String errorCode;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息")
    private String errorMsg;

    /**
     * 返回数据对象 result
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;

}
