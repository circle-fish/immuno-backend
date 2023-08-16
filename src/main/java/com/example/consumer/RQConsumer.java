package com.example.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.Model.KmAppInfo;
import com.example.TaskConfig;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.TaskConfig;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RocketMQMessageListener(topic= "mqtest",consumerGroup = "mqtest-consumer")
public class RQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
//        System.out.println("get message :"+message);
       System.out.println("get message : " +message);
        convert(message);
        System.out.println("Consumer convert completed........");
    }


    public void convert(String message) {

        KmAppInfo kmAppInfo = JSONObject.parseObject(message,KmAppInfo.class);

        KmFactory kmFactory = new KmFactory();
        List<KmcsTask> kmcsTasks = kmFactory.convert_to_kmcsTaskList(kmAppInfo);

        for(int i = 0; i < kmcsTasks.size(); i++)
        {
            System.out.println(JSON.toJSONString(kmcsTasks.get(i)));
        }
        System.out.println("————————————————————————————————————————————————————————————————————-————————");
    }


}
