package com.sl.water.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 降雨量信息响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "降雨预报信息响应对象，包含降雨时长、降雨总量、预警级别及时间范围数据")
@Accessors(chain = true)
public class RainYbDTO {

    /**
     * 降雨时长（单位：小时）
     * 示例值：4
     */
    @Schema(
            description = "降雨时长",
            example = "4"
    )
    private int rainDuration;

    /**
     * 降雨总量
     */
    @Schema(
            description = "降雨总量（单位：mm）",
            example = "10.5"
    )
    private Double rainfallTotal;

    /**
     * 预警级别
     */
    @Schema(
            description = "预警级别",
            example = "1"
    )
    private String level;

    /**
     * 降雨开始时间（精确到分钟）
     * 示例值：2023-08-01T08:00
     */
    @Schema(
            description = "降雨开始时间",
            example = "2023-08-01T08:00:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 降雨结束时间（精确到分钟）
     * 示例值：2023-08-01T12:30
     */

    @Schema(
            description = "降雨结束时间（精确到秒）",
            example = "2023-08-01T12:30:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;






}