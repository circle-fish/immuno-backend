package com.kingmed.immuno.model.vo;

import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  用于传输给前端的GeneralLabTask
 *   根据device_type的不同, 会有不同的task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeliosLabTaskWithPostion extends SampleBase {

    /**
     * LabTaskBase中需要的属性，java不能继承两类，单独加上
     */
    private String bizOrgCode;
    private int labOrderId;
    private String status;

    private String barcode;
    private String experimentNo;
    /**
     * 设备相关的信息
     */
    private Integer deviceId;
    private String deviceName;

    /**
     * 项目相关
     */
    private Integer labTestItemId;
    private String labTestItemName;
    /**
     * 试剂相关的信息
     * ??? reagentDetail被删除 直接用HeliosReagent本身存储-reagentLot试剂批号
     */
    private int reagentId;
    private String reagentLot;
    /**
     * 结果格式
     */
    /** 结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定 */
    private String result ;
    /** AI结果, 与result字段的格式相同 */
    private String aiResult ;
    private int resultType;
    /**
     * 标本的坐标
     * 用于前端展示
     * device_name, slide_index, well_index
     */
    private int slideIndex;
    private int wellIndex;

    //防止samplebase空特定写个构造函数
    public HeliosLabTaskWithPostion(SampleBase base) {
       
        this.setTaskType(base.getTaskType());
        this.setDevicePosition(base.getDevicePosition());
        this.setReagentLot(base.getReagentLot());

    }

}
