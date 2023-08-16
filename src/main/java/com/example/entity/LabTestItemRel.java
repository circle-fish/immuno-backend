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
@TableName("lab_test_item_rel")
public class LabTestItemRel implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String kmcsTestItemCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String kmcsTestItemName ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Long labTestItemId ;

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
    public String getKmcsTestItemCode(){
        return this.kmcsTestItemCode;
    }
    /**  */
    public void setKmcsTestItemCode(String kmcsTestItemCode){
        this.kmcsTestItemCode=kmcsTestItemCode;
    }
    /**  */
    public String getKmcsTestItemName(){
        return this.kmcsTestItemName;
    }
    /**  */
    public void setKmcsTestItemName(String kmcsTestItemName){
        this.kmcsTestItemName=kmcsTestItemName;
    }
    /**  */
    public Long getLabTestItemId(){
        return this.labTestItemId;
    }
    /**  */
    public void setLabTestItemId(Long labTestItemId){
        this.labTestItemId=labTestItemId;
    }
}