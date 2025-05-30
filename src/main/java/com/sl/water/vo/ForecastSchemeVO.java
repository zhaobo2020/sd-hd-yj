package com.sl.water.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: duan
 * @time: 2025-05-27 15:01
 */
@Data
@Schema(description = "预报预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ForecastSchemeVO {
    @Schema(
            description = "水库水位"
    )
    private BigDecimal z;

    @Schema(
            description = "水库最大下泄量"
    )
    private BigDecimal xxl;

    @Schema(
            description = "入流流量"
    )
    private BigDecimal q;

    @Schema(
            description = "满溢点"
    )
    @JsonProperty("myd_list")
    private List mydList;

    @Schema(
            description = "受淹面积"
    )
    private BigDecimal symj;

    @Schema(
            description = "满溢点"
    )
    @JsonProperty("whqy_list")
    private List whqyList;
}
