package com.kingmed.immuno.model.dataModel.dto;


import com.kingmed.immuno.entity.BaseEntity;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 样本的接口类, 用于在分配任务的时候, 从VirtualSlide中遍历的时候, 做分配
 */
@Data
public class SampleBase extends BaseEntity {

    /** 任务类型：   0: 常规任务   1: 质控任务 */
    @ApiModelProperty(name = "",notes = "任务类型：   0: 常规任务   1: 质控任务")
    private Integer taskType;

    /** 设备位置, 在任务分配时确定, 初始值为"" */
    @ApiModelProperty(name = "",notes = "设备位置, 在任务分配时确定, 初始值为空")
    private String devicePosition;

    @ApiModelProperty(name = "",notes = "试剂批号, 在任务分配时确定")
    private String reagentLot;

    public HeliosLabTaskWithPostion ToHeliosLabTaskWithPostion(){
        SampleBase sampleBase = this;
        if(sampleBase instanceof HeliosLabTaskWithPostion){
            return (HeliosLabTaskWithPostion) sampleBase;
        }
        return new HeliosLabTaskWithPostion(this);
    }
}
