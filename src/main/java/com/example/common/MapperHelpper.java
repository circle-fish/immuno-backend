package com.example.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.*;
import com.example.mapper.KmcsTaskMapper;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;
@Component
public class MapperHelpper {

    public int upsert(BaseEntity baseEntity, BaseMapper mapper ) {
        int res = 0;
        Long id  = baseEntity.getId();
        if(mapper.selectById(id)!=null)
            res = mapper.updateById(baseEntity);
        else
            res = mapper.insert(baseEntity);

        return res;
    }
    public int upsert(KmcsTask kmcsTask, KmcsTaskMapper kmcsTaskMapper) {
        int res = 0;
        String taskId = kmcsTask.getTaskId();
        if(kmcsTaskMapper.selectById(taskId)!=null)
            res = kmcsTaskMapper.updateById(kmcsTask);
        else
            res = kmcsTaskMapper.insert(kmcsTask);
        return res;
    }
}
