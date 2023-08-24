package com.example.serviceimpl;

import com.example.Factory.LabTaskFactory;
import com.example.common.EnumManager;
import com.example.common.MapperHelpper;
import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.entity.LabTestItem;
import com.example.entity.LabTestItemRel;
import com.example.mapper.KmcsTaskMapper;
import com.example.mapper.LabTaskMapper;
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
    MapperHelpper mapperHelpper;

    @Autowired
    private LabTaskMapper labTaskMapper;
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
                                              Map<KmcsTask,LabTestItem> kmTasksToTestItems
    ) {
        LabTestItem labTaskItem = kmTasksToTestItems.get(kmcsTask);
        LabTask labTask = labTaskFactory.createLabTask(kmcsTask, labTaskItem);
        EnumManager.LabTestItemMode conversionMode = labTaskItem.getConversionMode();
        List<KmcsTask> kmTasks = new ArrayList<KmcsTask>();
        if (conversionMode == EnumManager.LabTestItemMode.LOOSE) {
            for (KmcsTask kmTask : kmTasksToTestItems.keySet()) {
                LabTestItem templabTaskItem = kmTasksToTestItems.get(kmTask);
                if (templabTaskItem.getCode() == labTaskItem.getCode()) {
                    kmTasks.add(kmTask);
                }
            }
        }
        if (conversionMode == EnumManager.LabTestItemMode.STRICT) {
            for (KmcsTask kmTask : kmTasksToTestItems.keySet()) {
                LabTestItem templabTaskItem = kmTasksToTestItems.get(kmTask);
                if (templabTaskItem.getCode() == labTaskItem.getCode()) {
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
                                               Map<KmcsTask,LabTestItem> kmTasksToTestItems){
        if (kmTasksToTestItems.size() == 0){
            return new ConversionResult(new ArrayList<>(), new ArrayList<>());
        }else if (kmTasksToTestItems.size() == 1){
            return convertOneOnOne(kmTask,kmTasksToTestItems);
        }
        else{
            return convertManyToOne(kmTask,kmTasksToTestItems);
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

            ConversionResult conversionResult = doConvertStrategy(kmTask,kmTasksToTestItems);
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

        Map<String, List<KmcsTask>> kmTaskGroupByAppId = kmTasks
                .stream()
                .collect(Collectors.groupingBy(
                        KmcsTask::getAppId
                ));

        List<LabTask> labTasks = new ArrayList<>();
        List<KmcsTask> kmcsTasks = new ArrayList<>();

        for(String appId: kmTaskGroupByAppId.keySet()){
            List<KmcsTask> kmTasksInAppGroup =  kmTaskGroupByAppId.get(appId);
            Map<KmcsTask,LabTestItem> kmTasksToTestItems = getKmTasksToTestItemsInAppGroup(kmTasksInAppGroup);
            labTasks.addAll(convertTaskInAppGroup(kmTasksToTestItems).getLabTasks());
            kmcsTasks.addAll((convertTaskInAppGroup(kmTasksToTestItems).getKmcsTasks()));
        }

        ConversionResult conversionResult = DBModificationTransaction(new ConversionResult(labTasks,kmcsTasks));
        //return 在数据中修改过的对象
        return new BaseResponse<>("200", "success", conversionResult);
    }

    @Transactional
    public Map<KmcsTask,LabTestItem> getKmTasksToTestItemsInAppGroup(List<KmcsTask> KmTasksInAppGroup) {
        Map<KmcsTask,LabTestItem> kmTasksToTestItems = new HashMap<>();
        for(KmcsTask kmcsTask : KmTasksInAppGroup)
        {
            LabTestItemRel  labTestItemRel = labTestItemRelMapper.selectLabTestItemRelByKmcsTestItemCode(
                    kmcsTask.getTestItemCode(),kmcsTask.getBizOrgCode());
            if(labTestItemRel==null)
                continue;
            LabTestItem labTaskItem = labTestItemMapper.selectLabTestItemByCode(
                    labTestItemRel.getLabTestItemCode(),
                    labTestItemRel.getBizOrgCode()
            );

            kmTasksToTestItems.put(kmcsTask,labTaskItem);
        }
        return kmTasksToTestItems;
    }

    @Transactional
    public ConversionResult DBModificationTransaction(ConversionResult conversionResult) {

        for (LabTask labTask : conversionResult.getLabTasks()) {
            mapperHelpper.upsert(labTask,labTaskMapper);
        }
        for (KmcsTask kmcsTask : conversionResult.getKmcsTasks()) {
                kmcsTask.setStatus(1);
            mapperHelpper.upsert(kmcsTask,kmcsTaskMapper);
        }
        return new ConversionResult(conversionResult.getLabTasks(),conversionResult.getKmcsTasks());

    }

}


