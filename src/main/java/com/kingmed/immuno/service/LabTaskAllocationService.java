package com.kingmed.immuno.service;

import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.InitVirtualMachinesResult;
import com.kingmed.immuno.model.dataModel.SampleAndQCTasksResult;
import com.kingmed.immuno.model.dataModel.TaskAllocationResult;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;

import java.util.List;

public interface LabTaskAllocationService {


    /**
     * 处理Helios的s该函数用来处理Helios设备的任务分配问题
     * 在第一个版本优先解决ANA的任务分配问题
     * @param labTasks
     * @param devices
     * @param heliosReagent
     * @param operatorName
     * @return 任务分配结果
     */
    TaskAllocationResult heliosTaskAllocation(List<LabTask> labTasks,
                                              List<Device> devices,
                                              HeliosReagent heliosReagent,
                                              String operatorName);

    /**
     *初始化虚拟的设备
     */
    InitVirtualMachinesResult initVirtualMachines(List<Device> devices);

    /**
     * 在向虚拟Slide添加样本时, 需要修改LabTask的相关属性, 后面进行Commit
     *
     * @param labTask
     * @param heliosLabTaskWithPostion
     * @param virtualMachine
     */

    void insertLabTaskIntoVirtualSlide(LabTask labTask,
                                              HeliosLabTaskWithPostion heliosLabTaskWithPostion,
                                              VirtualMachine virtualMachine);


    /**
     * 将从数据库中获取到的LabTask按照Sample和QC进行分开
     * 并且对Sample进行排序(普通任务和质检任务)
     * @param labTasks
     * @return sample和qc两个任务列表
     *
     */
    SampleAndQCTasksResult getSampleAndQCTasks(List<LabTask> labTasks);

    /**
     * 给占位的VirtualLabTask填充对应的ID, 并且将其添加到virtual_lab_tasks中, 等待后面进行update
     */

    List<HeliosLabTaskWithPostion> fillVirtualLabTasks(List<VirtualMachine> virtualMachineList,
                                                              List<LabTask> labTasks);


}
