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
 * @description:降雨预警
 * @author duan
 */
@Data
@Schema(description = "降雨预警")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RainfallVO {


    @Schema(
            description = "历史降雨列表"
    )
    List<RainfallHistoryVO> sc;

    @Schema(
            description = "预测降雨列表"
    )
    List<RainfallYbVO> yb;
}