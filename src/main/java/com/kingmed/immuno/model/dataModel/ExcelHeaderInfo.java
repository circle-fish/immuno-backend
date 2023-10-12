package com.kingmed.immuno.model.dataModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 传递打印excel表头的封装数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelHeaderInfo {
    /**
     * 表号
     */
    Integer tableNo;
    /**
     * 试剂厂家
     */
    String ReagentManufacturer;
    /**
     * 检测方法
     */
    String DetectionMethod;
    /**
     * (当前批次）室内湿度
     */
    String wet;
    /**
     * (当前批次）室内温度
     */
    String temperature;

}
