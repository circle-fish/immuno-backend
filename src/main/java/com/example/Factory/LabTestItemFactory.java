package com.example.Factory;

import com.example.consumer.model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.entity.LabTestItem;

import java.util.ArrayList;
import java.util.List;

public class LabTestItemFactory {

    public List<LabTestItem> convertToLabTestItemList( List<KmcsTask> kmTasks)
    {
        List<LabTestItem> labTestItems = new ArrayList<>();
        for(KmcsTask kmTask :kmTasks)
        {
            LabTestItem labTestItem = convertToLabTestItem(kmTask);
            labTestItems.add(labTestItem);
        }
        return labTestItems;
    }

    public LabTestItem convertToLabTestItem(KmcsTask kmTask) {
        LabTestItem labTestItem = new LabTestItem();
        labTestItem.setCode(kmTask.getTestItemCode());//???
        labTestItem.setName(kmTask.getTestItemName());
        labTestItem.setBizOrgCode(kmTask.getBizOrgCode());

        return labTestItem;
    }

}
