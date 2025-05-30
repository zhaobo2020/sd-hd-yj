package com.sl.plat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.common.ResponseData;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BThresholdWater;
import com.sl.water.service.BThresholdWaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plat/threshold/water")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = " 水情预警阀值", description = "提供水情预警阀值增、删、改、查、列表查询相关功能")
public class BThresholdWaterController {

    private final BThresholdWaterService bThresholdWaterService;

    @GetMapping("/pageList")
    @Operation(summary = "分页列表")
    public ResponseData<Page<BThresholdWater>> pageList(PageQueryParam pageQueryParam) {
        return ResponseData.success(bThresholdWaterService.pageList(pageQueryParam));
    }

    @GetMapping
    @Operation(summary = "列表")
    public ResponseData<List<BThresholdWater>> getList() {
        return ResponseData.success(bThresholdWaterService.list());
    }

    @PostMapping
    @Operation(summary = "新增")
    public ResponseData addThresholdRainfall(@RequestBody BThresholdWater bThresholdWater) {
        bThresholdWaterService.save(bThresholdWater);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public ResponseData removeBThresholdWater(@PathVariable long id) {
        bThresholdWaterService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改")
    public ResponseData updateBThresholdWater(@PathVariable long id, @RequestBody BThresholdWater bThresholdWater) {
        bThresholdWater.setId(id);
        bThresholdWaterService.updateById(bThresholdWater);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询")
    public ResponseData<BThresholdWater> getBThresholdWater(@PathVariable long id) {
        return ResponseData.success(bThresholdWaterService.getById(id));
    }

}