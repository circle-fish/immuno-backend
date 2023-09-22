package com.kingmed.immuno.model.dataModel.dto;

/**
 * Heliostask的数据结构
 */
public class HelioLabTaskDO extends LabTaskDO{
    private int slideIndex;
    private int wellIndex;

    /**
     * 构造函数部分要重新给slideIndex和wellIndex初始化赋值
     * 这两个字段的值由自身的deviceposition决定
     */
    public HelioLabTaskDO(){
        super();
        if(this.getDevicePosition()!=null){
            String[] devicePosition = this.getDevicePosition().split("-");
            this.slideIndex = Integer.parseInt(devicePosition[1]);
            this.wellIndex = Integer.parseInt(devicePosition[2]);
        }
    }

}
