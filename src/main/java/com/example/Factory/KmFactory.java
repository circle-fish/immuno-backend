package com.example.Factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.Model.KmAppInfo;
import com.example.Model.KmItem;
import com.example.Model.KmTask;
import com.example.TaskConfig;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KmFactory {

    public  List<KmcsTask> convert_to_kmcsTaskList(KmAppInfo kmAppInfo)
    {

        List<KmcsTask> kmcsTask_list = new ArrayList<KmcsTask>();
        int size = kmAppInfo.getItemList().size();//task 总数量
        for(int i = 0; i < kmAppInfo.getItemList().size(); i++)
            size+=kmAppInfo.getItemList().get(i).getDependTasks().size();

        for(int i = 0 ;i < kmAppInfo.getItemList().size() ;i++ )
        {
            KmItem kmitem = kmAppInfo.getItemList().get(i);
            for(int j = 0 ; j < kmitem.getDependTasks().size() + 1 ;j++) {
                KmcsTask kmcsTask = new KmcsTask();
                kmcsTask.setAppId(kmAppInfo.getApplicationId());
                kmcsTask.setBarcode(kmAppInfo.getBarcode());
                kmcsTask.setBizOrgCode(kmAppInfo.getBizOrgCode());
                kmcsTask.setExperimentNo(kmAppInfo.getItemList().get(0).getExperimentNo());
                kmcsTask.setStatus(kmitem.getStatus());
                if (j == 0) {
                    kmcsTask.setTaskId(kmitem.getTaskId());
                    kmcsTask.setTestItemCode(kmitem.getTestItemCode());
                    kmcsTask.setTestItemName(kmitem.getTestItemName());
                } else{
                    kmcsTask.setTaskId(kmitem.getDependTasks().get(j-1).getTaskId());
                    kmcsTask.setTestItemCode(kmitem.getDependTasks().get(j-1).getTestItemCode());
                    kmcsTask.setTestItemName(kmitem.getDependTasks().get(j-1).getTestItemName());
                }

                kmcsTask_list.add(kmcsTask);
            }
        }

        return kmcsTask_list;
    }

}
