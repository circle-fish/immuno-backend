package com.example.Factory;

import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.entity.LabTestItem;
import org.springframework.stereotype.Component;

@Component
public class LabTaskFactory {

    public LabTask createLabTask(KmcsTask kmcsTask, LabTestItem labTestItem){
        LabTask labTask = new LabTask();
        labTask.setBarcode(kmcsTask.getBarcode());
        labTask.setExperimentNo(kmcsTask.getExperimentNo());
        labTask.setStatus(0);
        labTask.setBizOrgCode(kmcsTask.getBizOrgCode());
        labTask.setTaskType(0);
        labTask.setLabTestItemId(labTestItem.getId());
        labTask.setLabTestItemCode(labTestItem.getCode());
        labTask.setLabTestItemName(labTestItem.getName());
        labTask.setDeviceType(labTestItem.getDeviceType());
        labTask.setReagentType(labTestItem.getReagentType());

        return labTask;
    }
}
