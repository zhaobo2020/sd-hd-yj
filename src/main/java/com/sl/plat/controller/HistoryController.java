package com.sl.plat.controller;


import com.sl.common.ResponseData;
import com.sl.water.dto.RainListDTO;
import com.sl.water.service.StRPptnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/plat/yb")
@Tag(name = "历时数据接口", description = "提供历史数据查询相关功能")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j

public class HistoryController {
//    // 注入降雨信息服务类（根据实际项目调整Bean名称）
    private final StRPptnService stRPptnService;

    @GetMapping("/rain/history")
    @Operation(
            summary = "查询历史降雨数据",
            description = "根据监测站点ID和指定降雨时长查询历史降雨记录"
    )
    public ResponseData<RainListDTO> queryHistoryRain(
            @Parameter(
                    description = "监测站点ID（唯一标识观测站点）",
                    required = true,
                    example = "70311590"
            )
            @RequestParam @NotBlank(message = "监测站点ID不能为空") String stationId,

            @Parameter(
                    description = "查询的降雨时长（单位：小时）",
                    required = true,
                    example = "4.5",
                    schema = @Schema(minimum = "0.1")
            )
            @RequestParam @Min(value = 1, message = "降雨时长必须大于0") @Positive(message = "降雨时长必须大于0") Integer rainDuration
    ) {  // 自动绑定查询参数
        return ResponseData.success(stRPptnService.getRainByDuration(stationId , rainDuration));
    }

}