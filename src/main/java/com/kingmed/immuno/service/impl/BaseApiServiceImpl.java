package com.kingmed.immuno.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kingmed.immuno.service.BaseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseApiServiceImpl implements BaseApiService {
    @Autowired
    private RestTemplate restTemplate;
    private String baseUrl;
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 直接返回content的字符串
     *
     * @param pathUrl
     * @param headers
     * @param body
     * @return content的字符串
     */
    @Override
    public String execute(String pathUrl, HttpHeaders headers, Object body) {
        String url =  this.baseUrl + pathUrl ;
        String jsonString = JSON.toJSONString(body);
        HttpEntity<String> entity = new HttpEntity(jsonString,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url,entity,String.class);
        return response.getBody();
    }

    /**
     * 执行并返回指定的对象类型
     * @param pathUrl
     * @param headers
     * @param body
     * @param type
     * @return 指定的对象类型
     */
    @Override
    public <RespType> RespType execAndReturn(String pathUrl, HttpHeaders headers, Object body, Class<RespType> type) {
        String content = execute(pathUrl,headers,body);
        RespType resp = JSONObject.parseObject(content, type);
        return resp;
    }
}
