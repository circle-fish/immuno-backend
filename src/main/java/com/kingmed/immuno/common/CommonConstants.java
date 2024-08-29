package com.kingmed.immuno.common;

import java.util.Arrays;
import java.util.List;

public interface CommonConstants {

    String X_ACCESS_TOKEN = "X-Access-Token";

    Integer SUCCESS = 0;

    Integer FAIL = 1;

    Integer NOT_DELETED = 0;

    Integer DELETED = 1;

    Integer DEFAULT_PAGE_NUM = 1;

    Integer DEFAULT_PAGE_SIZE = 100;

    Integer AWAITING_GO_SIGN = 0;

    Integer IN_USE = 1;

    Integer NUM_IMAGE = 3;
    /**
     * kmcs平台上传文件使用的upload_channel
     */

    String UPLOAD_CHANNEL = "immuno_sys";


    /**
     * 质控任务类型，qc和nc各做一次
     */
    List<String> QC_NAMES = Arrays.asList("qc", "nc");
    List<String> AI_TASK_RELATED_TEST_ITEM_TYPE_LIST = Arrays.asList("ANA");
}
