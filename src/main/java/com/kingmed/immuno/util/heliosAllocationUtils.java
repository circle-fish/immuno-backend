package com.kingmed.immuno.util;

import com.kingmed.immuno.common.IndexCounter;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.exception.ServiceException;
import com.kingmed.immuno.model.dataModel.dto.LabTaskDO;
import com.kingmed.immuno.model.dataModel.dto.VirtualMachine;
import com.kingmed.immuno.model.dataModel.dto.VirtualSlide;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.factory.HeliosLabTaskWithPositionFactory;
import com.kingmed.immuno.service.factory.LabTaskFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务分配的工具类，添加sample任务和qc任务到Slide中
 */
public class heliosAllocationUtils {

    @Autowired
    private static LabTaskFactory labTaskFactory;
    @Autowired
    private static HeliosLabTaskWithPositionFactory heliosLabTaskWithPositionFactory;


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
            LabTask virtualLabTask = labTaskFactory.initQcLabTask(nextLabTask,
                    nextVirtualMachine,
                    heliosReagent,
                    nextVirtualMachine.getVirtualSlides().size(),
                    virtualSlide.getNextIndex(),
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
            HeliosLabTaskWithPostion virtualLabTask = heliosLabTaskWithPositionFactory.initByNormLabTask(
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

    /**
     * 将LabTask父类强制转换为子类???
     *
     * @param task
     * @return LabTaskDO
     */
    public static LabTaskDO convertToDO(LabTask task) {
        return (LabTaskDO) task;
    }
}
