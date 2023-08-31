package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTestItem;

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
