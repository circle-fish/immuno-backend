package com.kingmed.immuno.common;

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
        inited,binded,allocated,testing,saved,submitted,unhandled;
    }
}
