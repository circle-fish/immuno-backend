package com.example;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

public class TaskConfig {

    @ApiModelProperty(name = "", notes = "")
    private String appId;
    @ApiModelProperty(name = "", notes = "")
    private String experimentNo;
    @ApiModelProperty(name = "", notes = "")
    private Integer status;

    @ApiModelProperty(name = "", notes = "")
    private String barcode;
    @ApiModelProperty(name = "", notes = "")
    private String bizOrgCode;

    @ApiModelProperty(name = "", notes = "")
    private Integer version;

    @ApiModelProperty(name = "", notes = "")
    private Date createdTime;

    @ApiModelProperty(name = "", notes = "")
    private Date updatedTime;
    /**
     *
     */
    @ApiModelProperty(name = "", notes = "")
    private String createdBy;
    /**
     *
     */
    @ApiModelProperty(name = "", notes = "")
    private String updatedBy;


    public void convert(Object a) {
        try {
            if (a instanceof KmcsTask) {
                ((KmcsTask) a).setAppId(this.appId);
                ((KmcsTask) a).setBizOrgCode(this.bizOrgCode);
                ((KmcsTask) a).setBarcode(this.barcode);
                ((KmcsTask) a).setStatus(this.status);
                ((KmcsTask) a).setVersion(this.version);
                ((KmcsTask) a).setCreatedTime(this.createdTime);
                ((KmcsTask) a).setUpdatedTime(this.updatedTime);
                ((KmcsTask) a).setCreatedBy(this.createdBy);
                ((KmcsTask) a).setUpdatedBy(this.updatedBy);
            }
            if (a instanceof LabTask) {

                ((LabTask) a).setBizOrgCode(this.bizOrgCode);
                ((LabTask) a).setBarcode(this.barcode);
                ((LabTask) a).setStatus(this.status);
                ((LabTask) a).setVersion(this.version);
                ((LabTask) a).setCreatedTime(this.createdTime);
                ((LabTask) a).setUpdatedTime(this.updatedTime);
                ((LabTask) a).setCreatedBy(this.createdBy);
                ((LabTask) a).setUpdatedBy(this.updatedBy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getExperimentNo() {
        return this.experimentNo;
    }

    public void setExperimentNo(String experimentNo) {
        this.experimentNo = experimentNo;
    }


    @JSONField(name = "applicationId")
    public String getAppId() { return this.appId;}
    @JSONField(name = "applicationId")
    public void setAppId(String appId) {  this.appId = appId;}
    /**
     *
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     *
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     *
     */
    public String getBizOrgCode() {
        return this.bizOrgCode;
    }

    /**
     *
     */
    public void setBizOrgCode(String bizOrgCode) {
        this.bizOrgCode = bizOrgCode;
    }
    /**  */
    public Integer getStatus(){
        return this.status;
    }
    /**  */
    public void setStatus(Integer status){
        this.status=status;
    }
    /**
     *
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     *
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     *
     */
    public Date getCreatedTime() {
        return this.createdTime;
    }

    /**
     *
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *
     */
    public Date getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     *
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     *
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     *
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     */
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     *
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}


