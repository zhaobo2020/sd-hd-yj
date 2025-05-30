package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.dao.BThresholdWaterDao;
import com.sl.water.entity.BThresholdWater;
import com.sl.water.service.BThresholdWaterService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class BThresholdWaterServiceImpl extends ServiceImpl<BThresholdWaterDao, BThresholdWater> implements BThresholdWaterService {

    @Override
    public Page<BThresholdWater> pageList(PageQueryParam pageQueryParam) {
        Page<BThresholdWater> page = new Page<>(pageQueryParam.getPage(), pageQueryParam.getSize());
        return this.page(page);
    }

    @Override
    public BThresholdWater getByKindAndThreshold(String kind, double zYb) {

        LambdaQueryWrapper<BThresholdWater> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BThresholdWater::getKind, kind).le(BThresholdWater::getThreshold, zYb);
        List<BThresholdWater> list = list(lambdaQueryWrapper);
        BThresholdWater bThresholdWater = new BThresholdWater();
        BThresholdWater bThresholdWaterMax = list.stream().max(Comparator.comparingDouble(BThresholdWater::getThreshold)).orElse(null);
        if (bThresholdWaterMax != null) {
            bThresholdWater = bThresholdWaterMax;
        } else {
            bThresholdWater.setThreshold(0.0);
            bThresholdWater.setLevel(0);
            bThresholdWater.setName(null);
        }
        return bThresholdWater;
    }
}

