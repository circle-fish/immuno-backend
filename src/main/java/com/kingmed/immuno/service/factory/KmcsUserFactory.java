package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.KmcsUser;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class KmcsUserFactory {

    public  KmcsUser createKmcsUser(String username, String password, String bizOrgCode, 
                           String token , Date regTime){
        KmcsUser kmcsUser = new KmcsUser();
        kmcsUser.setUsername(username);
        kmcsUser.setPassword(password);
        kmcsUser.setBizOrgCode(bizOrgCode);
        kmcsUser.setToken(token);
        kmcsUser.setRegTime(regTime);

        return kmcsUser;
    }
}
