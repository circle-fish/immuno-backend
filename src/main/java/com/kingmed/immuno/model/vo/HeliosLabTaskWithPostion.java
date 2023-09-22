package com.kingmed.immuno.model.vo;

import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  用于传输给前端的GeneralLabTask
 *   根据device_type的不同, 会有不同的task
 */
@Data
@AllArgsConstructor
public class HeliosLabTaskWithPostion extends SampleBase {

    /**
     * LabTaskBase中需要的属性，java不能继承两类，单独加上
     */
    private String bizOrgCode;
    private int labOrderId;
    private String status;

    private String barcode;

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
     */
    //??? reagentDetail被删除 直接用HeliosReagent本身存储
    private int reagentId;
    private String reagentLot;
    /**
     * 结果格式
     */
    private int resultType;
    /**
     * 标本的坐标
     * 用于前端展示
     * device_name, slide_index, well_index
     */
    private int slideIndex;
    private int wellIndex;

    public HeliosLabTaskWithPostion() {}
    private class Config{
        private boolean ormMode = true;

        public boolean getOrmMode() {
            return ormMode;
        }
    }


}
