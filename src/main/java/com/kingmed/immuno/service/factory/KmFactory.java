package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.consumer.model.KmAppInfo;
import com.kingmed.immuno.consumer.model.KmDependTask;
import com.kingmed.immuno.consumer.model.KmItem;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KmFactory {

    public KmcsTask updatedWhenConverted(LabTask labTask ,KmcsTask kmcsTask) {
        if(labTask!=null) {
            kmcsTask.setLabTaskId(labTask.getId());
            kmcsTask.setStatus(1);
        }
        return kmcsTask;
    }


    private KmcsTask convertKmDependTaskToKmcsTask(
            KmDependTask kmDependTask,
            String applicationId,
            String barcode,
            String bizOrgCode,
            String experimentNo
    ){

        KmcsTask kmcsTask = new KmcsTask();
        kmcsTask.setTaskId(kmDependTask.getTaskId());
        kmcsTask.setAppId(applicationId);
        kmcsTask.setBarcode(barcode);
        kmcsTask.setBizOrgCode(bizOrgCode);
        kmcsTask.setExperimentNo(experimentNo);

        kmcsTask.setTestItemCode(kmDependTask.getTestItemCode());
        kmcsTask.setTestItemName(kmDependTask.getTestItemName());

        kmcsTask.setLabTaskId(0);
        kmcsTask.setStatus(0);

        return kmcsTask;
    }

    private List<KmcsTask> convertKmItemToKmcsTask(
            KmItem kmItem,
            String applicationId,
            String barcode,
            String bizOrgCode
    ){
        List<KmcsTask> kmcsTaskList = new ArrayList<>();

        KmcsTask kmcsTask = new KmcsTask();
        kmcsTask.setTaskId(kmItem.getTaskId());
        kmcsTask.setAppId(applicationId);
        kmcsTask.setBarcode(barcode);
        kmcsTask.setBizOrgCode(bizOrgCode);
        kmcsTask.setExperimentNo(kmItem.getExperimentNo());

        kmcsTask.setTestItemCode(kmItem.getTestItemCode());
        kmcsTask.setTestItemName(kmItem.getTestItemName());

        kmcsTask.setLabTaskId(0);
        kmcsTask.setStatus(0);

        kmcsTaskList.add(kmcsTask);

        for (KmDependTask kmDependTask : kmItem.getDependTasks()){
            kmcsTaskList.add(convertKmDependTaskToKmcsTask(
                    kmDependTask,
                    applicationId,
                    barcode,
                    bizOrgCode,
                    kmItem.getExperimentNo()
            ));
        }
        return  kmcsTaskList;
    }

    public  List<KmcsTask> convertToKmcsTaskList(KmAppInfo kmAppInfo)
    {
        String applicationId = kmAppInfo.getApplicationId();
        String barcode = kmAppInfo.getBarcode();
        String bizOrgCode = kmAppInfo.getBizOrgCode();
        List<KmcsTask> kmcsTaskList = new ArrayList<>();

        for (KmItem kmItem : kmAppInfo.getItemList()){
            kmcsTaskList.addAll(convertKmItemToKmcsTask(
                    kmItem,
                    applicationId,
                    barcode,
                    bizOrgCode
            ));
        }

        return kmcsTaskList;
    }



}
