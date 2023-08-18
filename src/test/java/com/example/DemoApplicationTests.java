package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.consumer.RQConsumer;
import com.example.consumer.model.KmAppInfo;
import com.example.entity.KmcsTask;
import com.example.mapper.KmcsTaskMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =  DemoApplication.class)
class DemoApplicationTests {

//    @Autowired
//    private MyProducer myProducer;
    @Autowired
    private RQConsumer rqConsumer;

    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;

    public String read(String addr) throws IOException {
        FileReader fileReader = new FileReader(addr);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }

        bufferedReader.close();
        fileReader.close();

        String fileContent = stringBuilder.toString();
        return fileContent;
    }
    @Test
    public void testKmscTaskMapper() throws IOException {

        KmcsTask kmcsTask = kmcsTaskMapper.selectById("098f0eae-f3d0-4e7b-8e40-cdcb528dcd39");
        int del_res = kmcsTaskMapper.deleteByTaskId("098f0eae-f3d0-4e7b-8e40-cdcb528dcd39");
        int result = kmcsTaskMapper.insertKmcsTask(kmcsTaskMapper.selectById("098f0eae-f3d0-4e7b-8e40-cdcb528dcd39"));
        int up_res = kmcsTaskMapper.updateKmcsTask(kmcsTask);
        System.out.println("test result:" + del_res + result + "/n" + JSON.toJSONString(kmcsTask));

    }

    @Test
    public void testMessageFlow() throws IOException {

        String message = read("KmcsData.txt");
//        myProducer.sendMessage("mqtest",message);
        rqConsumer.onMessage(message);
    }
}
