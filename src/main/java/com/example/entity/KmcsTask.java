package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "",description = "")
@TableName("kmcs_task")
@Data

public class KmcsTask {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private String taskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String appId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**
     * 0 —— 初始化，待操作
     * 1 —— 已转换为LabTask
     * 2 —— 已保存
     * 3 —— 已提交
     * */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;

    @TableField(value = "version",fill = FieldFill.INSERT)
    private Integer version ;
    /**  */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime ;
    /**  */
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime ;
    /**  */
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy ;
    /**  */
    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy ;
}