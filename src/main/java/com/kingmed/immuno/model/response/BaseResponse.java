package com.kingmed.immuno.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private String code;

    /**
     * 返回消息
     */

    @ApiModelProperty(value = "返回代码")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

}
