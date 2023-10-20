package com.kingmed.immuno.model.dataModel.fileClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件信息基础类，用于上传文件等
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoBase {
    String fileName;
    int fileSize;
    String fileType = "image/png";
    String fileContent;
    String attPurpose = "";
}
