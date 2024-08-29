package com.kingmed.immuno.service;

import com.kingmed.immuno.entity.HeliosAiTask;

import java.util.List;

public interface HeliosAiInferenceService {

    void addTasks(List<HeliosAiTask> heliosAITaskList);

    void prepareTasks();
}
