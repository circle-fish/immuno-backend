package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
@TableName("lab_task")
public class LabTask implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "主键ID",notes = "雪花算法生成")
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
    public Integer getStatus(){
        return this.status;
    }
    /**  */
    public void setStatus(Integer status){
        this.status=status;
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
    public Integer getTaskType(){
        return this.taskType;
    }
    /**  */
    public void setTaskType(Integer taskType){
        this.taskType=taskType;
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
    public String getResult(){
        return this.result;
    }
    /**  */
    public void setResult(String result){
        this.result=result;
    }
    /**  */
    public String getAiResult(){
        return this.aiResult;
    }
    /**  */
    public void setAiResult(String aiResult){
        this.aiResult=aiResult;
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
    @JSONField(name = "testItemName")
    public String getLabTestItemName(){
        return this.labTestItemName;
    }
    /**  */
    @JSONField(name = "testItemName")
    public void setLabTestItemName(String labTestItemName){
        this.labTestItemName=labTestItemName;
    }
    /**  */
    public Integer getLabTestItemType(){
        return this.labTestItemType;
    }
    /**  */
    public void setLabTestItemType(Integer labTestItemType){
        this.labTestItemType=labTestItemType;
    }
    /**  */
    public Long getLabOrderId(){
        return this.labOrderId;
    }
    /**  */
    public void setLabOrderId(Long labOrderId){
        this.labOrderId=labOrderId;
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
    public Long getDeviceId(){
        return this.deviceId;
    }
    /**  */
    public void setDeviceId(Long deviceId){
        this.deviceId=deviceId;
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
    public String getDevicePosition(){
        return this.devicePosition;
    }
    /**  */
    public void setDevicePosition(String devicePosition){
        this.devicePosition=devicePosition;
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
    public String getReagentLot(){
        return this.reagentLot;
    }
    /**  */
    public void setReagentLot(String reagentLot){
        this.reagentLot=reagentLot;
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