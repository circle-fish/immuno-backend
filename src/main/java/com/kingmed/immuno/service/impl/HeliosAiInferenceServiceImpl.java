package com.kingmed.immuno.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingmed.immuno.entity.HeliosAiTask;
import com.kingmed.immuno.mapper.HeliosAiTaskMapper;
import com.kingmed.immuno.mapper.HeliosImageMapper;
import com.kingmed.immuno.service.HeliosAiInferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AI推理结果的服务实现类，核心逻辑尚待完成
 */
@Service
public class HeliosAiInferenceServiceImpl implements HeliosAiInferenceService {

    @Autowired
    private KmcsPlatformServiceImpl kmcsPlatformService;

    @Autowired
    private HeliosAiTaskMapper heliosAiTaskMapper;
    @Autowired
    private HeliosImageMapper heliosImageMapper;
    @Autowired
    private HeliosImageServiceImpl heliosImageService;

    private String baseUrl = "";

    private List<HeliosAiTask> heliosAiTaskList;

    /**
     * @param heliosAiTasks
     */

    public void addTasks(List<HeliosAiTask> heliosAiTasks) {
        heliosAiTaskList.addAll(heliosAiTasks);
    }

    /**
     * 在start的时候进行, 直接装载数据库中的数据
     * 获取所有HeliosAITask
     */
    @Override
    public void prepareTasks() {
        QueryWrapper<HeliosAiTask> heliosAiTaskQueryWrapper = new QueryWrapper<>();
        //选全部
        heliosAiTaskQueryWrapper.select("*");
        List<HeliosAiTask> heliosAiTasks = heliosAiTaskMapper.selectList(heliosAiTaskQueryWrapper);
        heliosAiTaskList.addAll(heliosAiTasks);
    }
}
