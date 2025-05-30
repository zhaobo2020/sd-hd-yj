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

/**
 * @description:
 * @author: duan
 * @time: 2025-05-23 16:39
 */

@Data
@Schema(description = "水库预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RsvrWarn {
    @Schema(
            description = "id"
    )
    private Long id;
    @Schema(
            description = "时间",
            example = "2023-08-01T08:00:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime tm;
    @Schema(
            description = "当前水位"
    )
    private Double z;
    @Schema(
            description = "出库流量"
    )
    private Double otq;
    @Schema(
            description = "水位预警级别"
    )
    @JsonProperty("z_level")
    private Integer zLevel;
    @Schema(
            description = "水位预警级别名称"
    )
    @JsonProperty("z_level_name")
    private String zLevelName;
    @Schema(description = "水位阈值")
    @JsonProperty("z_threshold")
    private Double zThreshold;
    @Schema(
            description = "出库流量预警级别"
    )
    private Integer otqLevel;
    @Schema(
            description = "出库流量预警级别名称"
    )
    private String otqLevelName;
    @Schema(description = "出库流量阈值")
    @JsonProperty("otq_threshold")
    private Double otqThreshold;

}
