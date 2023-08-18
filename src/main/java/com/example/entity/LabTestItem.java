package com.example.entity;

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
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer type ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer transferMode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer resultType ;
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
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long reagentDetailId ;

}