package com.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户年龄")
    private Integer age;

}
