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
@TableName("helios_reagent_detail")
public class HeliosReagentDetail implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String name ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String batchNo ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date expireDate ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer numWells ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer numQc ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
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
    public Long getLabTestItemId(){
        return this.labTestItemId;
    }
    /**  */
    public void setLabTestItemId(Long labTestItemId){
        this.labTestItemId=labTestItemId;
    }
    /**  */
    public String getName(){
        return this.name;
    }
    /**  */
    public void setName(String name){
        this.name=name;
    }
    /**  */
    public String getBatchNo(){
        return this.batchNo;
    }
    /**  */
    public void setBatchNo(String batchNo){
        this.batchNo=batchNo;
    }
    /**  */
    public Date getExpireDate(){
        return this.expireDate;
    }
    /**  */
    public void setExpireDate(Date expireDate){
        this.expireDate=expireDate;
    }
    /**  */
    public Integer getNumWells(){
        return this.numWells;
    }
    /**  */
    public void setNumWells(Integer numWells){
        this.numWells=numWells;
    }
    /**  */
    public Integer getNumQc(){
        return this.numQc;
    }
    /**  */
    public void setNumQc(Integer numQc){
        this.numQc=numQc;
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