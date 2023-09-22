package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.dto.LabTaskDO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用于传递LabTask任务分配结果的数据类
 */
@Data
@AllArgsConstructor
public class TaskAllocationResult {

    List<LabTask> sampleTasks;
    List<LabTaskDO> qcTasksForInsert;
    List<LabTask> qcTasksForDelete;

}
