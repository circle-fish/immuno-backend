package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-25
 */
@ApiModel(value = "",description = "")
@TableName("helios_image")
@Data
public class HeliosImage extends BaseEntity{

    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 设备Id */
    @ApiModelProperty(name = "",notes = "设备Id")
    private Integer deviceId ;
    /** 设备名称 */
    @ApiModelProperty(name = "",notes = "设备名称")
    private String deviceName ;
    /** 与其绑定的LabTask的Id */
    @ApiModelProperty(name = "",notes = "与其绑定的LabTask的Id")
    private Integer labTaskId ;
    /** 条码号 */
    @ApiModelProperty(name = "",notes = "条码号")
    private String barcode ;
    /** 实验号 */
    @ApiModelProperty(name = "",notes = "实验号")
    private String experimentNo ;
    /** 项目Id */
    @ApiModelProperty(name = "",notes = "项目Id")
    private Integer labTestItemId ;
    /** 项目名称 */
    @ApiModelProperty(name = "",notes = "项目名称")
    private String labTestItemName ;
    /** 与其绑定的KmcsTaskTask的Id */
    @ApiModelProperty(name = "",notes = "与其绑定的KmcsTaskTask的Id")
    private String kmcsTaskId ;
    /** 是否绑定到KmcsTask上 */
    @ApiModelProperty(name = "",notes = "是否绑定到KmcsTask上")
    private Boolean isBindedToKmcsTask ;
    /** 坐标：玻片编号 */
    @ApiModelProperty(name = "",notes = "坐标：玻片编号")
    private Integer slide ;
    /** 坐标：孔位编号 */
    @ApiModelProperty(name = "",notes = "坐标：孔位编号")
    private Integer well ;
    /** 坐标：图片编号 */
    @ApiModelProperty(name = "",notes = "坐标：图片编号")
    private Integer index ;
    /** 文件名称 */
    @ApiModelProperty(name = "",notes = "文件名称")
    private String fileName ;
    /** 文件大小 */
    @ApiModelProperty(name = "",notes = "文件大小")
    private Integer fileSize ;
    /** 图片备注 */
    @ApiModelProperty(name = "",notes = "图片备注")
    private String attPurpose ;
    /** 文件地址 */
    @ApiModelProperty(name = "",notes = "文件地址")
    private String attachmentId ;
    /** 图片判读结果 */
    @ApiModelProperty(name = "",notes = "图片判读结果")
    private String label ;
    /** AI判读结果 */
    @ApiModelProperty(name = "",notes = "AI判读结果")
    private String inference ;
    /** 备注 */
    @ApiModelProperty(name = "",notes = "备注")
    private String remark ;
    /**  */
    

}