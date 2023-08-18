//package com.example.producer;//package com.mq.my.producer.demo.producer;
//
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//@Component
//
//public class MyProducer {
//
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//
//    public void sendMessage(String topic, String message){
//        rocketMQTemplate.convertAndSend(topic,message);
//    }
//}
