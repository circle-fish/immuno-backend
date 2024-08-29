package com.kingmed.immuno.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskQueryRequest {
    private String taskId;
    private String userName;
}
