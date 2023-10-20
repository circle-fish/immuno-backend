package com.kingmed.immuno.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;

import java.lang.reflect.Type;

public interface BaseApiService {
    /**
     * 直接返回content的字符串
     * @param pathUrl
     * @param headers
     * @param body
     * @return content的字符串
     */
    String execute(String pathUrl, HttpHeaders headers,Object body);

    <RespType> RespType execAndReturn(String pathUrl, HttpHeaders headers, Object body, Class<RespType> type);
}
