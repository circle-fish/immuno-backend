package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HeliosLabTaskWithPositionFactory {

    public static HeliosLabTaskWithPostion setAttributesByLabTask(LabTask labTask){
        HeliosLabTaskWithPostion heliosLabTaskWithPostion = new HeliosLabTaskWithPostion();

        heliosLabTaskWithPostion.setTaskType(labTask.getTaskType());
        heliosLabTaskWithPostion.setExperimentNo(labTask.getExperimentNo());
        heliosLabTaskWithPostion.setLabTestItemId(labTask.getLabTestItemId());
        heliosLabTaskWithPostion.setLabTestItemName(labTask.getLabTestItemName());
        heliosLabTaskWithPostion.setBizOrgCode(labTask.getBizOrgCode());
        heliosLabTaskWithPostion.setLabOrderId(labTask.getLabOrderId());
        heliosLabTaskWithPostion.setStatus(EnumManager.LabTaskStatus.allocated.toString());
        return heliosLabTaskWithPostion;
    }

    /**
     *占位时初始化, 后面在分配设备时更新id, barcode, version等信息
     *最后回传给前端
     */
    public static HeliosLabTaskWithPostion initByNormLabTask(LabTask labTask,
                                                      VirtualMachine virtualMachine,
                                                      HeliosReagent heliosReagent,
                                                      int slideIndex,
                                                      int wellIndex
                                                      )
    {
        HeliosLabTaskWithPostion heliosLabTaskWithPostion = setAttributesByLabTask(labTask);


        heliosLabTaskWithPostion.setDeviceId(virtualMachine.getId());
        heliosLabTaskWithPostion.setDevicePosition(String.format("%d-%d-%d", virtualMachine.getId(), slideIndex, wellIndex));
        heliosLabTaskWithPostion.setDeviceName(virtualMachine.getDeviceName());
        heliosLabTaskWithPostion.setReagentId(heliosReagent.getId());
        heliosLabTaskWithPostion.setReagentLot(heliosReagent.getBatchNo());
        heliosLabTaskWithPostion.setSlideIndex(slideIndex);
        heliosLabTaskWithPostion.setWellIndex(wellIndex);


        heliosLabTaskWithPostion.setStatus(EnumManager.LabTaskStatus.allocated.toString());

        return heliosLabTaskWithPostion;
    }
    public static HeliosLabTaskWithPostion initByQCLabTask(LabTask qcLabTask, Map<Integer, Device> deviceMap){
        String []indexStrArray = qcLabTask.getDevicePosition().split("-");
        int slideIndex = Integer.parseInt(indexStrArray[1]);
        int wellIndex = Integer.parseInt(indexStrArray[2]);
        String deviceName = deviceMap.get(qcLabTask.getDeviceId()).getDeviceName();

        HeliosLabTaskWithPostion heliosLabTaskWithPostion = setAttributesByLabTask(qcLabTask);
        heliosLabTaskWithPostion.setSlideIndex(slideIndex);
        heliosLabTaskWithPostion.setWellIndex(wellIndex);
        heliosLabTaskWithPostion.setDeviceName(deviceName);
        heliosLabTaskWithPostion.setReagentLot(qcLabTask.getReagentLot());
        heliosLabTaskWithPostion.setDevicePosition(String.format("%d-%d-%d",qcLabTask.getDeviceId(), slideIndex, wellIndex));

        heliosLabTaskWithPostion.setStatus(EnumManager.LabTaskStatus.allocated.toString());
        return heliosLabTaskWithPostion;
    }

    public static List<HeliosLabTaskWithPostion> initListByQCLabTask(List<LabTask> qcLabTasks, Map<Integer,Device> deviceMap){

        List<HeliosLabTaskWithPostion> heliosLabTaskWithPostions = new ArrayList<>();
        for(LabTask qcLabTask : qcLabTasks){
            heliosLabTaskWithPostions.add(initByQCLabTask(qcLabTask,deviceMap));
        }
        return heliosLabTaskWithPostions;
    }

    /**
     * 设备分配完成后, 修改占位HeliosLabTaskWithPostion的相关属性
     * 需要修改的属性:
     */

    public HeliosLabTaskWithPostion updateByLabTask(HeliosLabTaskWithPostion virtualLabTask,
                                                    LabTask labTask){
        virtualLabTask.setId(labTask.getId());
        virtualLabTask.setBarcode(labTask.getBarcode());
        virtualLabTask.setExperimentNo(labTask.getExperimentNo());
        virtualLabTask.setVersion(labTask.getVersion());
        return virtualLabTask;
    }
}
