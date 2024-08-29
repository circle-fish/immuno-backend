package com.kingmed.immuno.common;

/**
 * 存储枚举常量，如状态，类型，转换模式等等
 */
public class  EnumManager{
    public enum LabTestItemMode {
        /**
         * 转换模式
         */
        LOOSE,STRICT;
    }
    public enum LabTaskStatus {
        /**
         * 转换模式
         */
        inited(0),binded(1),allocated(2),testing(3),saved(4),submitted(5),unhandled(-1);
        private Integer value;
        LabTaskStatus(Integer value){
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }

    public enum Environment{
        FT,PRD;
    }

    /**
     * pc  阳性质控
     * nc 阴性质控
     */
    public enum QC_NAMES{
        pc,nc;
    }
    /**
     * normal 普通任务 - 0
     * qc 质控任务 - 1
     */
    public enum LabTaskType{
        normal(0),
        qc(1);

        private Integer value;
        LabTaskType(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
    /**
     *设备类型
     */
    public enum DeviceType{
        helios(0),blot(1);
        private Integer value;
        DeviceType(Integer value){
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }
    /**
     *设备状态
     */
    public enum DeviceStatus{
        usable,unusable;
    }
    /**
     *试剂类型
     */
    public enum ReagentType{
        helios(0),blot(1);
        private Integer value;
        ReagentType(Integer value){
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }
    /**
     * 保存结果类型
     * plain 普通的结果类型
     * structured 结构化的结果类型
     */
    public enum LabTaskResulType{
        plain(0),
        structured(1);

        private Integer value;
        LabTaskResulType(Integer value){
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }
    public enum HeliosAITaskStatus{
        /**
         * 存储Helios的AI任务状态
         */
        inited(0),uploaded(1),finished(2);
        private Integer value;
        HeliosAITaskStatus(Integer value){
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }


}
