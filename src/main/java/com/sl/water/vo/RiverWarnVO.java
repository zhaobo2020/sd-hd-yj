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
@Schema(description = "河道预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RiverWarnVO {
    @Schema(
            description = "名称"
    )
    private String name;

    @Schema(
            description = "当前水位"
    )
    private Double z;

    @Schema(
            description = "流量"
    )
    private Double q;

    @Schema(
            description = "预报流量"
    )
    @JsonProperty("q_yb")
    private Double qYb;

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
            description = "流量预警级别"
    )
    @JsonProperty("q_level")
    private Integer qLevel;

    @Schema(
            description = "流量预警级别名称"
    )
    @JsonProperty("q_level_name")
    private String qLevelName;

    @Schema(
            description = "预报水位预警级别"
    )
    @JsonProperty("q_yb_level")
    private Integer qYbLevel;

    @Schema(
            description = "预报水位预警级别名称"
    )
    @JsonProperty("q_yb_level_name")
    private String qYbLevelName;


}
