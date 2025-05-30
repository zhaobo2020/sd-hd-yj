package com.sl.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkDDResponse {
    @JsonProperty("tm_list")
    private List<String> timeList;

    @JsonProperty("w_level_list")
    private List<Double> waterLevelList;

    @JsonProperty("out_q_list")
    private List<Double> outflowList;
}