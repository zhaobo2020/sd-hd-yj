package com.sl.plat.controller;

import com.sl.common.ResponseData;
import com.sl.water.service.*;
import com.sl.water.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duan
 */
@RestController
@RequestMapping("/plat/warn")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "降雨及水情预警接口", description = "提供降雨及水情预警查询相关功能")
public class WarnController {
    private final int[] RAIN_DURATION_LIST = {1, 3, 6, 12, 24, 36, 48, 72};
    private final RainFallHistoryService rainFallHistoryService;
    private final RainFallYbService rainFallYbService;
    private final WaterWarnService waterWarnService;

    @GetMapping("/rainfall")
    @Operation(summary = "查询时段内的累计降雨量情况及降雨预报")
    public ResponseData<RainfallVO> getRainfall() {
        List<RainfallHistoryVO> historyRainList = new ArrayList();
        List<RainfallYbVO> ybRainList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        for (int rainDuration : RAIN_DURATION_LIST) {
            historyRainList.add(rainFallHistoryService.getRainfallByDuration(localDateTime, rainDuration));
            ybRainList.add(rainFallYbService.getRainfallByDuration(localDateTime, rainDuration));
        }
        RainfallVO rainfallVO = new RainfallVO(historyRainList, ybRainList);
        return ResponseData.success(rainfallVO);
    }

    @GetMapping("/water")
    @Operation(summary = "查询水情预警情况包含水库跟河道")
    public ResponseData<WaterWarnVO> getWaterWarn() {

        ReservoirWarnVO reservoirWarnVO = waterWarnService.getReservoirWarnVO();
        List<RiverWarnVO> riverWarnVOList = waterWarnService.getRiverWarnVOList();
        WaterWarnVO waterWarnVO = new WaterWarnVO(reservoirWarnVO, riverWarnVOList);
        return ResponseData.success(waterWarnVO);
    }
}
