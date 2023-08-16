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
@TableName("helios_image")
public class HeliosImage implements Serializable,Cloneable{
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
    public Long getLabTaskId(){
        return this.labTaskId;
    }
    /**  */
    public void setLabTaskId(Long labTaskId){
        this.labTaskId=labTaskId;
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
    public Long getLabTestItemId(){
        return this.labTestItemId;
    }
    /**  */
    public void setLabTestItemId(Long labTestItemId){
        this.labTestItemId=labTestItemId;
    }
    /**  */
    public String getLabTestItemName(){
        return this.labTestItemName;
    }
    /**  */
    public void setLabTestItemName(String labTestItemName){
        this.labTestItemName=labTestItemName;
    }
    /**  */
    public String getKmcsTaskId(){
        return this.kmcsTaskId;
    }
    /**  */
    public void setKmcsTaskId(String kmcsTaskId){
        this.kmcsTaskId=kmcsTaskId;
    }
    /**  */
    public Integer getIsBindedToKmcsTask(){
        return this.isBindedToKmcsTask;
    }
    /**  */
    public void setIsBindedToKmcsTask(Integer isBindedToKmcsTask){
        this.isBindedToKmcsTask=isBindedToKmcsTask;
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
    public String getFileName(){
        return this.fileName;
    }
    /**  */
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    /**  */
    public Integer getFileSize(){
        return this.fileSize;
    }
    /**  */
    public void setFileSize(Integer fileSize){
        this.fileSize=fileSize;
    }
    /**  */
    public String getAttPurpose(){
        return this.attPurpose;
    }
    /**  */
    public void setAttPurpose(String attPurpose){
        this.attPurpose=attPurpose;
    }
    /**  */
    public String getAttachmentId(){
        return this.attachmentId;
    }
    /**  */
    public void setAttachmentId(String attachmentId){
        this.attachmentId=attachmentId;
    }
    /**  */
    public String getLabel(){
        return this.label;
    }
    /**  */
    public void setLabel(String label){
        this.label=label;
    }
    /**  */
    public String getInference(){
        return this.inference;
    }
    /**  */
    public void setInference(String inference){
        this.inference=inference;
    }
    /**  */
    public String getRemark(){
        return this.remark;
    }
    /**  */
    public void setRemark(String remark){
        this.remark=remark;
    }
}