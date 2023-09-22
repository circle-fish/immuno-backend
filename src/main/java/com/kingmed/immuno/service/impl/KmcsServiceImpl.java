package com.kingmed.immuno.service.impl;

import cn.hutool.json.ObjectMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.config.GlobalConfig;
import com.kingmed.immuno.model.request.AuthRequest;
import com.kingmed.immuno.entity.KmcsUser;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.mapper.KmcsUserMapper;
import com.kingmed.immuno.service.KmcsService;
import com.kingmed.immuno.util.DateTimeUtil;
import com.kingmed.immuno.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class KmcsServiceImpl implements KmcsService {

    /*
     *对应子公司的用户信息缓存
     */
    private Map<String,KmcsUser> userMap = new HashMap<>();
    /*
     *访问外部接口的工具类
     */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KmcsUserMapper kmcsUserMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MapperHelpper mapperHelpper;
    /*
     *获取Token字符串的基地址
     */
    private String baseUrl = "";
    /**
     * 初始化访问的url
     * */
    private void initUrl(EnumManager.Environment env){
        GlobalConfig globalConfig = new GlobalConfig(env);
        this.baseUrl = globalConfig.getKmcsPlatformUrl();
    }
    /**
     * 获取KmcsUser的Token
     *
     * @param kmcsUser 账号 password 密码
     * @return boolean 验证结果
     */

    @Override
    public String getTokenFromKmcs(KmcsUser kmcsUser) {
        initUrl(EnumManager.Environment.FT);
        String url  = this.baseUrl +
                "/esb/pluginConfigEsbCtrl/login.json";
        //？？参考python版吧把返回字符串转json取出result中token ： resp["result"]["token"]

        AuthRequest authRequest = new AuthRequest(
                kmcsUser.getUsername(),
                kmcsUser.getPassword(),
                "KMCS"
        );

        String header = "{\n" +
                "            \"targetServiceCode\": \"LB.PCM.LOGIN\"\n" +
                "        }";
        //headers 问题 该如何指定消息头???
        ResponseEntity<String> response  = restTemplate.postForEntity(url, authRequest,String.class,header);

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

//        String token = getTokenFromKmcs(kmcsUser);
//        -先用自生成的testtoken ???
        if(kmcsUser == null){
            throw new ServiceException("用户为空!");
        }
        String token = jwtUtil.createToken(kmcsUser);
        String bizOrgCode = kmcsUser.getBizOrgCode();
        Date regTime = kmcsUser.getRegTime();
        /*
        * 从数据库中查找对应子公司的User并判断是否过期
        * 过期则更新为今日的24点
        /*
        *用户过期，重新设置regTime为今天的24点
         */
        if(regTime.compareTo(DateTimeUtil.getStNow()) < 0){
            kmcsUser.setRegTime(DateTimeUtil.getEdNow());
        }
        kmcsUser.setToken(token);

        mapperHelpper.upsert(kmcsUser,kmcsUserMapper);
        userMap.put(bizOrgCode,kmcsUser);

        return token;
    }
    /**
     * 1.从子公司登录过的用户的缓存中查找token
     * 2.找不到再去数据库找
     * 3.调用第三方接口
     * @param  bizOrgCode 子公司代码
     * @return String 新Token
     */
    public String getToken(String bizOrgCode){

        KmcsUser kmcsUser = userMap.get(bizOrgCode);

        if(kmcsUser != null ){
            Date regTime = kmcsUser.getRegTime();
            if(regTime.compareTo(DateTimeUtil.getStNow()) > 0 ){
                return kmcsUser.getToken();
            }else{
                return updateToken(kmcsUser);
            }
        }
        else{
            /**
             * 已存在 - 对应子公司中user取第一个用户的token进行返回 ???
             */
            QueryWrapper<KmcsUser> kmcsUserQueryWrapper = new QueryWrapper<>();
            kmcsUserQueryWrapper.eq("biz_org_code",bizOrgCode);
            kmcsUser = kmcsUserMapper.selectList(kmcsUserQueryWrapper).get(0);
            Date regTime = kmcsUser.getRegTime();

            if(regTime!=null && regTime.compareTo(DateTimeUtil.getStNow()) > 0 ){
                userMap.put(bizOrgCode,kmcsUser);
                return kmcsUser.getToken();
                //是否存在token为空的情况??? - 初始化kmcsUser需要带token
            }
            return updateToken(kmcsUser);
        }
    }
    /*
    * 调用第三方登录接口得到accountId
     */
    @Override
    public String getAccountIdFromKmcs(KmcsUser kmcsUser) {
        initUrl(EnumManager.Environment.FT);
        String url  = this.baseUrl + "/restful/thridAuth";
        //？？参考python版吧把返回字符串转json取出result中token ： resp["result"]["token"]

        AuthRequest authRequest = new AuthRequest(
                kmcsUser.getUsername(),
                kmcsUser.getPassword(),
                "KMCS"
        );
        HttpHeaders header = new HttpHeaders();
        header.set("serviceid","login");


        ResponseEntity<String> response  = getAuthResponseFromKmcs(authRequest,url,header);

        JSONObject responseJsonBody = JSON.parseObject(response.getBody());

        JSONObject  resultJsonObject= JSON.parseObject(responseJsonBody.getString(""));
        JSONArray accountArray = JSONArray.parseArray(resultJsonObject.getString("accounts"));
        if(accountArray.isEmpty()) {
            throw new ServiceException(
                    "accounts 信息为空! 没有返回所需要的accountId！"
            );
        }
        String accountid = accountArray.getJSONObject(0).getString("accountId");

        return accountid;

    }
    /*
    * 先查accountId 然后根据其在调用另一个接口获取岗位权限字段orgCode
     */
    @Override
    public String getOrgCodeFromKmcs(KmcsUser kmcsUser) {
        initUrl(EnumManager.Environment.FT);

        String treeCode = "KMCS001001";

        String accountId = getAccountIdFromKmcs(kmcsUser);
        String subSysFlag ="null";
        String main = "true";
        String url  = this.baseUrl + String.format(
                "restful/pl/bizorg/findPositionsByCode/%s/%s/%s/%s"
                ,treeCode,subSysFlag,accountId,main
        );

        AuthRequest authRequest = new AuthRequest(
                kmcsUser.getUsername(),
                kmcsUser.getPassword(),
                "KMCS"
        );
        ResponseEntity<String> response  = getAuthResponseFromKmcs(authRequest,url,new HttpHeaders());
        //此处不需消息头，设参数默认空,分别处理
        JSONObject responseJsonBody = JSON.parseObject(response.getBody());
        JSONArray accountArray = JSONArray.parseArray(responseJsonBody.toJSONString());
        if(accountArray.isEmpty()) {
            throw new ServiceException(
                    "accounts 信息为空! 没有返回所需要的accountId！"
            );
        }
        //??? 拿第一个accountId 还是做个accountIdList
        String orgCode = accountArray.getJSONObject(0).getString("orgCode");

        return orgCode;

    }
    public ResponseEntity<String> getAuthResponseFromKmcs(AuthRequest authRequest, String url, HttpHeaders headers){

        String requestBody = JSON.toJSONString(authRequest);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url,entity,String.class);

        if(response == null){
            throw new ServiceException("请求失败 ！；无法访问到对应的Kmcs系统接口url: "+url
                    +"没有响应:" + response);
        }
        /*
         *检测返回的状态 - 200 Ok
         * 然后检测body中的errorCode 是否为 ”10000“
         */
        if(response.getStatusCode() != HttpStatus.OK){
            throw new ServiceException("请求失败 ！；无法访问到对应的Kmcs系统接口url: "+url
                    +"响应消息头:" + response.getHeaders());
        }

        return response;
    }
    
}
