package com.example.entity;

import com.example.common.EnumManager;

import com.example.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "",description = "")
@TableName("lab_test_item")
@Data
public class LabTestItem extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String name ;
    /**
     * 项目代码
     * 在一个子公司中的testItemCode是唯一的
     * */
    @ApiModelProperty(name = "",notes = "")
    private String code ;

    @ApiModelProperty(name = "",notes = "转换模式")
    private EnumManager.LabTestItemMode conversionMode ;


    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer sortedIndex ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer deviceType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer reagentType ;

}