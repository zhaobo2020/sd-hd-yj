package com.sl.water.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskTip;

public interface BRiskTipService extends IService<BRiskTip> {

    Page<BRiskTip> pageList(PageQueryParam pageQueryParam);

    boolean updateState(Long id, String state);
}

