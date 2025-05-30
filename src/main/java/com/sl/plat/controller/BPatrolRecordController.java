package com.sl.plat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.common.ResponseData;
import com.sl.plat.req.BPatrolRecordRequest;
import com.sl.water.dto.BPatrolRecordStaDTO;
import com.sl.water.entity.BPatrolRecord;
import com.sl.water.service.BPatrolRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plat/patrol")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "人工巡查", description = "提供人工巡查增、删、改、查、列表查询相关功能")
public class BPatrolRecordController {

    private final BPatrolRecordService bPatrolRecordService;

    @GetMapping("/pageList")
    @Operation(summary = "分页列表")
    public ResponseData<Page<BPatrolRecord>> pageList(BPatrolRecordRequest pageQueryParam) {
        return ResponseData.success(bPatrolRecordService.pageList(pageQueryParam));
    }

    @GetMapping
    @Operation(summary = "列表")
    public ResponseData<List<BPatrolRecord>> getList(
            @Parameter(
                    description = "类型（语音，短信，图片，视频）",
                    required = false,
                    example = "语音"
            )
            @RequestParam(required = false) String kind) {
        return ResponseData.success(bPatrolRecordService.listByKind(kind));
    }

    @PostMapping
    @Operation(summary = "新增")
    public ResponseData addThresholdRainfall(@RequestBody BPatrolRecord bPatrolRecord) {
        bPatrolRecordService.save(bPatrolRecord);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public ResponseData removeBPatrolRecord(@PathVariable long id) {
        bPatrolRecordService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改")
    public ResponseData updateBPatrolRecord(@PathVariable long id, @RequestBody BPatrolRecord bPatrolRecord) {
        bPatrolRecord.setId(id);
        bPatrolRecordService.updateById(bPatrolRecord);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询")
    public ResponseData<BPatrolRecord> getBPatrolRecord(@PathVariable long id) {
        return ResponseData.success(bPatrolRecordService.getById(id));
    }

    @PutMapping("/updateState/{id}")
    @Operation(summary = "修改状态")
    public ResponseData updateBRiskTipState(@PathVariable long id, @RequestBody BPatrolRecord bPatrolRecord) {
        bPatrolRecordService.updateState(id, bPatrolRecord.getState());
        return ResponseData.success("修改成功");
    }

    @GetMapping("/sta")
    @Operation(summary = "统计")
    public ResponseData<BPatrolRecordStaDTO> sta() {
        return ResponseData.success(bPatrolRecordService.sta());
    }

}