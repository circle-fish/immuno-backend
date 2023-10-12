package com.kingmed.immuno.model.request;

import com.kingmed.immuno.entity.LabTask;
import com.kingmed.immuno.model.dataModel.ExcelHeaderInfo;
import com.kingmed.immuno.model.vo.HeliosLabTaskWithPostion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于导出任务分配操作清单的excel文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportRequest {
    /**
     * 任务列表
     */
    List<HeliosLabTaskWithPostion> heliosLabTaskWithPostions;
    /**
     * 表头信息
     */
    ExcelHeaderInfo excelHeaderInfo;
}
