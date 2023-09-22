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

    public HeliosLabTaskWithPostion setAttributesByLabTask(LabTask labTask){
        HeliosLabTaskWithPostion heliosLabTaskWithPostion = new HeliosLabTaskWithPostion();

        heliosLabTaskWithPostion.setTaskType(labTask.getTaskType());
        heliosLabTaskWithPostion.setLabTestItemId(labTask.getLabTestItemId());
        heliosLabTaskWithPostion.setLabTestItemName(labTask.getLabTestItemName());
        heliosLabTaskWithPostion.setBizOrgCode(labTask.getBizOrgCode());
        heliosLabTaskWithPostion.setLabOrderId(labTask.getLabOrderId());
        heliosLabTaskWithPostion.setStatus(EnumManager.LabTaskStatus.allocated.toString());
        return heliosLabTaskWithPostion;
    }
    public HeliosLabTaskWithPostion initByNormLabTask(LabTask labTask,
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
        heliosLabTaskWithPostion.setReagentLot(heliosReagent.getBatchNo());
        heliosLabTaskWithPostion.setSlideIndex(slideIndex);
        heliosLabTaskWithPostion.setWellIndex(wellIndex);


        heliosLabTaskWithPostion.setStatus(EnumManager.LabTaskStatus.allocated.toString());

        return heliosLabTaskWithPostion;
    }
    public HeliosLabTaskWithPostion initByQCLabTask(LabTask qcLabTask, Map<Integer, Device> deviceMap){
        String []indexStrArray = qcLabTask.getDevicePosition().split("-");
        int slideIndex = Integer.parseInt(indexStrArray[1]);
        int wellIndex = Integer.parseInt(indexStrArray[2]);
        String deviceName = deviceMap.get(qcLabTask.getDeviceId()).getDeviceName();

        HeliosLabTaskWithPostion heliosLabTaskWithPostion = setAttributesByLabTask(qcLabTask);
        heliosLabTaskWithPostion.setSlideIndex(slideIndex);
        heliosLabTaskWithPostion.setWellIndex(wellIndex);
        heliosLabTaskWithPostion.setDeviceName(deviceName);

        return heliosLabTaskWithPostion;
    }

    public List<HeliosLabTaskWithPostion> initListByQCLabTask(List<LabTask> qcLabTasks, Map<Integer,Device> deviceMap){

        List<HeliosLabTaskWithPostion> heliosLabTaskWithPostions = new ArrayList<>();
        for(LabTask qcLabTask : qcLabTasks){
            heliosLabTaskWithPostions.add(initByQCLabTask(qcLabTask,deviceMap));
        }
        return heliosLabTaskWithPostions;
    }

}
