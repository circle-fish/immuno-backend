package com.kingmed.immuno.model.dataModel.fileClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
    String id;
    String uploadChannel;
    String uploader;
    List<FileInfo> fileInfos;
};