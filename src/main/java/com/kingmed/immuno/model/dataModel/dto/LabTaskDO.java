package com.kingmed.immuno.model.dataModel.dto;

import cn.hutool.core.bean.BeanUtil;
import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.LabTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 该类用于回传通用的LabTask的信息, 与LabTaskPO的数据结构类似
 * 暂时未定要保留的字段??? 先继承LabTask处理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LabTaskDO extends LabTask {
    /**
     * 任务类型名称
     */
    private String taskTypeName;

    /**
     * 结果相关
     * 根据结果不同，其保存形式也不同
     */
    private EnumManager.LabTaskResulType resulType;
    private String resulTypeName;
    /**
     * 试剂类型
     */
    private String reagentTypeName;

    /**
     * 从继承来的LabTask决定试剂和任务等名称以便传回前端展示
     * 使用BaenUtil工具包给LabTaskDO子类赋值
     */
    public LabTaskDO(LabTask labTask){
        BeanUtil.copyProperties(labTask,this);
        getTypeName();
    }
    public void getTypeName() {
        if(this.getReagentType() == 0) {
            this.setReagentTypeName(EnumManager.ReagentType.helios.name());
        }else if(this.getReagentType() == 1){
            this.setReagentTypeName(EnumManager.ReagentType.blot.name());
        }
        if(this.getTaskType() == 0) {
            this.setTaskTypeName(EnumManager.LabTaskType.normal.name());
        }else if(this.getTaskType() == 1 ){
            this.setTaskTypeName(EnumManager.LabTaskType.qc.name());
        }
    }
}
