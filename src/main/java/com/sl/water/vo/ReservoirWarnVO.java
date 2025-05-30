package com.sl.water.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class ReservoirWarnVO {
    @Schema(
            description = "当前水位"
    )
    private Double z;
    @Schema(
            description = "入库流量"
    )
    private Double inq;
    @Schema(
            description = "出库流量"
    )
    private Double otq;
    @Schema(
            description = "预报水位"
    )
    @JsonProperty("z_yb")
    private Double zYb;
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
    @Schema(
            description = "出库流量预警级别"
    )
    private Integer otqLevel;
    @Schema(
            description = "出库流量预警级别名称"
    )
    private String otqLevelName;
    @Schema(
            description = "预报水位预警级别"
    )
    @JsonProperty("z_yb_level")
    private Integer zYbLevel;
    @Schema(
            description = "预报水位预警级别名称"
    )
    @JsonProperty("z_yb_level_name")
    private String zYbLevelName;


}
