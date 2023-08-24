package com.example.model.dataModel;

import com.example.entity.KmcsTask;
import com.example.entity.LabTask;
import com.example.entity.LabTestItem;
import com.example.entity.LabTestItemRel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用于传递项目转换结果的数据类
 */
@Data
@AllArgsConstructor
public class FileReadResult {
    List<LabTestItem> labTestItems;
    List<LabTestItemRel> labTestItemRels;
}
