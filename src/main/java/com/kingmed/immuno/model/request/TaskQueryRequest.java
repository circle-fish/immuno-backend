package com.kingmed.immuno.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskQueryRequest {
    private String taskId;
    private String userName;
}
