package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "")
@TableName("kmcs_task")
@Data
public class KmcsTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /** KmcsTask的TaskID */
    @ApiModelProperty(name = "",notes = "KmcsTask的TaskID")
    @TableId
    private String taskId ;
    /** 状态:      * 0 —— 初始化，待操作      * 1 —— 已转换为LabTask      * 2 —— 已保存      * 3 —— 已提交 */
    @ApiModelProperty(name = "",notes = "状态:      * 0 —— 初始化，待操作      * 1 —— 已转换为LabTask      * 2 —— 已保存      * 3 —— 已提交")
    private Integer status ;
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 申请单Id */
    @ApiModelProperty(name = "",notes = "申请单Id")
    private String appId ;
    /** 条码号 */
    @ApiModelProperty(name = "",notes = "条码号")
    private String barcode ;
    /** 实验号 */
    @ApiModelProperty(name = "",notes = "实验号")
    private String experimentNo ;
    /** 项目代码 */
    @ApiModelProperty(name = "",notes = "项目代码")
    private String testItemCode ;
    /** 项目名称 */
    @ApiModelProperty(name = "",notes = "项目名称")
    private String testItemName ;
    /** 与该对象绑定的LabTask的Id */
    @ApiModelProperty(name = "",notes = "与该对象绑定的LabTask的Id")
    private Integer labTaskId ;
    /**  */
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