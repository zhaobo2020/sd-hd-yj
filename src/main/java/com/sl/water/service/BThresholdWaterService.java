package com.sl.water.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BThresholdWater;

public interface BThresholdWaterService extends IService<BThresholdWater> {

    Page<BThresholdWater> pageList(PageQueryParam pageQueryParam);

    /**
     * 通过类型跟流量查询阈值信息
     * @param kind
     * @param zYb
     * @return 阈值信息
     */
    BThresholdWater getByKindAndThreshold(String kind, double zYb);
}

