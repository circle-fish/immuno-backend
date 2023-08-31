package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTestItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
public class DoubleMap {

    Map<KmcsTask, List<LabTestItem>> KmTasksToTestItems;

    Map<LabTestItem, List<KmcsTask>> TestItemsTokmTasks;

}
