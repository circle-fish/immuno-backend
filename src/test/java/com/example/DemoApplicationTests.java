package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.example.Factory.KmFactory;
import com.example.Model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.TaskConfig;
import com.example.consumer.RQConsumer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =  DemoApplication.class)
class DemoApplicationTests {
    @Autowired
    private MyTest myTest;
    @Autowired
    private KmFactory kmFactory;

    @Test
    void contextLoads() {
    }
    @Test
    void test_convert() {
        String message = GlobalInfo.message;

        KmAppInfo kmAppInfo = JSONObject.parseObject(message,KmAppInfo.class);

        List<KmcsTask> kmcsTasks = kmFactory.convert_to_kmcsTaskList(kmAppInfo);

        for(int i = 0; i < kmcsTasks.size(); i++)
        {
            System.out.println(JSON.toJSONString(kmcsTasks.get(i)));
        }
        System.out.println("test over ________________________________________________________________");
    }
    @Test
    void test_mq_convert()
    {



    }

}
