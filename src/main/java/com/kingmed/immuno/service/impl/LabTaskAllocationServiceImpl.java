package com.kingmed.immuno.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.IndexCounter;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.DeviceMapper;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.model.dataModel.InitVirtualMachinesResult;
import com.kingmed.immuno.model.dataModel.ModificationResult;
import com.kingmed.immuno.model.dataModel.SampleAndQCTasksResult;
import com.kingmed.immuno.model.dataModel.TaskAllocationResult;
import com.kingmed.immuno.model.dataModel.dto.LabTaskDO;
import com.kingmed.immuno.model.dataModel.dto.SampleBase;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.LabTaskAllocationService;
import com.kingmed.immuno.util.HeliosAllocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.kingmed.immuno.common.CommonConstants.QC_NAMES;
@Service
public class LabTaskAllocationServiceImpl implements LabTaskAllocationService {
    @Autowired
    private MapperHelpper mapperHelpper;
    @Autowired
    private LabTaskMapper labTaskMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    /**
     * 缓存virtualmachine，方便excel导出
     */
    private static Set<VirtualMachine> virtualMachineList = new HashSet<>();
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
                                                                   String operatorName)
    {
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

                List<LabTask> qcAddToSlideList = HeliosAllocationUtils.addQCToSlide(virtualSlide, QC_NAMES, numQc,
                        nextLabTask, nextVirtualMachine, heliosReagent, operatorName);

                for(LabTask qcTask : qcAddToSlideList ) {
                    if(qcTask != null){
                        LabTaskDO qcTaskDO = new LabTaskDO(qcTask);
                        qcTasksForInsert.add(qcTaskDO);
                        //无法强制转换为子类，通过创建对象实例赋值
                    }
                }
            }
            HeliosAllocationUtils.addSampleToSlide(virtualSlide, nextVirtualMachine, heliosReagent, nextLabTask, labTaskIndex);

            nextVirtualMachine.addSlide(virtualSlide);
            virtualMachineIndex.resetAdd();
        }

        fillVirtualLabTasks(virtualMachines, sampleTasks);
        virtualMachineList.addAll(virtualMachines);
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
            if(device.getDeviceType() == EnumManager.DeviceType.helios.getValue() && device.getStatus() == 1 ){
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

    /**
     * 数据库更新：
     * device的 labTestItemIds 添加 、
     * 任务LabTask的添加与删除以及与其绑定的设备试剂等信息的更新
     * @param result
     * @return 更新后的设备和任务列表的元组
     */
    @Transactional
    public ModificationResult DBModification(TaskAllocationResult result){
        List<LabTask> sampleTasks = result.getSampleTasks();
        List<LabTaskDO> qcTasksForInsert = result.getQcTasksForInsert();
        List<LabTask> qcTasksForDelete = result.getQcTasksForDelete();

        List<LabTask> retTasks = new ArrayList<>();
        List<Device> retDevices = new ArrayList<>();
        List<VirtualMachine> retVirtualMachines = new ArrayList<>();
        /**
         * 更新任务列表
         */
        for(LabTask sample : sampleTasks){
            mapperHelpper.upsert(sample,labTaskMapper);
            retTasks.add(sample);
        }
        for(LabTask qcTask : qcTasksForDelete){
            labTaskMapper.deleteByid(qcTask.getId());
        }
        for(LabTaskDO qcTask : qcTasksForInsert){
            LabTask labTask = new LabTask();
            BeanUtil.copyProperties(qcTask,labTask);
            //ID是否也被赋值???
            mapperHelpper.upsert(labTask,labTaskMapper);
            retTasks.add(labTask);
        }
        /**
         * 更新对应设备的项目id列表
         */
        for(VirtualMachine virtualMachine : virtualMachineList){
            Device device = deviceMapper.selectById(virtualMachine.getId());
            UpdateWrapper<Device> deviceUpdateWrapper = new UpdateWrapper<>();
            System.out.println("虚拟设备结构体的项目id表"+virtualMachine.getLabTestItemIdList());
            System.out.println(virtualMachine.getLabTestItemIds());
            deviceUpdateWrapper.set("lab_test_item_ids",virtualMachine.getLabTestItemIds());
            deviceMapper.update(device,deviceUpdateWrapper);
            retDevices.add(device);
            retVirtualMachines.add(virtualMachine);
        }
        /**
         *完成数据库中更新将缓存清空
         */
//        virtualMachineList.clear();
        return new ModificationResult(retDevices,retTasks,retVirtualMachines);
    }

    public Set<VirtualMachine> getVirtualMachineList() {
        return virtualMachineList;
    }
    public void clearVirtualMachineList(){
        virtualMachineList.clear();
    }
}
