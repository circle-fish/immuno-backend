package com.kingmed.immuno.service.impl;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.common.MapperHelpper;
import com.kingmed.immuno.entity.KmcsTask;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.entity.LabTestItem;
import com.kingmed.immuno.entity.LabTestItemRel;
import com.kingmed.immuno.mapper.KmcsTaskMapper;
import com.kingmed.immuno.mapper.LabTaskMapper;
import com.kingmed.immuno.mapper.LabTestItemMapper;
import com.kingmed.immuno.mapper.LabTestItemRelMapper;
import com.kingmed.immuno.model.dataModel.ConversionResult;
import com.kingmed.immuno.model.dataModel.DoubleMap;
import com.kingmed.immuno.model.response.BaseResponse;
import com.kingmed.immuno.service.TaskConversionService;
import com.kingmed.immuno.service.factory.KmFactory;
import com.kingmed.immuno.service.factory.LabTaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 本模块用于将KmTask转换成LabTask，并将LabTask插入数据库
 *
 * sorted index 排序取最大
 * kmtask多对多labtestitems
 * 根据lab_test_rel表做两个映射 KmTasksToTestItems 与 TestItemsToKmTasks
 *
 * Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems
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

    @Autowired
    private KmFactory kmFactory;

    /**
     * 一对一转换
     * @param kmcsTask
     */
    private ConversionResult convertOneOnOne(KmcsTask kmcsTask,
                                             Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems){
        LabTestItem labTaskItem = KmTasksToTestItems.get(kmcsTask).get(0);
        LabTask labTask = labTaskFactory.createLabTask(kmcsTask, labTaskItem);
        //创建之后立马更新
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
     * 多个LabTestItem转为一个LabTask
     * @param kmcsTask
     */
    private ConversionResult convertManyToOne(KmcsTask kmcsTask,
                                              Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems,
                                              Map<LabTestItem, List<KmcsTask>> TestItemsToKmTasks){
        List<LabTestItem> labTestItems = KmTasksToTestItems.get(kmcsTask);
        labTestItems = labTestItems.stream().
                sorted(Comparator.comparing(LabTestItem::getSortedIndex).reversed())
                .collect(Collectors.toList());

//        得到按sorted_index从大到小排序的labTestItems , 只优先转换sort_index最大的那一个

        List<LabTask> labTasks = new ArrayList<>();

//        判断STRICT与LOOSE转换
        LabTestItem labTestItem = labTestItems.get(0);
        if(ConvertCheck(labTestItem,KmTasksToTestItems,TestItemsToKmTasks)){
            LabTask labTask = labTaskFactory.createLabTask(kmcsTask,labTestItem);
            //创建之后立马更新
            labTasks.add(labTask);
        }else {
            //无法转换返回两个空
            return new ConversionResult(new ArrayList<>(), new ArrayList<>());
        }
        return new ConversionResult(
               labTasks,
                new ArrayList<KmcsTask>() {{
                    add(kmcsTask);
                }}
            );
    }
    //根据labtestItem的转换模式，判断是否能进行转换
    private boolean ConvertCheck(LabTestItem labTaskItem, Map<KmcsTask, List<LabTestItem>> KmTasksToTestItems
            ,Map<LabTestItem, List<KmcsTask>> TestItemsToKmTasks ) {
        EnumManager.LabTestItemMode conversionMode = labTaskItem.getConversionMode();

//        实际的项目代码actualCodes--所有未转换KmTask中的code
        List<String> actualCodes = KmTasksToTestItems.keySet()
                .stream()
                .filter(s->s.getStatus()==0)//未转换的Kmtask
                .map(x->x.getTestItemCode())
                .collect(Collectors.toList());
//        需要的项目代码needCodes --根据rel表中的test_item_coe对应的多个KmcsTaskCode
        List<String> needCodes =  TestItemsToKmTasks.get(labTaskItem)
                .stream()
                .map(x->x.getTestItemCode())
                .collect(Collectors.toList());
//        严格转换需要未转换的KmTask的所有actualCodes 是需要转换的needCodes的父集
        if (conversionMode == EnumManager.LabTestItemMode.STRICT && actualCodes.containsAll(needCodes)) {
            return true;
        }
//        宽松转换两者只要有交集即可
        if (conversionMode == EnumManager.LabTestItemMode.LOOSE && actualCodes.retainAll(needCodes)) {
            return true;
        }
       return false; 

    }

    private ConversionResult doConvertStrategy(KmcsTask kmTask,
                                               Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems,
                                               Map<LabTestItem, List<KmcsTask>> TestItemsTokmTasks){
        if (KmTasksToTestItems.get(kmTask).size() == 0){
            return new ConversionResult(new ArrayList<>(), new ArrayList<>());
        }else if (KmTasksToTestItems.get(kmTask).size() == 1){
            return convertOneOnOne(kmTask,KmTasksToTestItems);
            //一对一只需一个映射即可???此处判断size来决定策略
        }
        else{
            return convertManyToOne(kmTask,KmTasksToTestItems,TestItemsTokmTasks);
        }
    }

    /**
     * 将KmTask转换成LabTask，并将LabTask插入数据库
     * @param KmTasksToTestItems
     */
    private ConversionResult convertTaskInAppGroup(Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems,Map<LabTestItem, List<KmcsTask>> TestItemsTokmTasks){
        List<LabTask> labTasks = new ArrayList<>();
        List<KmcsTask> kmTasksForUpdate = new ArrayList<>(KmTasksToTestItems.keySet().size());
        for(KmcsTask kmTask : KmTasksToTestItems.keySet()){
            if (kmTask.getStatus() != 0){
                continue;
            }
            //忽略已转换的KmcsTask ——getCheckTasks
            ConversionResult conversionResult = doConvertStrategy(kmTask,KmTasksToTestItems,TestItemsTokmTasks);

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
            Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems = getKmTasksToTestItemsInAppGroup(kmTasksInAppGroup).getKmTasksToTestItems();
            Map<LabTestItem,List<KmcsTask>> TestItemsToKmTasks = getKmTasksToTestItemsInAppGroup(kmTasksInAppGroup).getTestItemsTokmTasks();
            labTasks.addAll(convertTaskInAppGroup(KmTasksToTestItems,TestItemsToKmTasks).getLabTasks());
            kmcsTasks.addAll((convertTaskInAppGroup(KmTasksToTestItems,TestItemsToKmTasks).getKmcsTasks()));
        }

        ConversionResult conversionResult = DBModificationTransaction(new ConversionResult(labTasks,kmcsTasks));
        //return 在数据中修改过的对象
        return new BaseResponse<>("200", "success", conversionResult);
    }


    //return kmTask 对应 labtestitems
    private DoubleMap getKmTasksToTestItemsInAppGroup(List<KmcsTask> KmTasksInAppGroup) {
        Map<KmcsTask,List<LabTestItem>> KmTasksToTestItems = new HashMap<>();
        Map<LabTestItem, List<KmcsTask>> TestItemsToKmTasks = new HashMap<>();
        
        List<LabTestItem> AllLabTestItems = new ArrayList<>();
        //AllLabTestItems 表示该AppId下所有KmTasks对应的labTestItems集合

        for(KmcsTask kmcsTask : KmTasksInAppGroup)
        {
            List<LabTestItemRel>  KmcslabTestItemRels = labTestItemRelMapper.selectLabTestItemRelsByKmcsTestItemCode(
                    kmcsTask.getTestItemCode(),
                    kmcsTask.getBizOrgCode());
            //获取Rel表中 KmcsItemCode ——> labTestItemCode 的映射
            
            List<LabTestItem> labTestItems = new ArrayList<>();

            for(LabTestItemRel KmcslabTestItemRel : KmcslabTestItemRels) {
                if (KmcslabTestItemRel == null) {
                    continue;
                }
                LabTestItem labTestItem = labTestItemMapper.selectLabTestItemByCode(
                        KmcslabTestItemRel.getLabTestItemCode(),
                        KmcslabTestItemRel.getBizOrgCode()
                );
                if(labTestItem!=null) {
                    labTestItems.add(labTestItem);
                }
            }

            KmTasksToTestItems.put(kmcsTask,labTestItems);
            AllLabTestItems.addAll(labTestItems);

            //获取map ：kmTask 和他对应的所有labTestItems & AllLabTestItems 加上
        }
        
        for(LabTestItem labTestItem : AllLabTestItems) {
            List<LabTestItemRel> labTestItemRels = labTestItemRelMapper.selectLabTestItemRelsByLabTestItemCode(
                    labTestItem.getCode(),
                    labTestItem.getBizOrgCode());
            List<String> kmcsTestItemCodes = labTestItemRels.stream()
                    .map(LabTestItemRel::getKmcsTestItemCode)
                    .collect(Collectors.toList());

            List<KmcsTask> kmcsTasks = KmTasksInAppGroup
                    .stream()
                    .filter(kmcsTask -> kmcsTestItemCodes.contains(kmcsTask.getTestItemCode()))
                    .collect(Collectors.toList());
            //获取Rel表中 LabTestItemCode ——> KmcsItemCode 的映射

            TestItemsToKmTasks.put(labTestItem,kmcsTasks);
            //获取map ：labTestItem 和他对应的所有KmcsTasks
        }


        
        return new DoubleMap(KmTasksToTestItems,TestItemsToKmTasks);
    }

    public ConversionResult DBModificationTransaction(ConversionResult conversionResult) {

        for (int i = 0 ; i < conversionResult.getKmcsTasks().size() ; i ++ ) {
            LabTask labTask = conversionResult.getLabTasks().get(i);
            mapperHelpper.upsert(labTask,labTaskMapper);
        }

        for (int i = 0 ; i < conversionResult.getKmcsTasks().size() ; i ++ ) {
            LabTask labTask = conversionResult.getLabTasks().get(i);
            KmcsTask kmcsTask = conversionResult.getKmcsTasks().get(i);
            //？？？是否按顺序一一对应 / 在上面的convert函数中做updated值无法传回来
            kmFactory.updatedWhenConverted(labTask,kmcsTask);
            mapperHelpper.upsert(kmcsTask,kmcsTaskMapper);
        }
        return new ConversionResult(conversionResult.getLabTasks(),conversionResult.getKmcsTasks());

    }

}


