package com.example.Model;

import lombok.Data;

import java.util.List;

@Data
public class KmItem {
    String testItemCode;
    Integer status;
    String testItemName;
    String experimentNo;
    String taskId;
    String testItemType;
    String testItemId;
    List<KmTask> dependTasks;
}
