package com.kingmed.immuno.service.factory;

import com.kingmed.immuno.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseFactory {
    public static BaseEntity initBaseAttribute(BaseEntity entity,String operatorName){
        Date now = new Date();
        entity.setCreatedBy(operatorName);
        entity.setCreatedTime(now);
        entity.setUpdatedBy(operatorName);
        entity.setUpdatedTime(now);
        entity.setVersion(1);
        return entity;
    }


}
