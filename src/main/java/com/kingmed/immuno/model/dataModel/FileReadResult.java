package com.kingmed.immuno.model.dataModel;

import com.kingmed.immuno.entity.LabTestItem;
import com.kingmed.immuno.entity.LabTestItemRel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用于传递项目转换结果的数据类
 */
@Data
@AllArgsConstructor
public class FileReadResult {
    List<LabTestItem> labTestItemList;
    List<LabTestItemRel> labTestItemRelList;
}
