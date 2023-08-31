package com.kingmed.immuno.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kingmed.immuno.entity.BaseEntity;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.mapper.KmcsTaskMapper;
import org.springframework.stereotype.Component;
@Component
public class MapperHelpper {

    public int upsert(BaseEntity baseEntity, BaseMapper mapper ) {
        int res = 0;
        Integer id  = baseEntity.getId();
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
