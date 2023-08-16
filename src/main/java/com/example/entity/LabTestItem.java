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
@TableName("lab_test_item")
public class LabTestItem implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String name ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer type ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer transferMode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer resultType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer sortedIndex ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer deviceType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Integer reagentType ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long reagentDetailId ;
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
    public String getName(){
        return this.name;
    }
    /**  */
    public void setName(String name){
        this.name=name;
    }
    /**  */
    public Integer getType(){
        return this.type;
    }
    /**  */
    public void setType(Integer type){
        this.type=type;
    }
    /**  */
    public Integer getTransferMode(){
        return this.transferMode;
    }
    /**  */
    public void setTransferMode(Integer transferMode){
        this.transferMode=transferMode;
    }
    /**  */
    public Integer getResultType(){
        return this.resultType;
    }
    /**  */
    public void setResultType(Integer resultType){
        this.resultType=resultType;
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
    public Integer getSortedIndex(){
        return this.sortedIndex;
    }
    /**  */
    public void setSortedIndex(Integer sortedIndex){
        this.sortedIndex=sortedIndex;
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
    public Integer getReagentType(){
        return this.reagentType;
    }
    /**  */
    public void setReagentType(Integer reagentType){
        this.reagentType=reagentType;
    }
    /**  */
    public Long getReagentDetailId(){
        return this.reagentDetailId;
    }
    /**  */
    public void setReagentDetailId(Long reagentDetailId){
        this.reagentDetailId=reagentDetailId;
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