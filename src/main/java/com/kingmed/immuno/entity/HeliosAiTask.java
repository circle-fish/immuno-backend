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
@TableName("helios_ai_task")
@Data
public class HeliosAiTask extends BaseEntity{
    
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 任务状态：初始状态 — 0 已上收 — 1 已判读 — 2  */
    @ApiModelProperty(name = "",notes = "任务状态：初始状态 — 0 已上收 — 1 已判读 — 2 ")
    private Integer status ;
    /** 设备编号 */
    @ApiModelProperty(name = "",notes = "设备编号")
    private Integer deviceId ;
    /** 坐标：玻片编号 */
    @ApiModelProperty(name = "",notes = "坐标：玻片编号")
    private Integer slide ;
    /** 坐标：孔位编号 */
    @ApiModelProperty(name = "",notes = "坐标：孔位编号")
    private Integer well ;
    /** 坐标: 图片编号 */
    @ApiModelProperty(name = "",notes = "坐标: 图片编号")
    private Integer index ;
    /** 获取图片的url */
    @ApiModelProperty(name = "",notes = "获取图片的url")
    private String fileId ;
    /** 所属的LabTask的Id */
    @ApiModelProperty(name = "",notes = "所属的LabTask的Id")
    private Integer labTaskId ;
    /** 附件helios_image的Id */
    @ApiModelProperty(name = "",notes = "附件helios_image的Id")
    private Integer heliosImageId ;
    /**  */
    

}