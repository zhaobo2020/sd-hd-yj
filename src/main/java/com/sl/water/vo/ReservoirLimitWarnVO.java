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
@Schema(description = "水库限汛预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservoirLimitWarnVO {
    @Schema(
            description = "当前水位"
    )
    private Double z;

    @Schema(
            description = "讯限水位"
    )
    @JsonProperty("z_limit")
    private Double zLimit;

    @Schema(
            description = "距讯限水位"
    )
    @JsonProperty("z_dis")
    private Double zDis;

    @Schema(
            description = "监测时间",
            example = "2023-08-01T08:00:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime tm;

}
