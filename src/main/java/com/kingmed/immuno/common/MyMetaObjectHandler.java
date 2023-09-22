package com.kingmed.immuno.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 每次插入更新操作对通用字段进行更新
 */
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
        /**
         * 如果operatorName为空，则不更新updatedBy属性值
         */
        String operatorName = (String) metaObject.getValue("updatedBy");
        if (operatorName != null && !operatorName.isEmpty()) {
            this.setFieldValByName("updatedBy", operatorName, metaObject);
        }
        this.setFieldValByName("updatedTime", new Date(), metaObject);
        this.setFieldValByName("version",(Integer) metaObject.getValue("version") + 1 , metaObject);
    }
}
