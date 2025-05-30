package com.sl.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.util.List;
import java.time.LocalDateTime;


/**
 * 预报结果数据传输对象
 * <p>用于封装模型计算后的核心预报结果、特征参数及时间序列过程数据</p>
 */
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "预报结果数据传输对象（封装模型计算核心结果及过程数据）")
public class ForecastResultDTO {

    /**
     * 预报方案名称（唯一标识本次预报任务）
     * 示例值：2023年8月1日48小时降雨预报
     */
    @Schema(
            description = "预报方案名称（唯一标识本次预报任务）",
            example = "2023年8月1日48小时降雨预报",
            maxLength = 100
    )
    private String forecastName;

    // ==================== 核心计算结果 ====================
    /**
     * 来水总量（单位：立方米）
     * 示例值：1500000.0（表示150万立方米）
     */
    @Schema(
            description = "来水总量（单位：立方米）",
            example = "1500000.0",
            minimum = "0.0"
    )
    private Double totalInflow;

    /**
     * 来水峰值流量（单位：立方米每秒）
     * 示例值：2500.5（表示峰值流量2500.5m³/s）
     */
    @Schema(
            description = "来水峰值流量（单位：立方米每秒）",
            example = "2500.5",
            minimum = "0.0"
    )
    private Double peakInflow;

    /**
     * 来水峰值出现时间（精确到秒）
     * 示例值：2023-08-01T14:30:00
     */
    @Schema(
            description = "来水峰值出现时间（精确到秒）",
            example = "2023-08-01T14:30:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    private LocalDateTime peakTime;

    /**
     * 最高水位（单位：米）
     * 示例值：105.3（表示最高水位105.3米）
     */
    @Schema(
            description = "最高水位（单位：米）",
            example = "105.3",
            minimum = "0.0"
    )
    private Double maxWaterLevel;

    /**
     * 最高水位出现时间（精确到秒）
     * 示例值：2023-08-01T15:15:00
     */
    @Schema(
            description = "最高水位出现时间（精确到秒）",
            example = "2023-08-01T15:15:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    private LocalDateTime maxLevelTime;

    /**
     * 闸门开启时间（精确到秒）
     * 示例值：2023-08-01T12:00:00（表示12点开启闸门）
     */
    @Schema(
            description = "闸门开启时间（精确到秒）",
            example = "2023-08-01T12:00:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    private LocalDateTime gateOpenTime;

    // ==================== 特征水位参数 ====================
    /**
     * 正常蓄水位（单位：米）
     * 示例值：100.0（水库正常运行时的蓄水位）
     */
    @Schema(
            description = "正常蓄水位（水库正常运行时的蓄水位，单位：米）",
            example = "100.0",
            minimum = "0.0"
    )
    private Double normalStorageLevel;

    /**
     * 防洪高水位（单位：米）
     * 示例值：98.5（防洪期间允许达到的最高水位）
     */
    @Schema(
            description = "防洪高水位（防洪期间允许达到的最高水位，单位：米）",
            example = "98.5",
            minimum = "0.0"
    )
    private Double floodControlLevel;

    /**
     * 设计洪水位（单位：米）
     * 示例值：102.0（按设计标准确定的洪水位）
     */
    @Schema(
            description = "设计洪水位（按设计标准确定的洪水位，单位：米）",
            example = "102.0",
            minimum = "0.0"
    )
    private Double designFloodLevel;

    /**
     * 汛线水位（单位：米）
     * 示例值：95.0（汛期限制水位，需低于正常蓄水位）
     */
    @Schema(
            description = "汛线水位（汛期限制水位，需低于正常蓄水位，单位：米）",
            example = "95.0",
            minimum = "0.0"
    )
    private Double floodSeasonLevel;

    // ==================== 时间序列过程数据（严格时间对齐） ====================
    /**
     * 时间过程序列（按小时/分钟对齐的时间点）
     * 示例值：["2023-08-01T08:00:00","2023-08-01T09:00:00","2023-08-01T10:00:00"]
     */
    @Schema(
            description = "时间过程序列（按预报步长对齐的时间点，精确到秒）",
            example = "[\"2023-08-01T08:00:00\",\"2023-08-01T09:00:00\"]",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    private List<LocalDateTime> timeSeries;

    /**
     * 入库流量过程（与时间序列一一对应，单位：立方米每秒）
     * 示例值：[1200.0, 1500.5, 1800.0]（对应8:00、9:00、10:00的入库流量）
     */
    @Schema(
            description = "入库流量过程（与时间序列一一对应，单位：立方米每秒）",
            example = "[1200.0, 1500.5]"
    )
    private List<Double> inflowProcess;

    /**
     * 出库流量过程（与时间序列一一对应，单位：立方米每秒）
     * 示例值：[800.0, 900.5, 1000.0]（对应8:00、9:00、10:00的出库流量）
     */
    @Schema(
            description = "出库流量过程（与时间序列一一对应，单位：立方米每秒）",
            example = "[800.0, 900.5]"
    )
    private List<Double> outflowProcess;

    /**
     * 水位过程（与时间序列一一对应，单位：米）
     * 示例值：[98.2, 98.5, 98.8]（对应8:00、9:00、10:00的水位）
     */
    @Schema(
            description = "水位过程（与时间序列一一对应，单位：米）",
            example = "[98.2, 98.5]"
    )
    private List<Double> waterLevelProcess;

    /**
     * 降雨过程（与时间序列一一对应，单位：毫米）
     * 示例值：[1.2, 3.5, 0.8]（对应8:00、9:00、10:00的降雨量）
     */
    @Schema(
            description = "降雨过程（与时间序列一一对应，单位：毫米）",
            example = "[1.2, 3.5]"
    )
    private List<Double> rainfallProcess;
}

