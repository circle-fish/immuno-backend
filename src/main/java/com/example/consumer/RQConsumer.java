package com.example.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.consumer.model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.mapper.KmcsTaskMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RocketMQMessageListener(
        topic= "mqtest",
        consumerGroup = "mqtest-consumer")

public class RQConsumer implements RocketMQListener<String> {

    @Autowired
    private KmFactory kmFactory;

    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;

    private void convert(String message) {
        KmAppInfo kmAppInfo = JSONObject.parseObject(message, KmAppInfo.class);
        List<KmcsTask> kmcsTasks = kmFactory.convertToKmcsTaskList(kmAppInfo);
        for (KmcsTask kmcsTask : kmcsTasks) {
            kmcsTaskMapper.insert(kmcsTask);
        }
    }

    @Override
    public void onMessage(String message) {
        //System.out.println("get message :"+message);
        System.out.println("get message : " +message);
        convert(message);
        System.out.println("Consumer convert completed........");
    }
}
