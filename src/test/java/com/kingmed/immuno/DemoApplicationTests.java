package com.kingmed.immuno;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.mapper.*;
import com.kingmed.immuno.model.request.AuthRequest;
import com.kingmed.immuno.model.request.UserQueryRequest;
import com.kingmed.immuno.model.response.StatusChangeResponse;
import com.kingmed.immuno.service.factory.KmcsUserFactory;
import com.kingmed.immuno.service.impl.*;
import net.minidev.asm.ex.NoSuchFieldException;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

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
    private LabTaskMapper labTaskMapper;
    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    @Autowired
    private MapperHelpper mapperHelpper;
    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;
    @Autowired
    private HeliosAiTaskMapper heliosAiTaskMapper;

    @Autowired
    private KmcsServiceImpl kmcsService;
    @Autowired
    private KmcsUserServiceImpl kmcsUserService;
    @Autowired
    private LabTaskServiceImpl labTaskService;
    @Autowired
    private BaseApiServiceImpl baseApiService;

    @Test
    public void testAny() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        System.out.println(EnumManager.LabTaskStatus.valueOf("inited").getValue());
        System.out.println(StatusChangeResponse.getStatusChangeResult(EnumManager.LabTaskStatus.inited));
        QueryWrapper<LabTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",186).last("for update");
        LabTask labTask = labTaskMapper.selectOne(queryWrapper);
        System.out.println(labTask);

    }
    @Test
    public void testMessageFlow() throws IOException, MQBrokerException, RemotingException, InterruptedException, MQClientException {


        KmcsTask kmcsTask = kmcsTaskMapper.selectById("testing@taskId--1");
        System.out.println(kmcsTask.getVersion());
        System.out.println(kmcsTask);

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

    /**
     * 对登录流程测试
     * @throws IOException
     * @throws NoSuchFieldException
     */
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

    /**
     * 登录中对缓存的用户信息测试-
     * 更新缓存用户的token
     * @throws IOException
     * @throws NoSuchFieldException
     */
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
    public void testBaseApiService() {
        baseApiService.setBaseUrl("http://kmcs-ft.kingmed.com.cn:8070/km-platform-web");
        String pathUrl = "/restful/thridAuth";
        HttpHeaders headers = new HttpHeaders();
        headers.set("serviceid", "login");
        AuthRequest authRequest = new AuthRequest(
                "K000127",
                "a123456",
                "KMCS"
        );
        String resp = baseApiService.execAndReturn(pathUrl,headers,authRequest,String.class);
        System.out.println(resp);

    }

}
