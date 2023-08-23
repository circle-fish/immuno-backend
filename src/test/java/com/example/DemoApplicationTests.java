package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Factory.KmFactory;
import com.example.common.MapperHelpper;
import com.example.consumer.RQConsumer;
import com.example.consumer.model.KmAppInfo;
import com.example.entity.Device;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.mapper.DeviceMapper;
import com.example.mapper.KmcsTaskMapper;

import com.example.mapper.LabTaskMapper;
import com.example.model.dataModel.ConversionResult;
import com.example.model.response.BaseResponse;
import com.example.producer.MyProducer;
import com.example.serviceimpl.KmcsTaskServiceImpl;
import com.example.serviceimpl.TaskConversionServiceImpl;
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

    @Autowired
    TaskConversionServiceImpl taskConversionService;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private MapperHelpper mapperHelpper;
    @Autowired
    private MyProducer myProducer;
    @Autowired
    private RQConsumer rqConsumer;

    @Autowired
    private LabTaskMapper labTaskMapper;

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
    public void testAutoIncrement() throws IOException {
        int i = 10;
        while (i>0) {
            LabTask labTask = new LabTask();
            labTaskMapper.insertLabTask(new LabTask());
            System.out.println(JSON.toJSONString(labTask));
            i--;
        }
    }
    @Test
    public void testMappers() throws IOException {
        Device device = new Device();
        device.setId(23L);
        deviceMapper.insertDevice(device);
        device.setDeviceName("device1");
        System.out.println(deviceMapper.selectById(23L).getDeviceName());
        device.setDeviceName("device2");
        deviceMapper.updateById(device);
        System.out.println(deviceMapper.selectById(23L).getDeviceName());
        deviceMapper.deleteById(23L);
    }

    @Test
    public void testMessageFlow() throws IOException {

        String message = read("D:\\work\\projects\\kmcs-demo\\demo2\\immuno-backend\\src\\main\\resources\\KmcsData.txt");
        myProducer.sendMessage("mqtest",message);
    }
    @Test void testMapperHelper() throws IOException, NoSuchFieldException {

        int res = mapperHelpper.upsert(kmcsTaskMapper.selectById("098f0eae-f3d0-4e7b-8e40-cdcb528dcd39"),kmcsTaskMapper);
        System.out.println(res);
    }
    @Test void testconvertKmTaskToLabTask() throws IOException, NoSuchFieldException {
        String bizOrgCode = "K010101001";
        BaseResponse<ConversionResult> response = taskConversionService.convertKmTaskToLabTask(bizOrgCode);
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }

}
