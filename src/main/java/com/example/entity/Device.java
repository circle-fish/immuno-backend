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
@TableName("device")
public class Device implements Serializable,Cloneable{
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
    private Integer deviceType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String deviceName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String labTestItemIds ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer capacity ;
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
    public Integer getDeviceType(){
        return this.deviceType;
    }
    /**  */
    public void setDeviceType(Integer deviceType){
        this.deviceType=deviceType;
    }
    /**  */
    public String getDeviceCode(){
        return this.deviceCode;
    }
    /**  */
    public void setDeviceCode(String deviceCode){
        this.deviceCode=deviceCode;
    }
    /**  */
    public String getDeviceName(){
        return this.deviceName;
    }
    /**  */
    public void setDeviceName(String deviceName){
        this.deviceName=deviceName;
    }
    /**  */
    public String getLabTestItemIds(){
        return this.labTestItemIds;
    }
    /**  */
    public void setLabTestItemIds(String labTestItemIds){
        this.labTestItemIds=labTestItemIds;
    }
    /**  */
    public Integer getCapacity(){
        return this.capacity;
    }
    /**  */
    public void setCapacity(Integer capacity){
        this.capacity=capacity;
    }
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
}