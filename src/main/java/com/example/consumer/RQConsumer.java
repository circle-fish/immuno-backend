package com.example.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.common.MapperHelpper;
import com.example.consumer.model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.mapper.KmcsTaskMapper;
import com.example.model.dataModel.ConversionResult;
import com.example.model.response.BaseResponse;
import com.example.serviceimpl.TaskConversionServiceImpl;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private MapperHelpper mapperHelpper;
    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;
    @Autowired
    private TaskConversionServiceImpl taskConversionService;

    private void convert(String message) throws NoSuchFieldException {
        KmAppInfo kmAppInfo = JSONObject.parseObject(message, KmAppInfo.class);
        List<KmcsTask> kmcsTasks = kmFactory.convertToKmcsTaskList(kmAppInfo);

        for (KmcsTask kmcsTask : kmcsTasks) {
            mapperHelpper.upsert(kmcsTask,kmcsTaskMapper);
        }
        logger.info("KmTasks saved in DB ..................................");
        logger.info("starting taskConversion :                              ");
        List<String> bizOrgCodeList = new ArrayList<>();
        for (KmcsTask kmcsTask : kmcsTasks) {
            if(!bizOrgCodeList.contains(kmcsTask.getBizOrgCode()))
                bizOrgCodeList.add(kmcsTask.getBizOrgCode());
        }
        for(String bizOrgCode :bizOrgCodeList) {
            BaseResponse<ConversionResult> response = taskConversionService.convertKmTaskToLabTask(bizOrgCode);
           logger.info(response.getCode());
           logger.info(response.getMessage());
            List<LabTask> labTasks = response.getData().getLabTasks();
           System.out.println(labTasks.size());
            for (int i = 0; i < labTasks.size(); i++)
               logger.info(JSON.toJSONString(labTasks.get(i)));
        }
        logger.info("Consumer convert completed........");
    }

    @Override
    public void onMessage(String message) {
//        logger.info("get message : /n" + message);
        logger.info("consumer start converting  ........../n");
        try {
            convert(message);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

    }
}
