package com.kingmed.immuno.model.dataModel.fileClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoQueryBody{
    String fileId;
    String isIncludeFileContent = "true";

    public FileInfoQueryBody(String fileId) {
        this.fileId = fileId;
    }
};