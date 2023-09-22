package com.kingmed.immuno.model.dataModel.dto;

import com.kingmed.immuno.entity.LabOrder;
import com.kingmed.immuno.entity.LabTask;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * GeneralLabTaskDO与LabOrder的结合, 用于返回数据
 * 返回labTasks队列和其对应的批次
 */
@Data
@AllArgsConstructor
public class LabOrderTaskDO {
    List<LabTask> labTasks;
    LabOrder labOrder;
}
