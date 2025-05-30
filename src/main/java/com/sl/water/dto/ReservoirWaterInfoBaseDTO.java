package com.sl.water.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) // 关键注解
public class ReservoirWaterInfoBaseDTO {

    /**
     * 测站编码
     */
    private String stationCode;
    /**
     * 水库名称
     */
    private String name;

    /**
     * 校核洪水位，单位：m
     */
    private Double checkFloodLevel;
    /**
     * 设计洪水位，单位：m
     */
    private Double designFloodLevel;
    /**
     * 正常高水位，单位：m
     */
    private Double normalHighWaterLevel;
    /**
     * 死水位，单位：m
     */
    private Double deadWaterLevel;
    /**
     * 兴利水位，单位：m
     */
    private Double beneficialWaterLevel;

    /**
     * 汛线水位，单位：m
     */
    private Double wLevelLimit;
}
