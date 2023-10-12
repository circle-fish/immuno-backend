package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class HeliosReagentFactory {
    public HeliosReagent createHeliosReagent(String bizOrgCode,int counter){
        HeliosReagent heliosReagent = new HeliosReagent();
        String count = Integer.toString(counter);
        heliosReagent.setName("@Reagent"+count);
        heliosReagent.setBatchNo(DateTimeUtil.getNow().toString());
        heliosReagent.setExpireDate(DateTimeUtil.addTime(DateTimeUtil.getNow(),1,0,0));
        //???暂定两个质控pc与nc 以及12个玻片
        heliosReagent.setNumWells(12);
        heliosReagent.setNumQc(2);
        heliosReagent.setBizOrgCode(bizOrgCode);
        return heliosReagent;
    }
}
