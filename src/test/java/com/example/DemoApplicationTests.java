package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.model.KmAppInfo;
import com.example.entity.KmcsTask;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
