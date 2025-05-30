package com.sl.plat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.common.ResponseData;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskPerson;
import com.sl.water.service.BRiskPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plat/risk/warn/person")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "预警人员管理", description = "提供预警人员增、删、改、查、列表查询相关功能")
public class BRiskPersonController {

    private final BRiskPersonService bRiskPersonService;

    @GetMapping("/pageList")
    @Operation(summary = "分页列表")
    public ResponseData<Page<BRiskPerson>> pageList(PageQueryParam pageQueryParam) {
        return ResponseData.success(bRiskPersonService.pageList(pageQueryParam));
    }

    @GetMapping
    @Operation(summary = "列表")
    public ResponseData<List<BRiskPerson>> getList() {
        return ResponseData.success(bRiskPersonService.list());
    }

    @PostMapping
    @Operation(summary = "新增")
    public ResponseData addThresholdRainfall(@RequestBody BRiskPerson bRiskPerson) {
        bRiskPersonService.save(bRiskPerson);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public ResponseData removeBRiskTip(@PathVariable long id) {
        bRiskPersonService.removeById(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改")
    public ResponseData updateBRiskTip(@PathVariable long id, @RequestBody BRiskPerson bRiskPerson) {
        bRiskPerson.setId(id);
        bRiskPersonService.updateById(bRiskPerson);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询")
    public ResponseData<BRiskPerson> getBRiskTip(@PathVariable long id) {
        return ResponseData.success(bRiskPersonService.getById(id));
    }

}