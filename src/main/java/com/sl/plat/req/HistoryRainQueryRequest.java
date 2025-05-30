package com.sl.plat.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 历史降雨信息查询请求参数
 */
@Data
@Schema(description = "历史降雨信息查询请求参数（根据站点ID和降雨时长查询）")
public class HistoryRainQueryRequest {



    @Schema(
            description = "监测站点ID（唯一标识观测站点）",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "70311590"
    )
    @NotBlank(message = "监测站点ID不能为空")
    private String stationId;

    @Schema(
            description = "查询的降雨时长（单位：小时）",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "4.5",
            minimum = "0.1"
    )
    @Min(value = 0, message = "降雨时长必须大于0")
    @Positive(message = "降雨时长必须大于0")
    private Integer rainDuration;
}