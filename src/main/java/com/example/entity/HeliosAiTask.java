package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

 /**
 * ;
 * @author : http://www.chiner.pro
 * @date : 2023-8-11
 */
@ApiModel(value = "",description = "")
@TableName("helios_ai_task")
public class HeliosAiTask implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer version ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date createdTime ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date updatedTime ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String createdBy ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String updatedBy ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long deviceId ;
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
    private String fileId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long heliosImageId ;

    /**  */
    public Integer getVersion(){
        return this.version;
    }
    /**  */
    public void setVersion(Integer version){
        this.version=version;
    }
    /**  */
    public Date getCreatedTime(){
        return this.createdTime;
    }
    /**  */
    public void setCreatedTime(Date createdTime){
        this.createdTime=createdTime;
    }
    /**  */
    public Date getUpdatedTime(){
        return this.updatedTime;
    }
    /**  */
    public void setUpdatedTime(Date updatedTime){
        this.updatedTime=updatedTime;
    }
    /**  */
    public String getCreatedBy(){
        return this.createdBy;
    }
    /**  */
    public void setCreatedBy(String createdBy){
        this.createdBy=createdBy;
    }
    /**  */
    public String getUpdatedBy(){
        return this.updatedBy;
    }
    /**  */
    public void setUpdatedBy(String updatedBy){
        this.updatedBy=updatedBy;
    }
    /**  */
    public Long getId(){
        return this.id;
    }
    /**  */
    public void setId(Long id){
        this.id=id;
    }
    /**  */
    public String getBizOrgCode(){
        return this.bizOrgCode;
    }
    /**  */
    public void setBizOrgCode(String bizOrgCode){
        this.bizOrgCode=bizOrgCode;
    }
    /**  */
    public Integer getStatus(){
        return this.status;
    }
    /**  */
    public void setStatus(Integer status){
        this.status=status;
    }
    /**  */
    public Long getDeviceId(){
        return this.deviceId;
    }
    /**  */
    public void setDeviceId(Long deviceId){
        this.deviceId=deviceId;
    }
    /**  */
    public Integer getSlide(){
        return this.slide;
    }
    /**  */
    public void setSlide(Integer slide){
        this.slide=slide;
    }
    /**  */
    public Integer getWell(){
        return this.well;
    }
    /**  */
    public void setWell(Integer well){
        this.well=well;
    }
    /**  */
    public Integer getIndex(){
        return this.index;
    }
    /**  */
    public void setIndex(Integer index){
        this.index=index;
    }
    /**  */
    public String getFileId(){
        return this.fileId;
    }
    /**  */
    public void setFileId(String fileId){
        this.fileId=fileId;
    }
    /**  */
    public Long getLabTaskId(){
        return this.labTaskId;
    }
    /**  */
    public void setLabTaskId(Long labTaskId){
        this.labTaskId=labTaskId;
    }
    /**  */
    public Long getHeliosImageId(){
        return this.heliosImageId;
    }
    /**  */
    public void setHeliosImageId(Long heliosImageId){
        this.heliosImageId=heliosImageId;
    }
}