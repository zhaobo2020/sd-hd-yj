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
public class RiverLimitWarnVO {
    @Schema(
            description = "名称"
    )
    private String name;

    @Schema(
            description = "当前水位"
    )
    private BigDecimal z;

    @Schema(
            description = "流量"
    )
    private BigDecimal q;


    @Schema(
            description = "防洪水位"
    )
    @JsonProperty("z_limit")
    private BigDecimal zLimit;

    @Schema(
            description = "距防洪水位"
    )
    @JsonProperty("z_dis")
    private BigDecimal zDis;

}
