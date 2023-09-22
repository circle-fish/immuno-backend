package com.kingmed.immuno.service.impl;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.IndexCounter;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.InitVirtualMachinesResult;
import com.kingmed.immuno.model.dataModel.SampleAndQCTasksResult;
import com.kingmed.immuno.model.dataModel.TaskAllocationResult;
import com.kingmed.immuno.model.dataModel.dto.LabTaskDO;
import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.LabTaskAllocationService;
import com.kingmed.immuno.util.heliosAllocationUtils;
import io.swagger.models.auth.In;

import java.util.*;

import static com.kingmed.immuno.common.CommonConstants.QC_NAMES;

public class LabTaskAllocationServiceImpl implements LabTaskAllocationService {
    /**
     * 处理Helios的该函数用来处理Helios设备的任务分配问题
     * 在第一个版本优先解决ANA的任务分配问题
     *
     * @param  labTasks 待分配的任务列表
     * @param  devices 设备列表
     * @param  heliosReagent 试剂相信信息
     * @param  operatorName 操作者名称 (python rmy-dev 中的opter_name)
     *                      (对应 MyMetaObjectHandler中的createdBy默认值 admin)
     * @return 任务分配结果
     */
    @Override
    public TaskAllocationResult heliosTaskAllocation(List<LabTask> labTasks,
                                                                   List<Device> devices,
                                                                   HeliosReagent heliosReagent,
                                                                   String operatorName) {

        int numWells = heliosReagent.getNumWells();
        int numQc =  heliosReagent.getNumQc();
        /**
         * 取出sampleTask和qcTasks ，qcTasksForDelete取出后是待删除的任务
         * 已经生成了质控LabTask(qcTasksForInsert)，在自动分配前，需要将这些质控LabTask-qcTasksForDelete删除
         */
        List<LabTask> sampleTasks = getSampleAndQCTasks(labTasks).getSampleTasks();
        List<LabTask> qcTasksForDelete = getSampleAndQCTasks(labTasks).getQcTasks();

        List<LabTaskDO> qcTasksForInsert = new ArrayList<>();
        /**
          *这个Index只在向Slide中添加LabTask的时候才增加
          */
        IndexCounter labTaskIndex = new IndexCounter(0,sampleTasks.size());

        /**
         * 初始化设备相关信息
         */
        List<VirtualMachine> virtualMachines = initVirtualMachines(devices).getVirtualMachines();
        IndexCounter virtualMachineIndex = new IndexCounter(0,virtualMachines.size());

        /**
         * 以slide为单位进行任务的分配
         */

        while(!labTaskIndex.isMax()){
            LabTask nextLabTask = sampleTasks.get(labTaskIndex.getIndex());
            int nextLabTestItemId = nextLabTask.getLabTestItemId();

            VirtualMachine nextVirtualMachine = virtualMachines.get(virtualMachineIndex.getIndex());

            VirtualSlide virtualSlide = new VirtualSlide(0, nextLabTestItemId, numWells);

            /**
             * 设备没有qc任务时才会优先分配对应任务的项目的质控任务
             * 一个设备一次分配只能有一组质控分配任务
             */
            if (!nextVirtualMachine.hasQc()) {

                List<LabTask> qcAddToSlideList = heliosAllocationUtils.addQCToSlide(virtualSlide, QC_NAMES, numQc,
                        nextLabTask, nextVirtualMachine, heliosReagent, operatorName);

                for(LabTask qctask : qcAddToSlideList ) {
                    if(qctask != null){
                        qcTasksForInsert.add((LabTaskDO) qctask);
                        //将LabTask父类强制转换为子类???是否安全
                    }
                }
            }

            heliosAllocationUtils.addSampleToSlide(virtualSlide, nextVirtualMachine, heliosReagent, nextLabTask, labTaskIndex);

            nextVirtualMachine.addSlide(virtualSlide);
            virtualMachineIndex.resetAdd();
        }

        fillVirtualLabTasks(virtualMachines, sampleTasks);

        return new TaskAllocationResult(sampleTasks, qcTasksForInsert, qcTasksForDelete);

    }

    /**
     * 初始化虚拟的设备
     *
     * @param deviceList
     */
    @Override
    public InitVirtualMachinesResult initVirtualMachines(List<Device> deviceList) {
        List<VirtualMachine> virtualMachines = new ArrayList<>();
        Map<Integer,Device> deviceMap = new HashMap<>();
        for(Device device : deviceList){
            if(device.getDeviceType() == EnumManager.DeviceType.helios.getValue()){
                VirtualMachine virtualMachine = new VirtualMachine(device);
                virtualMachines.add(virtualMachine);
                deviceMap.put(device.getId(),device);
            }
        }

        return new InitVirtualMachinesResult(virtualMachines,deviceMap);
    }

    /**
     * 在向虚拟Slide添加样本时, 需要修改LabTask的相关属性, 后面进行Commit
     *
     * @param labTask
     * @param heliosLabTaskWithPostion
     * @param virtualMachine
     */
    @Override
    public void insertLabTaskIntoVirtualSlide(LabTask labTask, HeliosLabTaskWithPostion heliosLabTaskWithPostion, VirtualMachine virtualMachine) {
        labTask.setStatus(EnumManager.LabTaskStatus.allocated.toString());
        labTask.setDeviceId(virtualMachine.getId());
        labTask.setDeviceName(virtualMachine.getDeviceName());
        labTask.setDevicePosition(heliosLabTaskWithPostion.getDevicePosition());
        labTask.setReagentLot(heliosLabTaskWithPostion.getReagentLot());
    }

    /**
     * 将从数据库中获取到的LabTask按照Sample和QC进行分开
     * 并且对Sample进行排序(普通任务和质检任务)
     *
     * @param labTaskList
     * @return sample和qc两个任务列表
     */
    @Override
    public SampleAndQCTasksResult getSampleAndQCTasks(List<LabTask> labTaskList) {
        List<LabTask> sampleTasks = new ArrayList<>();
        List<LabTask> qcTasks = new ArrayList<>();

        for(LabTask labTask : labTaskList){
            if(labTask.getTaskType() == EnumManager.LabTaskType.normal.getValue()) {
                sampleTasks.add(labTask);
            } else if (labTask.getTaskType() == EnumManager.LabTaskType.qc.getValue()) {
                qcTasks.add(labTask);
            }else{
                throw new RuntimeException("未知的任务类型！" + labTask.getTaskType());
            }

        }
        /**
         * 对常规任务根据项目Id和实验编号排序
         */
        //???不规定result的值是否会有问题-待测试编译器优化后的排序写法
        Collections.sort(
                sampleTasks,
                Comparator.comparingInt(LabTask::getLabTestItemId).
                thenComparing(LabTask::getExperimentNo)
        );
        return new SampleAndQCTasksResult(sampleTasks,qcTasks);
    }

    /**
     * 给占位的VirtualLabTask填充对应的设备ID, 并且将其添加到virtual_lab_tasks中, 等待后面进行update
     *
     * @param virtualMachineList
     * @param labTasks
     */
    @Override
    public List<HeliosLabTaskWithPostion> fillVirtualLabTasks(List<VirtualMachine> virtualMachineList, List<LabTask> labTasks) {
        List<HeliosLabTaskWithPostion> virtualLabTasks = new ArrayList<>();
        int labTaskIndex = 0;

        for(VirtualMachine virtualMachine :virtualMachineList){
            for(VirtualSlide virtualSlide : virtualMachine.getVirtualSlides()){
                for(SampleBase virtualLabTask : virtualSlide.getVirtualLabTasks()){
                    if(virtualLabTask.getTaskType() == EnumManager.LabTaskType.normal.getValue()){
                        if(virtualLabTask instanceof HeliosLabTaskWithPostion) {
                            /**
                             * 以防万一做个类型检查???若如果不是怎么办-跳过???
                             */
                            insertLabTaskIntoVirtualSlide(
                                    labTasks.get(labTaskIndex),
                                    (HeliosLabTaskWithPostion) virtualLabTask,
                                    virtualMachine
                            );
                            labTaskIndex += 1;
                            virtualLabTasks.add((HeliosLabTaskWithPostion) virtualLabTask);
                        }
                    }
                }
            }
        }

        return virtualLabTasks;
    }
}
