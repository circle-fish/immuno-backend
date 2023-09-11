DROP TABLE IF EXISTS device;
CREATE TABLE device(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `status` INT    COMMENT '设备状态： 0—不可用 1—不可用' ,
    `device_type` INT    COMMENT '设备类型' ,
    `device_code` VARCHAR(32)    COMMENT '设备编号' ,
    `device_name` VARCHAR(32)    COMMENT '设备名称' ,
    `lab_test_item_ids` VARCHAR(128)    COMMENT '运行的实验项目的Id' ,
    `capacity` INT    COMMENT '容量' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS helios_ai_task;
CREATE TABLE helios_ai_task(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `status` INT    COMMENT '任务状态：初始状态 — 0 已上收 — 1 已判读 — 2 ' ,
    `device_id` BIGINT    COMMENT '设备编号' ,
    `slide` INT    COMMENT '坐标：玻片编号' ,
    `well` INT    COMMENT '坐标：孔位编号' ,
    `index` INT    COMMENT '坐标: 图片编号' ,
    `file_id` CHAR(36)    COMMENT '获取图片的url' ,
    `lab_task_id` BIGINT    COMMENT '所属的LabTask的Id' ,
    `helios_image_id` BIGINT    COMMENT '附件helios_image的Id' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS helios_image;
CREATE TABLE helios_image(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `device_id` BIGINT    COMMENT '设备Id' ,
    `device_name` VARCHAR(32)    COMMENT '设备名称' ,
    `lab_task_id` BIGINT    COMMENT '与其绑定的LabTask的Id' ,
    `barcode` VARCHAR(32)    COMMENT '条码号' ,
    `experiment_no` VARCHAR(32)    COMMENT '实验号' ,
    `lab_test_item_id` BIGINT    COMMENT '项目Id' ,
    `lab_test_item_name` VARCHAR(32)    COMMENT '项目名称' ,
    `kmcs_task_id` CHAR(36)    COMMENT '与其绑定的KmcsTaskTask的Id' ,
    `is_binded_to_kmcs_task` BIT(1)    COMMENT '是否绑定到KmcsTask上' ,
    `slide` INT    COMMENT '坐标：玻片编号' ,
    `well` INT    COMMENT '坐标：孔位编号' ,
    `index` INT    COMMENT '坐标：图片编号' ,
    `file_name` VARCHAR(256)    COMMENT '文件名称' ,
    `file_size` INT    COMMENT '文件大小' ,
    `att_purpose` VARCHAR(256)    COMMENT '图片备注' ,
    `attachment_id` CHAR(36)    COMMENT '文件地址' ,
    `label` VARCHAR(255)    COMMENT '图片判读结果' ,
    `inference` VARCHAR(255)    COMMENT 'AI判读结果' ,
    `remark` VARCHAR(512)    COMMENT '备注' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS helios_reagent;
CREATE TABLE helios_reagent(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `name` VARCHAR(32)    COMMENT '试剂名称' ,
    `batch_no` VARCHAR(32)    COMMENT '批次号' ,
    `expire_date` DATETIME    COMMENT '有效至日期' ,
    `num_wells` INT    COMMENT '玻片中孔的数目' ,
    `num_qc` INT    COMMENT '质控数' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS kmcs_task;
CREATE TABLE kmcs_task(
    `task_id` CHAR(36) NOT NULL   COMMENT 'KmcsTask的TaskID' ,
    `status` INT    COMMENT '状态:      * 0 —— 初始化，待操作      * 1 —— 已转换为LabTask      * 2 —— 已保存      * 3 —— 已提交' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `app_id` CHAR(36)    COMMENT '申请单Id' ,
    `barcode` VARCHAR(32)    COMMENT '条码号' ,
    `experiment_no` VARCHAR(32)    COMMENT '实验号' ,
    `test_item_code` VARCHAR(16)    COMMENT '项目代码' ,
    `test_item_name` VARCHAR(128)    COMMENT '项目名称' ,
    `lab_task_id` BIGINT    COMMENT '与该对象绑定的LabTask的Id' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (task_id)
)  COMMENT = '';

DROP TABLE IF EXISTS lab_order;
CREATE TABLE lab_order(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `wet` VARCHAR(32)    COMMENT '湿度' ,
    `temperature` VARCHAR(32)    COMMENT '温度' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS lab_task;
CREATE TABLE lab_task(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    `status` VARCHAR(255)    COMMENT '任务状态:     inited: 初始状态, 从KmTask转换后产生的初始状态     binded: 进入生产批次后的状态     allocated：任务分配后进入的状态     testing：进行检测中的状态     saved：LabTask的结果完成保存，并保存至KMCS系统中     submitted：LabTask的结果完成提交，并保存至KMCS系统中     unhandled: 该任务暂时不需要处理的状态' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `barcode` VARCHAR(32)    COMMENT '条码号' ,
    `experiment_no` VARCHAR(32)    COMMENT '实验号' ,
    `task_type` INT    COMMENT '任务类型：   0: 常规任务   1: 质控任务' ,
    `result` VARCHAR(255)    COMMENT '结果一般是字符串,有些项目可能是字符串形式,由labTestItemCode决定' ,
    `ai_result` VARCHAR(255)    COMMENT 'AI结果, 与result字段的格式相同' ,
    `lab_test_item_id` bigint    COMMENT '项目ID' ,
    `lab_test_item_name` VARCHAR(32)    COMMENT '项目名称' ,
    `lab_order_id` bigint    COMMENT '检测批次ID, 初始时为0' ,
    `device_type` INT    COMMENT '设备类型, 在转换时由项目决定' ,
    `device_id` bigint    COMMENT '设备ID, 在任务分配时确定, 初始值为0' ,
    `device_name` VARCHAR(32)    COMMENT '设备名称, 在任务分配时确定, 初始值为""' ,
    `device_position` VARCHAR(64)    COMMENT '设备位置, 在任务分配时确定, 初始值为""' ,
    `reagent_type` INT    COMMENT '试剂类型, 在转换时由项目决定' ,
    `reagent_lot` VARCHAR(32)    COMMENT '试剂批号, 在任务分配时确定' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS lab_test_item;
CREATE TABLE lab_test_item(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '项目Id' ,
    `name` VARCHAR(32)    COMMENT '项目名称' ,
    `code` VARCHAR(64)    COMMENT '' ,
    `conversion_mode` VARCHAR(255)    COMMENT '转换模式：LOOSE 宽松转换；STRICT 严格转换' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `sorted_index` INT    COMMENT '排序权重' ,
    `device_type` INT    COMMENT '设备类型' ,
    `reagent_type` INT    COMMENT '试剂类型' ,
    `version` INT    COMMENT '' ,
    `created_time` DATETIME    COMMENT '' ,
    `updated_time` DATETIME    COMMENT '' ,
    `created_by` VARCHAR(32)    COMMENT '' ,
    `updated_by` VARCHAR(32)    COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '';

DROP TABLE IF EXISTS lab_test_item_rel;
CREATE TABLE lab_test_item_rel(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '关系Id' ,
    `biz_org_code` VARCHAR(32)    COMMENT '子公司代码' ,
    `kmcs_test_item_code` VARCHAR(16)    COMMENT 'KmcsTask对应的项目编号' ,
    `kmcs_test_item_name` VARCHAR(64)    COMMENT 'KmcsTask对应的项目名称' ,
    `lab_test_item_code` VARCHAR(64)    COMMENT '该KmcsTask对应的LabTestItem项目编号' ,
    PRIMARY KEY (id)
)  COMMENT = '';

