package com.sl.plat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.common.ResponseData;
import com.sl.common.exception.GlobalErrorCodeEnum;
import com.sl.common.exception.SchemeNotFoundException;
import com.sl.model.dto.ForecastResultDTO;
import com.sl.model.enums.ModelCalculationType;
import com.sl.model.enums.ProcessStatus;
import com.sl.plat.req.ForecastModelRequest;
import com.sl.water.entity.ForecastScheme;
import com.sl.water.service.ForecastSchemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plat/xaj/forecast")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "模型接口", description = "提供历史数据查询相关功能")
public class forecastController {

    private final ObjectMapper objectMapper;

    private final ForecastSchemeService forecastSchemeService;


    /**
     * 实测降雨预报模型计算接口
     * <p>接收实测降雨数据及模型参数，触发预报模型计算，返回未来时段降雨预报结果</p>
     *
     * @param request 实测降雨预报计算请求（包含实测数据、模型配置和预报参数）
     * @return 未来各时段降雨预报结果（单位：mm，按时间顺序排列）
     */
    @PostMapping("/history")
    @Operation(summary = "实测过程模型计算",
            description = "接收完整的实测过程参数，返回模型计算结果列表")
    public ResponseData<Long> calculateHistory(
            @Valid @RequestBody ForecastModelRequest request) throws JsonProcessingException {
        ForecastScheme forecastScheme = ForecastScheme.builder()
                .type(ModelCalculationType.MEASURED_PROCESS.getCode())
                .name(request.getSchemeName())
                .paramsJson(objectMapper.writeValueAsString(request))
                .status(ProcessStatus.WAITING.getCode())
                .build();
        boolean save = forecastSchemeService.save(forecastScheme);
        return ResponseData.success(forecastScheme.getId());
    }
    // 根据 方案id获取方案结果
    @GetMapping("/result/{id}")
    @Operation(summary = "根据方案id获取方案结果")
    public ResponseData<ForecastResultDTO> getResultById(@PathVariable("id") Long id) throws JsonProcessingException {
        ForecastScheme forecastScheme = forecastSchemeService.getById(id);
        if (forecastScheme.getStatus() == ProcessStatus.SUCCESS.getCode()){
            ForecastResultDTO resultDTO = objectMapper.readValue(forecastScheme.getResultJson(), ForecastResultDTO.class);
            return ResponseData.success(resultDTO);
        }else {
            return ResponseData.error(GlobalErrorCodeEnum.SCHEME_NOT_READY);
        }

    }

//     根据id获取计算状态
    @GetMapping("/status/{id}")
    @Operation(summary = "根据方案id获取计算状态")
    public ResponseData<Integer> getStatusById(@PathVariable("id") Long id) {

        return  forecastSchemeService.getOptById(id)
                .map(scheme -> ResponseData.success(scheme.getStatus()))
                .orElseThrow(() -> new SchemeNotFoundException("方案不存在，id: " + id));
    }


}