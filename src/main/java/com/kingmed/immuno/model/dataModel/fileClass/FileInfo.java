package com.kingmed.immuno.model.dataModel.fileClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo extends FileInfoBase {
    String id;
    String customId;
};