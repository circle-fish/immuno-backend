package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 用来查询HeliosImage的基础信息
 */
public class HeliosImageQuery extends HeliosImageQueryBase{
    String attachmentId = "";
    boolean needBindToKmcsTask = false;

    /**
     * 文件相关信息
     */

    String fileName;
    int fileSize;
    String fileType = "image/png";
    /**
     * base64编码的字符串
     */
    String fileContent;
    String attPurpose;

}
