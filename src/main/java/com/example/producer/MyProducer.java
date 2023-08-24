package com.example.producer;//package com.mq.my.producer.demo.producer;

import com.example.consumer.RQConsumer;
import io.swagger.annotations.Api;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Component
@Api(tags = "生产者接口")
@RestController
@RequestMapping("/MyProducer")
public class MyProducer {


    private RocketMQTemplate rocketMQTemplate;
    public void sendMessage(String topic, String message){
        rocketMQTemplate.convertAndSend(topic,message);
    }

    @PostMapping("/send")
    public void send(@RequestBody String message) throws MQClientException, MQBrokerException, RemotingException, InterruptedException, UnsupportedEncodingException {
        //创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group2");
        //指定 NameServer 地址
        producer.setNamesrvAddr("10.20.2.167:9876");
        //初始化Producer，整个应用生命周期内只需要初始化一次
        producer.start();

        Message msg = new Message(
                    "mqtest" /* 消息主题名 */,
                    "TagA" /* 消息标签 */,
                    (message)
                            .getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
        );

            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);

        // 一旦生产者实例不再被使用则将其关闭，包括清理资源，关闭网络连接等
//        producer.shutdown();
//        String myTopic = "mqtest";
//        rocketMQTemplate.convertAndSend(myTopic,message);
//        System.out.println("Sending Mission Completed ....................");
    }
}
