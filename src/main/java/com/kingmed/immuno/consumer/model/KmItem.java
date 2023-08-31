package com.kingmed.immuno.consumer.model;

import lombok.Data;

import java.util.List;

@Data
public class KmItem {

    private String taskId;
    private String testItemCode;
    private String testItemName;
    private String experimentNo;
    private String testItemType;
    private List<KmDependTask> dependTasks;
}
