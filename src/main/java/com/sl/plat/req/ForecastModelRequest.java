package com.sl.plat.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 人工预报模型计算请求参数类（支持多站点实测降雨）
 * 包含模型计算所需的核心输入参数，所有字段均为必填项
 */
@Data
@Schema(description = "人工预报模型计算请求参数对象，支持多站点实测降雨数据")
public class ForecastModelRequest {

    @Schema(description = "预报方案名称（唯一标识当前预报方案）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025夏季洪水预演方案")
    @NotBlank(message = "方案名称不能为空")
    private String schemeName;

    @Schema(description = "模拟计算时长（单位：小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "72")
    @Positive(message = "模拟时长必须为正整数")
    @NotNull(message = "模拟时长不能为空")
    private Integer simulationDuration;

    @Schema(description = "降雨观测站点ID（唯一标识观测站点）", requiredMode = Schema.RequiredMode.REQUIRED, example = "70311590")
    @NotBlank(message = "站点ID不能为空")
    private String stationId;

    @Schema(description = "本次降雨持续时长（单位：分钟）", requiredMode = Schema.RequiredMode.REQUIRED, example = "180")
    @Positive(message = "降雨时长必须为正整数")
    @NotNull(message = "降雨时长不能为空")
    private Integer rainfallDuration;

    @Schema(description = "当前墒情（土壤含水量，0-1之间的小数）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.65")
    @NotNull(message = "墒情数据不能为空")
    @DecimalMin(value = "0.0", inclusive = true, message = "墒情值不能小于0")
    @DecimalMax(value = "1.0", inclusive = true, message = "墒情值不能大于1")
    private Double soilMoisture;

    @Schema(
            description = "水库初始水位（单位：米）",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "51.8",
            minimum = "0"
    )
    @PositiveOrZero(message = "初始水位不能为负数")
    @NotNull(message = "水库初始水位不能为空")
    private Double initialWaterLevel;

    @Schema(
            description = "水库调度开始时间（格式：yyyy-MM-dd'T'HH:mm:ss）",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2025-05-18T08:00:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")  // 控制序列化格式
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")              // 控制前端参数反序列化
    @NotNull(message = "调度开始时间不能为空")
    private LocalDateTime schedulingStartTime;

}
    