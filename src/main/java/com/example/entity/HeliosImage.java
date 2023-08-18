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
@TableName("helios_image")
@Data
public class HeliosImage extends BaseEntity {
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long deviceId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String kmcsTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer isBindedToKmcsTask ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer slide ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer well ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer index ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String fileName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer fileSize ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String attPurpose ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String attachmentId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String label ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String inference ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String remark ;

}