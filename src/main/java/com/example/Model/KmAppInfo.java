package com.example.Model;

import lombok.Data;

import java.util.List;

@Data
public class KmAppInfo {
    private String applicationId;
    private String barcode;
    private String bizOrgCode;
    private List<KmItem> itemList;
}
