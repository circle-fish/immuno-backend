package com.example.model.dataModel;

import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用于传递项目转换结果的数据类
 */
@Data
@AllArgsConstructor
public class ConversionResult {
    List<LabTask> labTasks;
    List<KmcsTask> kmcsTasks;


}
