package com.kingmed.immuno.service;

import com.kingmed.immuno.entity.KmcsUser;

/*
?依赖于Kmcs的服务，用于处理Kmcs的相关业务
*先暂放一些需要用到的功能函数-可以封装在其他service中
 */
public interface KmcsService {

    /*
    *从Kmcs处获取Token字符串
     */
    public String getTokenFromKmcs(KmcsUser kmcsUser);
    /*
     *更新User的Token并返回更新后的Token
     */
    public String updateToken(KmcsUser kmcsUser);


    String getAccountIdFromKmcs(KmcsUser kmcsUser);

    String getOrgCodeFromKmcs(KmcsUser kmcsUser);
}
