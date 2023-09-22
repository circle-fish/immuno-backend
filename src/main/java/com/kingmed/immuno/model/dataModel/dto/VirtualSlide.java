package com.kingmed.immuno.model.dataModel.dto;

import com.kingmed.immuno.common.IndexCounter;
import com.kingmed.immuno.entity.HeliosReagent;
import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import com.kingmed.immuno.service.factory.HeliosLabTaskWithPositionFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * helios设备任务分配相关的对象
 * 载物玻璃片，一个破璃片有每个孔位
 * 每个孔位对应一个SampleBase的virtualLabTask
 */
@Data
@AllArgsConstructor
public class VirtualSlide {
    @Autowired
    private HeliosLabTaskWithPositionFactory heliosLabTaskWithPositionFactory;

    private Integer initIndex;
    private Integer nextIndex;
    private Integer labTestItemId;
    private Integer capacity;

    private List<SampleBase> virtualLabTasks;

    private VirtualMachine bindDevice;

    public void bindMachine(VirtualMachine device){
        this.setBindDevice(device);
    }
    public VirtualSlide(int initIndex,int labTestItemId,int capacity){
        this.initIndex = initIndex;
        this.nextIndex = initIndex;
        this.labTestItemId = labTestItemId;
        this.capacity = capacity;
        virtualLabTasks = new ArrayList<>();
    }

    /**
     * 向Slide中批量添加样本, 直到Slide满了或者样本用完了
     * @param device
     * @param heliosReagent
     * @param labTask
     * @param indexCounter
     */
    public void batchAddSampleToSlide(VirtualMachine device,
                            HeliosReagent heliosReagent,
                            LabTask labTask,
                            IndexCounter indexCounter){
        while(this.checkAddable()){
            if(indexCounter.isMax()){
                return;
            }
            HeliosLabTaskWithPostion virtualLabTask = heliosLabTaskWithPositionFactory.
                    initByNormLabTask(
                            labTask,device,heliosReagent,
                            device.getVirtualSlides().size(),
                            this.getNextIndex()
                    );
            addSample(virtualLabTask);
            indexCounter.add();
        }
    }

    /**
     * 添加样本
     */
    public void addSample(SampleBase virtualLabTask){
        virtualLabTasks.add(virtualLabTask);
        this.nextIndex+=1;
    }

    /**
     * 检查是否可以添加
     * @return boolean
     */
    public boolean checkAddable(){
        return this.nextIndex < this.capacity;
    }



}
