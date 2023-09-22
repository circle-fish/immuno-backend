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
@TableName("device")
@Data
public class Device extends BaseEntity{
    
    /** 子公司代码 */
    @ApiModelProperty(name = "",notes = "子公司代码")
    private String bizOrgCode ;
    /** 设备状态： 0—不可用 1—可用 */
    @ApiModelProperty(name = "",notes = "设备状态： 0—不可用 1—可用")
    private Integer status ;
    /** 设备类型 */
    @ApiModelProperty(name = "",notes = "设备类型")
    private Integer deviceType ;
    /** 设备编号 */
    @ApiModelProperty(name = "",notes = "设备编号")
    private String deviceCode ;
    /** 设备名称 */
    @ApiModelProperty(name = "",notes = "设备名称")
    private String deviceName ;
    /** 运行的实验项目的Id */
    @ApiModelProperty(name = "",notes = "运行的实验项目的Id")
    private String labTestItemIds ;
    /** 容量 */
    @ApiModelProperty(name = "",notes = "容量")
    private Integer capacity ;
    /**  */
    

}