package com.kingmed.immuno.util;

import com.kingmed.immuno.common.IndexCounter;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.factory.HeliosLabTaskWithPositionFactory;
import com.kingmed.immuno.service.factory.LabTaskFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务分配的工具类，添加sample任务和qc任务到Slide中
 */

public class HeliosAllocationUtils {

    public static List<LabTask>  addQCToSlide(VirtualSlide virtualSlide,
                                              List<String> qcNames,
                                              int numQc,
                                              LabTask nextLabTask,
                                              VirtualMachine nextVirtualMachine,
                                              HeliosReagent heliosReagent,
                                              String operatorName)
    {

        List<LabTask> qcLabTasks = new ArrayList<>();

        for (int i = 0 ; i < numQc ; i++) {
            if (!virtualSlide.checkAddable()) {
                throw new ServiceException("该玻璃片的孔位已满！");
            }
            LabTask virtualLabTask = LabTaskFactory.initQcLabTask(nextLabTask,
                    nextVirtualMachine,
                    heliosReagent,
                    nextVirtualMachine.getVirtualSlides().size()+1,
                    virtualSlide.getNextIndex()+1,
                    qcNames.get(i),
                    operatorName
            );

            virtualSlide.addSample(virtualLabTask);
            qcLabTasks.add(virtualLabTask);
        }

        nextVirtualMachine.setHasQc(true);
        return qcLabTasks;
    }
    public static void addSampleToSlide(VirtualSlide virtualSlide,
                                        VirtualMachine device,
                                        HeliosReagent heliosReagent,
                                        LabTask labTask,
                                        IndexCounter counter)
    {
        while (virtualSlide.checkAddable()){
            if (counter.isMax()){
                return;
            }
            HeliosLabTaskWithPostion virtualLabTask = HeliosLabTaskWithPositionFactory.initByNormLabTask(
                    labTask,
                    device,
                    heliosReagent,
                    device.getVirtualSlides().size(),
                    virtualSlide.getNextIndex()
            );
            virtualSlide.addSample(virtualLabTask);
            counter.add();
        }
    }
    public static List<Integer> ParseDevicePosition(String position){
        String[] devicePosition = position.split("-");
        List<Integer> devicePositionArray = new ArrayList<>();
        for(int i = 0 ; i < devicePosition.length; i++){
            devicePositionArray.add( Integer.parseInt(devicePosition[i]));
        }
        return devicePositionArray;
    }

}
