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
@TableName("lab_order")
public class LabOrder implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String wet ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String temperature ;
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
    public String getWet(){
        return this.wet;
    }
    /**  */
    public void setWet(String wet){
        this.wet=wet;
    }
    /**  */
    public String getTemperature(){
        return this.temperature;
    }
    /**  */
    public void setTemperature(String temperature){
        this.temperature=temperature;
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