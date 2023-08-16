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
@TableName("kmcs_task")
public class KmcsTask implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private String taskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String appId ;
     /**  */
     @ApiModelProperty(name = "",notes = "")
     private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String barcode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String experimentNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String testItemName ;

    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTaskId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer status ;
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
    public String getTaskId(){
        return this.taskId;
    }
    /**  */
    public void setTaskId(String taskId){
        this.taskId=taskId;
    }
    /**  */
    public String getAppId(){
        return this.appId;
    }
    /**  */
    public void setAppId(String appId){
        this.appId=appId;
    }
    /**  */
    public String getBarcode(){
        return this.barcode;
    }
    /**  */
    public void setBarcode(String barcode){
        this.barcode=barcode;
    }
    /**  */
    public String getExperimentNo(){
        return this.experimentNo;
    }
    /**  */
    public void setExperimentNo(String experimentNo){
        this.experimentNo=experimentNo;
    }
    /**  */
    public String getTestItemCode(){
        return this.testItemCode;
    }
    /**  */
    public void setTestItemCode(String testItemCode){
        this.testItemCode=testItemCode;
    }
    /**  */
    public String getTestItemName(){
        return this.testItemName;
    }
    /**  */
    public void setTestItemName(String testItemName){
        this.testItemName=testItemName;
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
    public Long getLabTaskId(){
        return this.labTaskId;
    }
    /**  */
    public void setLabTaskId(Long labTaskId){
        this.labTaskId=labTaskId;
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