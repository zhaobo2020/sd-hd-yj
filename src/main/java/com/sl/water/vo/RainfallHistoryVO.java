package com.sl.water.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author duan
 * @description:历史降雨量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "历史降雨量信息对象")
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RainfallHistoryVO {

    /**
     * 降雨时长（单位：小时）
     */
    @Schema(
            description = "降雨时长",
            example = "4"
    )
    private int duration;

    /**
     * 降雨总量
     */
    @Schema(
            description = "降雨总量（单位：mm）",
            example = "10.5"
    )
    private Double rainfall;

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
     * 预警级别
     */
    @Schema(
            description = "预警级别",
            example = "1"
    )
    private Integer level;

    /**
     * 预警级别名称
     */
    @Schema(
            description = "预警级别名称"

    )
    private String levelName;

    @Schema(description = "降雨阈值")
    private Double threshold;

}