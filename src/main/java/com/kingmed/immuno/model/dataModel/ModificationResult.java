package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

/**
 * 传回数据更新的结果以及设备更新对应的虚拟设备VirtualMachineList
 */
public class ModificationResult {
    List<Device> devices;
    List<LabTask> labTasks;
    List<VirtualMachine> retVirtualMachines;
}
