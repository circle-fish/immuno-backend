package com.example.entity;

import com.example.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@ApiModel(value = "",description = "")
@TableName("lab_task")
@Data
public class LabTask extends BaseEntity {

    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;

    // AppId
    @ApiModelProperty(name = "",notes = "")
    private String appId ;

    // 条码号
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;

    // 实验号
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;

    // 任务状态
    // inited: 初始状态, 从KmTask转换后产生的初始状态
    // binded: 进入生产批次后的状态
    // allocated：任务分配后进入的状态
    // testing：进行检测中的状态
    // saved：LabTask的结果完成保存，并保存至KMCS系统中
    // submitted：LabTask的结果完成提交，并保存至KMCS系统中
    // unhandled: 该任务暂时不需要处理的状态
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;

    // 子公司代码
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;

    /**
     * 任务类型
     * 0: 常规任务
     * 1: 质控任务
     * */
    @ApiModelProperty(name = "",notes = "")
    private Integer taskType ;


    /**
     * 结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定
     * */
    @ApiModelProperty(name = "",notes = "结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定")
    private String result ;

    /**
     * AI结果, 与result字段的格式相同
     * */
    @ApiModelProperty(name = "",notes = "")
    private String aiResult ;

    /**
     * 项目ID
     * */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;

    /**
     * 项目代码
     * */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemCode;

    /**
     * 项目名称
     * */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemName ;

    /**
     * 检测批次ID, 初始时为0
     * */
    @ApiModelProperty(name = "",notes = "")
    private Long labOrderId ;

    /**
     * 设备类型, 在转换时由项目决定
     * */
    @ApiModelProperty(name = "",notes = "")
    private Integer deviceType ;

    /**
     * 设备ID, 在任务分配时确定, 初始值为0
     * */
    @ApiModelProperty(name = "",notes = "")
    private Long deviceId ;


    /**
     * 设备名称, 在任务分配时确定, 初始值为""
     * */
    @ApiModelProperty(name = "",notes = "")
    private String deviceName ;

    /**
     * 设备位置, 在任务分配时确定, 初始值为""
     * */
    @ApiModelProperty(name = "",notes = "")
    private String devicePosition ;

    /**
     * 试剂类型, 在转换时由项目决定
     * */
    @ApiModelProperty(name = "",notes = "")
    private Integer reagentType;

    /**
     * 试剂批号, 在任务分配时确定
     * */
    @ApiModelProperty(name = "",notes = "")
    private String reagentLot;

    /**
     * 试剂有效日期, 在任务分配时确定
     * */
    @ApiModelProperty(name = "",notes = "")
    private String reagentValidDate;
}