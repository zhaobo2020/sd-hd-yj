package com.sl.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum ModelCalculationType {
    // 实测过程（代码1）
    MEASURED_PROCESS(1, "实测过程"),
    // 快速预报过程（代码2）
    QUICK_FORECAST_PROCESS(2, "快速预报过程");

    private final int code;    // 类型代码
    private final String desc; // 类型描述

    // Getter方法
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}