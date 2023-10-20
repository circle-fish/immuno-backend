package com.kingmed.immuno.model.dataModel.fileClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadParam{
    String fileUploadId;
    String isIncludeFileContent = "false";

    public FileUploadParam(String fileUploadId) {
        this.fileUploadId = fileUploadId;
    }
};