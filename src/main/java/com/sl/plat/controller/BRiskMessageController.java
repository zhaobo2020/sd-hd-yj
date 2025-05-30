package com.sl.plat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.common.ResponseData;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskMessage;
import com.sl.water.service.BRiskMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plat/risk/warn/message")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "预警管理", description = "提供预警增、删、改、查、列表查询相关功能")
public class BRiskMessageController {

    private final BRiskMessageService bRiskMessageService;

    @GetMapping("/pageList")
    @Operation(summary = "分页列表")
    public ResponseData<Page<BRiskMessage>> pageList(PageQueryParam pageQueryParam) {
        return ResponseData.success(bRiskMessageService.pageList(pageQueryParam));
    }

    @GetMapping
    @Operation(summary = "列表")
    public ResponseData<List<BRiskMessage>> getList() {
        return ResponseData.success(bRiskMessageService.list());
    }

    @PostMapping
    @Operation(summary = "新增")
    public ResponseData addThresholdRainfall(@RequestBody BRiskMessage bRiskMessage) {
        bRiskMessageService.save(bRiskMessage);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public ResponseData removeBRiskMessage(@PathVariable long id) {
        bRiskMessageService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改")
    public ResponseData updateBRiskMessage(@PathVariable long id, @RequestBody BRiskMessage bRiskMessage) {
        bRiskMessage.setId(id);
        bRiskMessageService.updateById(bRiskMessage);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询")
    public ResponseData<BRiskMessage> getBRiskMessage(@PathVariable long id) {
        return ResponseData.success(bRiskMessageService.getById(id));
    }

}