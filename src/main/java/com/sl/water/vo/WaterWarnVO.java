package com.sl.water.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author duan
 * @description:水情预警
 */
@Data
@Schema(description = "水情预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WaterWarnVO {


    @Schema(
            description = "水库预警列表"
    )
    ReservoirWarnVO rsvr;

    @Schema(
            description = "河道预警列表"
    )
    List<RiverWarnVO> rivers;
}