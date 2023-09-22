package com.kingmed.immuno;

import com.alibaba.fastjson.JSON;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.consumer.RQConsumer;
import com.kingmed.immuno.entity.Device;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.DeviceMapper;
import com.kingmed.immuno.mapper.KmcsTaskMapper;
import com.kingmed.immuno.mapper.KmcsUserMapper;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.model.dataModel.ConversionResult;
import com.kingmed.immuno.model.producer.MyProducer;
import com.kingmed.immuno.model.request.AuthRequest;
import com.kingmed.immuno.model.request.UserQueryRequest;
import com.kingmed.immuno.model.response.BaseResponse;
import com.kingmed.immuno.service.factory.KmcsUserFactory;
import com.kingmed.immuno.service.impl.KmcsServiceImpl;
import com.kingmed.immuno.service.impl.KmcsUserServiceImpl;
import com.kingmed.immuno.service.impl.LabTaskServiceImpl;
import com.kingmed.immuno.service.impl.TaskConversionServiceImpl;
import com.kingmed.immuno.util.JwtUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Autowired
    private KmcsUserFactory kmcsUserFactory;
    @Autowired
    TaskConversionServiceImpl taskConversionService;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private KmcsUserMapper kmcsUserMapper;
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

    @Autowired
    private KmcsServiceImpl kmcsService;
    @Autowired
    private KmcsUserServiceImpl kmcsUserService;
    @Autowired
    private LabTaskServiceImpl labTaskService;

    public String read(String addr) throws IOException {
        FileReader fileReader = new FileReader(addr);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
            免疫荧光-开发
            免疫荧光-开发
            外部
            外部
        }

        bufferedReader.close();
        fileReader.close();

        return stringBuilder.toString();
    }

    @Test
    public void testAny() throws IOException {

        List<LabTask> initlabTasks =  labTaskService.initTasksForInterFace().get(0);
        List<LabTask> unhandledTasks = labTaskService.initTasksForInterFace().get(1);
        System.out.println(initlabTasks.get(0));
        System.out.println(unhandledTasks.size());

    }

    @Test
    public void testMessageFlow() throws IOException, MQBrokerException, RemotingException, InterruptedException, MQClientException {

//        String message = read("D:\\work\\projects\\kmcs-demo\\demo2\\immuno-backend\\src\\main\\resources\\KmcsData.txt");
//        myProducer.send(message);
        KmcsTask kmcsTask = kmcsTaskMapper.selectById("testing@taskId--1");
        System.out.println(kmcsTask.getVersion());
//        kmcsTask.setUpdatedBy("testing-updater");
//        kmcsTaskMapper.updateById(kmcsTask);
//        System.out.println(kmcsTask.getVersion());
        System.out.println(kmcsTask.toString());

    }

    @Test
    public void testMapperHelper() throws IOException, NoSuchFieldException {

        int res = mapperHelpper.upsert(kmcsTaskMapper.selectById("098f0eae-f3d0-4e7b-8e40-cdcb528dcd39"), kmcsTaskMapper);
        System.out.println(res);
    }

    @Test
    public void testPostRequest() {
        String url = "http://kmcs-ft.kingmed.com.cn:8070/km-platform-web/restful/thridAuth";
        AuthRequest authRequest = new AuthRequest(
                "K000127",
                "a123456",
                "KMCS"
        );
        HttpHeaders headers = new HttpHeaders();
        headers.set("serviceid", "login");

        ResponseEntity<String> response = kmcsService.getAuthResponseFromKmcs(authRequest, url, headers);
        System.out.println(JSON.toJSONString(response.getBody()));
    }

    @Test
    public void testAuthProcess() throws IOException, NoSuchFieldException {
        KmcsUser testUser = kmcsUserFactory.createKmcsUser(
                "K000127",
                "a123456",
                "-testing@bizOrgCode1",
                "",
                new Date());
        testUser.setToken(kmcsService.getAccountIdFromKmcs(testUser));
        //token是等价于查询的accountId???

        mapperHelpper.upsert(testUser, kmcsUserMapper);

        UserQueryRequest userQueryRequest = new UserQueryRequest(
                "K000127",
                "a123456",
                "-testing@bizOrgCode1");

        KmcsUser retUser = kmcsUserService.LoginQuery(userQueryRequest);

        Assert.assertEquals(testUser.getId(), retUser.getId());
        System.out.println("更新后token返回retUser的token：" + retUser.getToken());
        System.out.println("更新后token返回testUser的token：" + testUser.getToken());

        String testOrgCode = kmcsService.getOrgCodeFromKmcs(testUser);
        String retOrgCode = kmcsService.getOrgCodeFromKmcs(retUser);

        Assert.assertEquals(testOrgCode, retOrgCode);
        System.out.println("用户岗位名称 " + testOrgCode);
    }

    @Test
    public void testLoginQuery() throws IOException, NoSuchFieldException {
        UserQueryRequest userQueryRequest = new UserQueryRequest(
                "K000127",
                "a123456",
                "-testing@bizOrgCode1");

        KmcsUser retUser = kmcsUserService.LoginQuery(userQueryRequest);
        /**
         * 1.查找缓存中有无该用户
         * 2.没有则新建
         * 3.比较二者的id和token
         */
        KmcsUser testUser = null;
        if (retUser != null) {
            testUser = retUser;
        } else {
            testUser = kmcsUserFactory.createKmcsUser(
                    "K000127",
                    "a123456",
                    "-testing@bizOrgCode1",
                    "",
                    new Date());
            testUser.setToken(kmcsService.updateToken(testUser));
            mapperHelpper.upsert(testUser, kmcsUserMapper);
        }

        Assert.assertEquals(testUser.getId(), retUser.getId());
        System.out.println("更新后token返回retUser的token：" + retUser.getToken());
        System.out.println("更新后token返回testUser的token：" + testUser.getToken());

    }

    @Test
    public void testConvertKmTaskToLabTask() throws IOException, NoSuchFieldException {
        List<String> bizOrgCodes = kmcsTaskMapper.selectALlByBizOrgCode();
        //默认先设为String 之后改为泛型--输列名获取参数值

        System.out.println(bizOrgCodes);
        for (String bizOrgCode : bizOrgCodes) {
            BaseResponse<ConversionResult> response = taskConversionService.convertKmTaskToLabTask(bizOrgCode);
            if (response.getData() == null) {
                continue;
            }
            System.out.println(response.getCode());
            System.out.println(response.getMessage());
            List<LabTask> labTasks = response.getData().getLabTasks();
            System.out.println(labTasks.size());
            for (int i = 0; i < labTasks.size(); i++)
                System.out.println(JSON.toJSONString(labTasks.get(i)));
        }
        System.out.println("convertion completed .................................................");
    }

}
