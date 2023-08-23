package com.example.serviceimpl;

import com.example.Factory.LabTaskFactory;
import com.example.common.EnumManager;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.entity.LabTestItem;
import com.example.entity.LabTestItemRel;
import com.example.mapper.KmcsTaskMapper;
import com.example.mapper.LabTestItemMapper;
import com.example.mapper.LabTestItemRelMapper;
import com.example.model.dataModel.ConversionResult;
import com.example.model.dataModel.LabTaskDO;
import com.example.model.response.BaseResponse;
import com.example.service.TaskConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 本模块用于将KmTask转换成LabTask，并将LabTask插入数据库
 */
@Service
public class TaskConversionServiceImpl implements TaskConversionService {

    @Autowired
    private KmcsTaskMapper kmcsTaskMapper;

    @Autowired
    private LabTestItemRelMapper labTestItemRelMapper;

    @Autowired
    private LabTestItemMapper labTestItemMapper;

    @Autowired
    private LabTaskFactory labTaskFactory;

    /**
     * 一对一转换
     * @param kmcsTask
     */
    private ConversionResult convertOneOnOne(KmcsTask kmcsTask,
                                             List<LabTestItemRel> labTestItemRel,
                                             Map<KmcsTask,LabTestItem> kmTasksToTestItems){
        LabTestItem labTaskItem = kmTasksToTestItems.get(kmcsTask);
        LabTask labTask = labTaskFactory.createLabTask(kmcsTask, labTaskItem);

        return new ConversionResult(
                new ArrayList<LabTask>(){{
                    add(labTask);
                }},
                new ArrayList<KmcsTask>(){{
                    add(kmcsTask);
                }}
        );
    }

    /**kmcs_task
     * 多对一转换
     * @param kmcsTask
     */
    private ConversionResult convertManyToOne(KmcsTask kmcsTask,
                                              List<LabTestItemRel> labTestItemRel,
                                              Map<KmcsTask,LabTestItem> kmTasksToTestItems
    ) {
        LabTestItem labTaskItem = kmTasksToTestItems.get(kmcsTask);
        LabTask labTask = labTaskFactory.createLabTask(kmcsTask, labTaskItem);
        EnumManager.LabTestItemMode conversionMode = labTaskItem.getConversionMode();
        List<KmcsTask> kmTasks = new ArrayList<KmcsTask>();
        if (conversionMode == EnumManager.LabTestItemMode.LOOSE) {
            for (KmcsTask kmTask : kmTasksToTestItems.keySet()) {
                LabTestItem templabTaskItem = kmTasksToTestItems.get(kmTask);
                if (templabTaskItem.getId() == labTaskItem.getId()) {
                    kmTasks.add(kmTask);
                }
            }
        }
        if (conversionMode == EnumManager.LabTestItemMode.STRICT) {
            for (KmcsTask kmTask : kmTasksToTestItems.keySet()) {
                LabTestItem templabTaskItem = kmTasksToTestItems.get(kmTask);
                if (templabTaskItem.getId() == labTaskItem.getId()) {
                    kmTasks.add(kmTask);
                } else {
                    return new ConversionResult(new ArrayList<>(), new ArrayList<>());
                }
            }
        }
        return new ConversionResult(
                new ArrayList<LabTask>() {{
                    add(labTask);
                }},
                kmTasks
            );
    }

    private ConversionResult doConvertStrategy(KmcsTask kmTask,
                                               List<LabTestItemRel> labTestItemRels,
                                               Map<KmcsTask,LabTestItem> kmTasksToTestItems){
        if (labTestItemRels.size() == 0){
            return new ConversionResult(new ArrayList<>(), new ArrayList<>());
        }else if (labTestItemRels.size() == 1){
            return convertOneOnOne(kmTask,labTestItemRels,kmTasksToTestItems);
        }
        else{
            return convertManyToOne(kmTask,labTestItemRels,kmTasksToTestItems);
        }
    }

    /**
     * 将KmTask转换成LabTask，并将LabTask插入数据库
     * @param kmTasksToTestItems
     */
    private ConversionResult convertTaskInAppGroup(Map<KmcsTask,LabTestItem> kmTasksToTestItems){
        List<LabTask> labTasks = new ArrayList<>();
        List<KmcsTask> kmTasksForUpdate = new ArrayList<>(kmTasksToTestItems.keySet().size());
        for(KmcsTask kmTask : kmTasksToTestItems.keySet()){
            if (kmTask.getStatus() != 0){
                continue;
            }
            List<LabTestItemRel> labTestItemRels = labTestItemRelMapper.selectLabTestItemRelByTestItemCode(
                    kmTask.getTestItemCode(),kmTask.getBizOrgCode()
            );
            ConversionResult conversionResult = doConvertStrategy(kmTask,labTestItemRels,kmTasksToTestItems);
            labTasks.addAll(conversionResult.getLabTasks());
            kmTasksForUpdate.addAll(conversionResult.getKmcsTasks());
        }
        return new ConversionResult(labTasks,kmTasksForUpdate);
    }


    @Transactional
    public BaseResponse<ConversionResult> convertKmTaskToLabTask(String bizOrgCode) {
        List<KmcsTask> kmTasks = kmcsTaskMapper.selectKmcsTaskByBizOrgCode(bizOrgCode);
        if (kmTasks.size() == 0) {
            return new BaseResponse<>("404", "No task found", null);
        }
        Map<KmcsTask,LabTestItem> kmTasksToTestItems = new HashMap<>();
        for(KmcsTask kmcsTask : kmTasks)
        {
            LabTestItem labTaskItem = labTestItemMapper.selectLabTestItemByCode(
                    kmcsTask.getTestItemCode(),
                    kmcsTask.getBizOrgCode());

            kmTasksToTestItems.put(kmcsTask,labTaskItem);
        }

        List<LabTask> labTasks = new ArrayList<>();
        List<KmcsTask> kmcsTasks = new ArrayList<>();
        Map<String, List<KmcsTask>> kmTaskGroupByAppId = kmTasks
                .stream()
                .collect(Collectors.groupingBy(
                        KmcsTask::getAppId
                ));
        for(String appId: kmTaskGroupByAppId.keySet()){
            List<KmcsTask> kmTasksInAppGroup = kmTaskGroupByAppId.get(appId);
            labTasks.addAll(convertTaskInAppGroup(kmTasksToTestItems).getLabTasks());
            kmcsTasks.addAll((convertTaskInAppGroup(kmTasksToTestItems).getKmcsTasks()));

        }
        ConversionResult conversionResult = new ConversionResult(labTasks,kmcsTasks);
        return new BaseResponse<>("200", "success", conversionResult);
    }
}
