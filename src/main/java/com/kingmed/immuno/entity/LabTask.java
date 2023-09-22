package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "根据KmcsTask转换成的实验任务实体类")
@TableName("lab_task")
@Data
public class LabTask extends SampleBase {
    
    /** 任务状态:
     * inited: 初始状态, 从KmTask转换后产生的初始状态
     * binded: 进入生产批次后的状态
     * allocated：任务分配后进入的状态
     * testing：进行检测中的状态
     * saved：LabTask的结果完成保存，并保存至KMCS系统中
     * submitted：LabTask的结果完成提交，并保存至KMCS系统中
     * unhandled: 该任务暂时不需要处理的状态
     * */
    @ApiModelProperty(name = "",notes = "任务状态")
    private String status ;
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 条码号 */
    @ApiModelProperty(name = "",notes = "条码号")
    private String barcode ;
    /** 实验号 */
    @ApiModelProperty(name = "",notes = "实验号")
    private String experimentNo ;

    /** 结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定 */
    @ApiModelProperty(name = "",notes = "结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定")
    private String result ;
    /** AI结果, 与result字段的格式相同 */
    @ApiModelProperty(name = "",notes = "AI结果, 与result字段的格式相同")
    private String aiResult ;
    /** 项目ID */
    @ApiModelProperty(name = "",notes = "项目ID")
    private Integer labTestItemId ;

    /** 项目名称 */
    @ApiModelProperty(name = "",notes = "项目名称")
    private String labTestItemName ;
    /** 检测批次ID, 初始时为0 */
    @ApiModelProperty(name = "",notes = "检测批次ID, 初始时为0")
    private Integer labOrderId ;
    /** 设备类型, 在转换时由项目决定 */
    @ApiModelProperty(name = "",notes = "设备类型, 在转换时由项目决定")
    private Integer deviceType ;
    /** 设备ID, 在任务分配时确定, 初始值为0 */
    @ApiModelProperty(name = "",notes = "设备ID, 在任务分配时确定, 初始值为0")
    private Integer deviceId ;
    /** 设备名称, 在任务分配时确定, 初始值为"" */
    @ApiModelProperty(name = "",notes = "设备名称, 在任务分配时确定, 初始值为空")
    private String deviceName ;
    /** 试剂类型, 在转换时由项目决定 */
    @ApiModelProperty(name = "",notes = "试剂类型, 在转换时由项目决定")
    private Integer reagentType ;

}