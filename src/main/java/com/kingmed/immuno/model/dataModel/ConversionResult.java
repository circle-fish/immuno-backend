package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTask;
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
