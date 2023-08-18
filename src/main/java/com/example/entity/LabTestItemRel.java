package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@ApiModel(value = "",description = "")
@TableName("lab_test_item_rel")
@Data
public class LabTestItemRel{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String kmcsTestItemCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String kmcsTestItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;

}