package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.LabTask;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * 用于传递任务分配的任务获取过程的数据类
 */
@Data
@AllArgsConstructor
public class SampleAndQCTasksResult {
    List<LabTask> sampleTasks;
    List<LabTask> qcTasks;
}
