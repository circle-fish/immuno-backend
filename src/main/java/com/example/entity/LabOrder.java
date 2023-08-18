package com.example.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@ApiModel(value = "",description = "")
@TableName("lab_order")
@Data
public class LabOrder extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String wet ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String temperature ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;

}