package com.sl.plat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.common.ResponseData;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskTip;
import com.sl.water.service.BRiskTipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plat/risk/tip")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "风险提示单", description = "提供风险提示单增、删、改、查、列表查询相关功能")
public class BRiskTipController {

    private final BRiskTipService bRiskTipService;

    @GetMapping("/pageList")
    @Operation(summary = "分页列表")
    public ResponseData<Page<BRiskTip>> pageList(PageQueryParam pageQueryParam) {
        return ResponseData.success(bRiskTipService.pageList(pageQueryParam));
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    public ResponseData<List<BRiskTip>> getList() {
        return ResponseData.success(bRiskTipService.list());
    }

    @PostMapping
    @Operation(summary = "新增")
    public ResponseData addThresholdRainfall(@RequestBody BRiskTip bRiskTip) {
        bRiskTipService.save(bRiskTip);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public ResponseData removeBRiskTip(@PathVariable long id) {
        bRiskTipService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改")
    public ResponseData updateBRiskTip(@PathVariable long id, @RequestBody BRiskTip bRiskTip) {
        bRiskTip.setId(id);
        bRiskTipService.updateById(bRiskTip);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询")
    public ResponseData<BRiskTip> getBRiskTip(@PathVariable long id) {
        return ResponseData.success(bRiskTipService.getById(id));
    }

    @PutMapping("/updateState/{id}")
    @Operation(summary = "修改状态")
    public ResponseData updateBRiskTipState(@PathVariable long id, @RequestBody BRiskTip bRiskTip) {
        bRiskTipService.updateState(id,bRiskTip.getState());
        return ResponseData.success("修改成功");
    }

}