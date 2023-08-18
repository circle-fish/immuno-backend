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
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer taskType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer resultType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long resultId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String result ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String aiResult ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer labTestItemType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labOrderId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer deviceType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long deviceId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String devicePosition ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer reagentType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long reagentDetailId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String reagentLot ;

}