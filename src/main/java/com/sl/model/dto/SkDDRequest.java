package com.sl.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data  // Lombok 注解，自动生成 getter/setter
public class SkDDRequest {
    // 必填字段（与接口中的 JSON 字段名对应）
    @JsonProperty("tm_list")
    private List<String> timeList;

    @JsonProperty("in_q_list")
    private List<Double> flowList;

    // 可选字段（提供默认值）
    @JsonProperty("open_tm")
    private String gateOpenTime = "2025050908";  // 默认时间

    @JsonProperty("start_w")
    private Double initialStorage = 5467500.0;     // 默认初始库存
}