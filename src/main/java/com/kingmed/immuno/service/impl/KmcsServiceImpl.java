package com.kingmed.immuno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.domain.TokenRequest;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.mapper.KmcsUserMapper;
import com.kingmed.immuno.service.KmcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;


public class KmcsServiceImpl implements KmcsService {

    /*
     *对应子公司的用户信息缓存
     */
    private Map<String,KmcsUser> userMap;
    /*
     *访问外部接口的工具类
     */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    /*
     *获取Token字符串的基地址
     */
    private String baseUrl = "";

    /**
     * 获取KmcsUser的Token
     *
     * @param kmcsUser 账号 password 密码
     * @return boolean 验证结果
     */

    @Override
    public String getTokenFromKmcs(KmcsUser kmcsUser) {

        String url  = this.baseUrl +
                "/esb/pluginConfigEsbCtrl/login.json";
        //？？参考python版吧把返回字符串转json取出result中token ： resp["result"]["token"]

        TokenRequest tokenRequest = new TokenRequest(
                kmcsUser.getUsername(),
                kmcsUser.getPassword(),
                kmcsUser.getBizOrgCode()
        );

        String header = "{\n" +
                "            \"targetServiceCode\": \"LB.PCM.LOGIN\"\n" +
                "        }";
        //headers 问题 该如何指定消息头???
        ResponseEntity<String> response  = restTemplate.postForEntity(url,tokenRequest,String.class,header);

        /*
        *检测返回的状态 - 200 Ok
        * 然后检测body中的errorCode 是否为 ”1“ - 不为 ”1“ 则抛出异常
         */

        if(response.getStatusCode() != HttpStatus.OK){
            throw new ServiceException("请求失败 ！；无法访问到对应的Kmcs系统接口url: "+url
            +"响应消息头:" + response.getHeaders());
        }

        JSONObject responseJsonBody = JSON.parseObject(response.getBody());
        String errorCode  = responseJsonBody.getString("errorCode");

        if(errorCode != "1") {
            throw new ServiceException("请求失败 ！；无法访问到对应的Kmcs系统接口url: "+url
            +"/n响应消息体"+responseJsonBody + "/n错误代码："+errorCode);
        }

        JSONObject  resultJsonObject= JSON.parseObject(responseJsonBody.getString("result"));
        String token = resultJsonObject.getString("token");

        return token;

    }

    /**
     * 更新User的Token并返回该token
     *
     * @param kmcsUser 用户
     * @return String 新Token
     */
    @Transactional
    @Override
    public String updateToken(KmcsUser kmcsUser) {


        String token = getTokenFromKmcs(kmcsUser);

        String bizOrgCode = kmcsUser.getBizOrgCode();
        Date regTime = kmcsUser.getRegTime();

        /*
        * 从数据库中查找对应子公司的User并判断是否过期
         */
        try {
            QueryWrapper<KmcsUser> kmcsUserWrapper = new QueryWrapper<KmcsUser>();
            kmcsUserWrapper.eq("biz_org_code",bizOrgCode);
            kmcsUser = kmcsUserMapper.selectOne(kmcsUserWrapper);
            //??filter().first选取到的第一位如何排序的,
            // 形参按值传递，对副本kmcsUser修改
            if(kmcsUser == null){
                throw new ServiceException("在数据表中没有查询到子公司为" + bizOrgCode + "的用户！");
            }
            kmcsUser.setToken(token);
            kmcsUser.setRegTime(regTime);
            kmcsUserMapper.updateById(kmcsUser);

        }catch (Exception e){
            e.printStackTrace();
        }
        userMap.put(bizOrgCode,kmcsUser);

        return token;
    }
}
