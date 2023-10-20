package com.kingmed.immuno.service.factory;

import cn.hutool.core.bean.BeanUtil;
import com.kingmed.immuno.entity.HeliosImage;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.fileClass.FileInfoBase;
import com.kingmed.immuno.model.request.HeliosImagePosionInDevice;
import com.kingmed.immuno.model.request.HeliosImageQuery;
import com.kingmed.immuno.util.HeliosAllocationUtils;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeliosImageFactory {

    public HeliosImage updateHeliosImageWithLabTask(
            HeliosImage heliosImage,
            LabTask labTask
    ){
        //???用BeanUtils赋值相同属性会不会有问题
        heliosImage.setLabTaskId(labTask.getId());
        BeanUtil.copyProperties(labTask,heliosImage,"id");
        return heliosImage;
    }

    /**
     * 实际HeliosQuery类定义了file的文件信息该方法应该用不到
     * @param heliosImage
     * @param fileInfoBase
     * @return
     */
    public HeliosImage updateHeliosImageWithFileInfoBase(
            HeliosImage heliosImage,
            FileInfoBase fileInfoBase
    ){
        heliosImage.setFileName(fileInfoBase.getFileName());
        heliosImage.setFileSize(fileInfoBase.getFileSize());
        heliosImage.setAttPurpose(fileInfoBase.getAttPurpose());
        return heliosImage;
    }


    public HeliosImage updateHeliosImageWithPositionInDevice(
            HeliosImage heliosImage,
            HeliosImagePosionInDevice posionInDevice
    ){
        BeanUtil.copyProperties(posionInDevice,heliosImage);
        return heliosImage;
    }

    public HeliosImage updateHeliosImagePostionInfoByLabTask(
            HeliosImage heliosImage,
            LabTask labTask,
            int index
    ){
        heliosImage.setDeviceId(labTask.getDeviceId());
        List<Integer> positionArray = HeliosAllocationUtils.ParseDevicePosition(labTask.getDevicePosition());
        heliosImage.setSlide(positionArray.get(1));
        heliosImage.setWell(positionArray.get(2));
        heliosImage.setIndex(index);
        return heliosImage;
    }

    /**
     * 根据HeliosImageQuery和LabTask的信息创建HeliosImage
     * @param heliosImageQuery
     * @param labTask
     * @return HeliosImage
     */
    public HeliosImage createHelios(HeliosImageQuery heliosImageQuery,LabTask labTask)
    {
        HeliosImage heliosImage = new HeliosImage();
        heliosImage = updateHeliosImageWithLabTask(heliosImage,labTask);
        //Query中定义了FileInfo所需要的字段，无需调用updateHeliosImageWithFileInfoBase
        heliosImage = updateHeliosImageWithPositionInDevice(heliosImage,heliosImageQuery);
        heliosImage.setKmcsTaskId("");
        heliosImage.setIsBindedToKmcsTask(false);
        heliosImage.setAttachmentId(heliosImageQuery.getAttachmentId());
        heliosImage = (HeliosImage) BaseFactory.initBaseAttribute(heliosImage,heliosImageQuery.getOperatorName());
        return heliosImage;
    }
    public HeliosImage createHeliosImageByLabTask(LabTask labTask,
                                                  int index,
                                                  String operatorName)
    {
        HeliosImage heliosImage = new HeliosImage();
        heliosImage = updateHeliosImageWithLabTask(heliosImage,labTask);
        heliosImage = updateHeliosImagePostionInfoByLabTask(heliosImage,labTask,index);
        heliosImage.setKmcsTaskId("");
        heliosImage.setIsBindedToKmcsTask(false);
        heliosImage = (HeliosImage) BaseFactory.initBaseAttribute(heliosImage,operatorName);
        return  heliosImage;
    }


}
