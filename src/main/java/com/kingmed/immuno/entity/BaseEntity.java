package com.kingmed.immuno.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
/**
 * 通用字段 （包括ID）
 * */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "",notes = "")
    @TableId(type = IdType.AUTO)
    private Integer id ;

    @TableField(value = "version",fill = FieldFill.INSERT)
    private Integer version ;
    /**  */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime ;
    /**  */
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime ;
    /**  */
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy ;
    /**  */
    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy ;


}
