package com.example.common;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();

        this.setFieldValByName("version", 1, metaObject);
        this.setFieldValByName("createdTime", now, metaObject);
        this.setFieldValByName("createdBy", "admin", metaObject);
        this.setFieldValByName("updatedTime",now, metaObject);
        this.setFieldValByName("updatedBy","admin", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedTime", new Date(), metaObject);
        this.setFieldValByName("updatedBy", "admin", metaObject);
    }
}
