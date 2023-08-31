package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.entity.LabTestItem;
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
}
