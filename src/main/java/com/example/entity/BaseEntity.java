package com.example.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaseEntity {

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
