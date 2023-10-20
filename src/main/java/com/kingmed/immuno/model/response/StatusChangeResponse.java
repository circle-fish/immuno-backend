package com.kingmed.immuno.model.response;

import com.kingmed.immuno.common.EnumManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/**
 * 用于回传状态变更的结果
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusChangeResponse {
    private boolean success = false;
    private String message = "";

//    public static final Map<EnumManager.LabTaskStatus, Method>
//            statusChangeMap = new HashMap<>();
//    static {
//
//        statusChangeMap.put(EnumManager.LabTaskStatus.inited, initedStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.unhandled, unhandledStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.binded, bindedStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.allocated, allocatedStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.testing, testingStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.saved, savedStatusHandler());
//        statusChangeMap.put(EnumManager.LabTaskStatus.submitted, submittedStatusHandler());
//
//    }
    

    public static StatusChangeResponse initedStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if (targetStatus.ordinal() <= EnumManager.LabTaskStatus.binded.ordinal()) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从inited转成bindind或以下");
        }
    }

    public static StatusChangeResponse unhandledStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if (targetStatus == EnumManager.LabTaskStatus.binded) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从unhandled转成bindind");
        }
    }

    public static StatusChangeResponse bindedStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if ((targetStatus == EnumManager.LabTaskStatus.unhandled) || (targetStatus == EnumManager.LabTaskStatus.allocated)) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从binded转成unhandled或allocated");
        }
    }

    public static StatusChangeResponse allocatedStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if ((targetStatus == EnumManager.LabTaskStatus.testing) || (targetStatus == EnumManager.LabTaskStatus.unhandled)) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从allocated转成testing或unhandled");
        }
    }

    public static StatusChangeResponse testingStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if ((targetStatus == EnumManager.LabTaskStatus.saved) || (targetStatus == EnumManager.LabTaskStatus.submitted)) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从testing转成saved或submitted");
        }
    }

    public static StatusChangeResponse savedStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        if (targetStatus == EnumManager.LabTaskStatus.submitted) {
            return new StatusChangeResponse(true, "状态变更成功");
        } else {
            return new StatusChangeResponse(false, "只能从saved转成submitted");
        }
    }

    public static StatusChangeResponse submittedStatusHandler(EnumManager.LabTaskStatus targetStatus) {
        return new StatusChangeResponse(false, "submitted状态不允许变更");
    }

    /**
     * 通过目标任务当前状态判定要调用的函数
     * 并返回该函数的调用结果
     * @param targetStatus
     * @return
     * @throws NoSuchMethodException
     */
    public static StatusChangeResponse getStatusChangeResult(EnumManager.LabTaskStatus targetStatus)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<StatusChangeResponse> statusChangeResponseClass = StatusChangeResponse.class;
        String name = targetStatus.name().concat("StatusHandler");
        Method method = statusChangeResponseClass.getMethod(name,EnumManager.LabTaskStatus.class);
        return (StatusChangeResponse) method.invoke(new StatusChangeResponse(),targetStatus);
        //调用方法 返回object 可以进行强转

    }
}
