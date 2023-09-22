package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 存储初始化虚拟设备的结果
 * 虚拟设备链表和设备的映射
 */
@Data
@AllArgsConstructor
public class InitVirtualMachinesResult {

    List<VirtualMachine> virtualMachines;
    Map<Integer, Device> deviceMap;
}
