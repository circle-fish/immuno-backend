package com.example.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.consumer.model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.mapper.KmcsTaskMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RocketMQMessageListener(
        topic= "mqtest",
        consumerGroup = "mqtest-consumer")

public class RQConsumer implements RocketMQListener<String> {

    private static Logger logger = LoggerFactory.getLogger(RQConsumer.class);
    @Autowired
    private KmFactory kmFactory;

    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;

    private void convert(String message) {
        KmAppInfo kmAppInfo = JSONObject.parseObject(message, KmAppInfo.class);
        List<KmcsTask> kmcsTasks = kmFactory.convertToKmcsTaskList(kmAppInfo);
        for (KmcsTask kmcsTask : kmcsTasks) {
            kmcsTaskMapper.insertKmcsTask(kmcsTask);
        }
        logger.info("Consumer convert completed........");
    }

    @Override
    public void onMessage(String message) {
//        logger.info("get message : /n" + message);
        logger.info("consumer start converting  ..........");
        convert(message);

    }
}
