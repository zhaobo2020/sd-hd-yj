package com.sl.plat.controller;

import cn.hutool.json.JSONObject;
import com.sl.common.ResponseData;
import com.sl.water.service.RiskWarnService;
import com.sl.water.vo.ForecastSchemeVO;
import com.sl.water.vo.ReservoirLimitWarnVO;
import com.sl.water.vo.RiverLimitWarnVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: duan
 * @time: 2025-05-26 17:10
 */
@RestController
@RequestMapping("/plat/risk")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "风险预警接口", description = "提供风险预警查询相关功能")
public class RiskController {
    private final RiskWarnService riskWarnService;

    @GetMapping("/rsvr_warn")
    @Operation(summary = "查询水库监测风险预警")
    public ResponseData<ReservoirLimitWarnVO> queryReservoirWarn() {
        return ResponseData.success(riskWarnService.queryReservoirWarn());
    }

    @GetMapping("/river_warn")
    @Operation(summary = "查询河道风险预警")
    public ResponseData<RiverLimitWarnVO> queryRiverWarn(@Parameter(
            description = "站点ID",
            required = true,
            example = "1"
    )
                                                         @RequestParam @NotBlank(message = "站点ID不能为空") String stcd) {
        return ResponseData.success(riskWarnService.queryRiverWarn(stcd));
    }

    @GetMapping("/myd_warn")
    @Operation(summary = "查询漫溢点风险预警")
    public ResponseData<JSONObject> queryOverflowWarn() {
        return ResponseData.success(riskWarnService.queryOverflowWarn());
    }

    @GetMapping("/yb_warn")
    @Operation(summary = "查询预报预警", description = "对应关系待确认，接口待完善")
    public ResponseData<ForecastSchemeVO> queryForecastWarn() {
        return ResponseData.success(riskWarnService.queryForecastWarn());
    }
}
