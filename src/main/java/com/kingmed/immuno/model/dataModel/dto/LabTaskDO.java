package com.kingmed.immuno.model.dataModel.dto;

import com.kingmed.immuno.common.EnumManager;
import com.kingmed.immuno.entity.LabTask;

/**
 * 该类用于回传通用的LabTask的信息, 与LabTaskPO的数据结构类似
 * 暂时未定要保留的字段??? 先继承LabTask处理
 */
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
    private EnumManager.ReagentType reagentType;
    private String reagentTypeName;
    /**
     * 表示是否使用ORM（对象关系映射）模式
     * 不确定具体用法和作用???
     */
//    public class Config{
//        private boolean ormMode = true;
//
//        public boolean getOrmMode() {
//            return ormMode;
//        }
//    }
}
