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
@TableName("kmcs_user")
public class KmcsUser implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "")
    @TableId
    private Long id ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String username ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String password ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String bizOrgCode ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private String token ;
    /**  */
    @ApiModelProperty(name = "",notes = "")
    private Date regTime ;

    /**  */
    public Long getId(){
        return this.id;
    }
    /**  */
    public void setId(Long id){
        this.id=id;
    }
    /**  */
    public String getUsername(){
        return this.username;
    }
    /**  */
    public void setUsername(String username){
        this.username=username;
    }
    /**  */
    public String getPassword(){
        return this.password;
    }
    /**  */
    public void setPassword(String password){
        this.password=password;
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
    public String getToken(){
        return this.token;
    }
    /**  */
    public void setToken(String token){
        this.token=token;
    }
    /**  */
    public Date getRegTime(){
        return this.regTime;
    }
    /**  */
    public void setRegTime(Date regTime){
        this.regTime=regTime;
    }
}