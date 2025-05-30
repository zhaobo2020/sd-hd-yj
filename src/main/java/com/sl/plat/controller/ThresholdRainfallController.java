package com.sl.plat.controller;

import com.sl.common.ResponseData;
import com.sl.water.entity.ThresholdRainfall;
import com.sl.water.service.ThresholdRainfallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plat/threshold/rainfall")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "降雨阈值接口", description = "提供降雨阈值增、删、改、查、列表查询相关功能")
public class ThresholdRainfallController {
    private final ThresholdRainfallService thresholdRainfallService;

    @GetMapping
    @Operation(summary = "查询降雨阈值列表")
    public ResponseData<List<ThresholdRainfall>> getList() {
        return ResponseData.success(thresholdRainfallService.list());
    }

    @PostMapping
    @Operation(summary = "新增降雨阈值")
    public ResponseData addThresholdRainfall(@RequestBody ThresholdRainfall thresholdRainfall) {
        thresholdRainfallService.save(thresholdRainfall);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除降雨阈值")
    public ResponseData removeThresholdRainfall(@PathVariable long id) {
        thresholdRainfallService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改降雨阈值")
    public ResponseData updateThresholdRainfall(@PathVariable long id, @RequestBody ThresholdRainfall thresholdRainfall) {
        thresholdRainfall.setId(id);
        thresholdRainfallService.updateById(thresholdRainfall);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询降雨阈值")
    public ResponseData<ThresholdRainfall> getThresholdRainfall(@PathVariable long id) {
        thresholdRainfallService.getById(id);
        return ResponseData.success(thresholdRainfallService.getById(id));
    }
}
