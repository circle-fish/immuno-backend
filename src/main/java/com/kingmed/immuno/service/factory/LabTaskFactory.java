package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.entity.LabTestItem;
import com.kingmed.immuno.model.dataModel.dto.LabTaskDO;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class LabTaskFactory {

    //取sorted_index的最大的labtestitem,转换成labTask
    public LabTask createLabTask(KmcsTask kmcsTask, LabTestItem labTestItem){
        LabTask labTask = new LabTask();
        labTask.setBarcode(kmcsTask.getBarcode());
        labTask.setExperimentNo(kmcsTask.getExperimentNo());
        labTask.setStatus("inited");
        labTask.setBizOrgCode(kmcsTask.getBizOrgCode());
        labTask.setTaskType(0);
        labTask.setLabTestItemId(labTestItem.getId());
        //labOrder与LabTestItem的Id改为Integer类型 ？？？

//        labTask.setLabTestItemCode(labTestItem.getCode());
        labTask.setLabTestItemName(labTestItem.getName());
        labTask.setDeviceType(labTestItem.getDeviceType());
        labTask.setReagentType(labTestItem.getReagentType());

        return labTask;
    }


    public static LabTask initQcLabTask(
            LabTask labTask,
            VirtualMachine virtualMachine,
            HeliosReagent heliosReagent,
            int slideIndex,
            int wellIndex,
            String qcName,
            String operatorName)
    {

        // 设备分配时， 向VirtualSlide中添加QC任务
        // 后面用来持久化
        LabTask qcLabTask = new LabTask();

        qcLabTask.setBarcode(qcName);
        qcLabTask.setExperimentNo(qcName);
        qcLabTask.setStatus(EnumManager.LabTaskStatus.allocated.toString());
        qcLabTask.setBizOrgCode(labTask.getBizOrgCode());
        qcLabTask.setTaskType(EnumManager.LabTaskType.qc.getValue());
        qcLabTask.setResult(null);

        qcLabTask.setLabTestItemId(labTask.getLabTestItemId());
        qcLabTask.setLabTestItemName(labTask.getLabTestItemName());
        qcLabTask.setLabOrderId(labTask.getLabOrderId());
        qcLabTask.setDeviceType(labTask.getDeviceType());

        qcLabTask.setDeviceId(virtualMachine.getId());
        qcLabTask.setDeviceName(virtualMachine.getDeviceName());
        qcLabTask.setDevicePosition(virtualMachine.getId() + "-" + slideIndex + "-" + wellIndex);

        qcLabTask.setReagentType(labTask.getReagentType());
        qcLabTask.setReagentLot(heliosReagent.getBatchNo());
        //批次号
        qcLabTask = (LabTask) BaseFactory.initBaseAttribute(qcLabTask, operatorName);

        return qcLabTask;

    }

}
