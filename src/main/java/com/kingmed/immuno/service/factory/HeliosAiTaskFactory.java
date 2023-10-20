package com.kingmed.immuno.service.factory;

import cn.hutool.core.bean.BeanUtil;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.HeliosAiTask;
import com.kingmed.immuno.entity.HeliosImage;
import org.springframework.stereotype.Component;

@Component
public class HeliosAiTaskFactory {
    public HeliosAiTask createHeliosAiTaskByHeliosImage(HeliosImage heliosImage,String operatorName){
        HeliosAiTask heliosAiTask = new HeliosAiTask();
        heliosAiTask.setStatus(EnumManager.HeliosAITaskStatus.inited.getValue());
        heliosAiTask.setHeliosImageId(heliosImage.getId());
        heliosAiTask.setFileId(heliosImage.getAttachmentId());
        BeanUtil.copyProperties(heliosImage,heliosImage,"id");
        heliosAiTask = (HeliosAiTask) BaseFactory.initBaseAttribute(heliosAiTask,operatorName);
        return heliosAiTask;
    }
    public HeliosAiTask updateHeliosAiTaskByHeliosImage(HeliosAiTask heliosAiTask,HeliosImage heliosImage){

        heliosAiTask.setDeviceId(heliosAiTask.getDeviceId());
        heliosAiTask.setSlide(heliosImage.getSlide());
        heliosAiTask.setWell(heliosImage.getWell());
        heliosAiTask.setIndex(heliosImage.getIndex());
        heliosAiTask.setFileId(heliosImage.getAttachmentId());

        return heliosAiTask;
    }
}
