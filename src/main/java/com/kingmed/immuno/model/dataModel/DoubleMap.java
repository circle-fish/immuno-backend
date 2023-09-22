package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTestItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用于传递项目转换映射关系结果的数据类
 */
@Data
@AllArgsConstructor
public class DoubleMap {

    Map<KmcsTask, List<LabTestItem>> KmTasksToTestItems;

    Map<LabTestItem, List<KmcsTask>> TestItemsTokmTasks;

}
