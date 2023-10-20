package com.kingmed.immuno.service.impl;

import com.kingmed.immuno.model.dataModel.fileClass.FileInfo;
import com.kingmed.immuno.model.dataModel.fileClass.FileInfoQueryBody;
import com.kingmed.immuno.model.dataModel.fileClass.FileUpload;
import com.kingmed.immuno.model.dataModel.fileClass.FileUploadParam;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

public class KmcsPlatformServiceImpl extends BaseApiServiceImpl{
    public FileUpload findByFileUploadId(FileUploadParam fileUploadParam){
        HttpHeaders headers = new HttpHeaders();
        headers.set("serviceid", "findByFileUploadId");
        headers.set("content-type","application/json;charset=UTF-8");
        FileUpload fileUpload = execAndReturn("/restful/fileUpload",
                headers,
                fileUploadParam,
                FileUpload.class
        );

        return fileUpload;
    }
    /**
     * 上传文件并且返回文件的file_id
     * need_file_id: 是否需要返回file_id
     * @param fileUpload
     * @param needFileId
     * @return 文件的fileId
     */
    public String  uploadFile(FileUpload fileUpload,boolean... needFileId){
        String path = "/restful/fileUpload";
        HttpHeaders headers = new HttpHeaders();
        headers.set("serviceid", "upload");
        headers.set("content-type","application/json;charset=UTF-8");
        String fileUploadId = execute(path,headers,fileUpload);
        //没有参数传入，使用可变参数（varargs）来实现方法的缺参默认值
        if(needFileId.length == 0 || (needFileId.length == 1 && needFileId[0] == false) ){
            return  fileUploadId;
        }
        else if(needFileId.length == 1 && needFileId[0] == true)
        {
            FileUploadParam fileUploadParam = new FileUploadParam(fileUploadId);
            fileUpload = findByFileUploadId(fileUploadParam);
            if(fileUpload.getFileInfos().size()==0){
                throw new RuntimeException("上传信息为空！fileUpload:" + fileUpload.getId());
            }
            return fileUpload.getFileInfos().get(0).getId();
        }
        else
        {
            throw new RuntimeException("参数needFileId输入有误！应为无参数或单个boolean变量！");
        }
    }

    public FileInfo findByFileId(String fileId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("serviceid", "findByFileId");
        headers.set("content-type","application/json;charset=UTF-8");

        FileInfo fileInfo =  execAndReturn("/restful/fileUpload",
                headers,
                new FileInfoQueryBody(fileId),
                FileInfo.class);

        return fileInfo;
    }

}
