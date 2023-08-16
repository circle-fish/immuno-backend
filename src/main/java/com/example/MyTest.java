package com.example;

import com.alibaba.fastjson.*;
import com.example.entity.KmcsTask;
import com.alibaba.fastjson.JSON;
import com.example.entity.LabTask;
import com.example.TaskConfig;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MyTest {
    public void convert(String message) {

        JSONObject conf_obj =  JSONObject.parseObject(message);
        JSONArray itemList_obj = conf_obj.getJSONArray("itemList");
        String itemList = itemList_obj.get(0).toString();
        JSONArray dependTasks_obj = itemList_obj.getJSONObject(0).getJSONArray("dependTasks");
        TaskConfig taskConfig = JSONObject.parseObject(message,TaskConfig.class);
        //成功转换
        KmcsTask kmcsTask = JSONObject.parseObject(itemList,KmcsTask.class);

        List<LabTask> lab_tasks = new ArrayList<>();
        lab_tasks.add(JSONObject.parseObject(itemList,LabTask.class));
        //first labtask

        for(int i = 0; i < dependTasks_obj.size(); i++)
        {
            LabTask labTask = JSONObject.parseObject(dependTasks_obj.get(i).toString(),LabTask.class);
            lab_tasks.add(labTask);
        }
        for (int i = 0; i < lab_tasks.size(); i++) {
            lab_tasks.get(i).setExperimentNo(lab_tasks.get(0).getExperimentNo());
            taskConfig.convert(lab_tasks.get(i));
//            System.out.println(JSON.toJSONString(lab_tasks.get(i)));
            //        convert object to JSONString
        }
        taskConfig.convert(kmcsTask);


//
//        System.out.println(JSON.toJSONString(taskConfig));

    }

}