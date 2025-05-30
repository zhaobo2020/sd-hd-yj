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
@Schema(description = "降雨量观测信息响应对象，包含降雨时长、时间范围及逐时段降雨量数据")
@Accessors(chain = true)
public class RainListDTO {

    /**
     * 降雨时长（单位：小时）
     * 示例值：4.5（表示 4 小时 30 分钟）
     */
    @Schema(
            description = "降雨时长",
            example = "4.5"
    )
    private Double rainDuration;

    /**
     * 降雨总量
     */
    @Schema(
            description = "降雨总量（单位：mm）",
            example = "10.5"
    )
    private Double rainfallTotal;

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

    /**
     * 时间过程（按时间顺序排列的时间点，与降雨量过程一一对应）
     * 示例：[2023-08-01T08:00, 2023-08-01T09:00, ..., 2023-08-01T12:30]
     */
    @Schema(
            description = "时间过程（按时间顺序排列的逐时段时间点，与降雨量过程一一对应）",
            example = "[\"2023-08-01T08:00:00\", \"2023-08-01T09:00:00\", \"2023-08-01T10:00:00\"]"
    )
    private List<LocalDateTime> timeProcess;

    /**
     * 降雨量过程（按时间顺序排列的降雨量值，单位：mm，与时间过程一一对应）
     * 示例：[1.2, 3.5, 2.8, 0.6, ...]
     */
    @Schema(
            description = "降雨量过程（按时间顺序排列的逐时段降雨量值，单位：mm，与时间过程一一对应）",
            example = "[1.2, 3.5, 2.8]"
    )
    private List<Double> rainfallProcess;


}