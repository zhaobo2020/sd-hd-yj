package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.water.dao.ThresholdRainfallDao;
import com.sl.water.entity.ThresholdRainfall;
import com.sl.water.service.ThresholdRainfallService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


/**
 * @author duan
 * @description:降雨阈值
 */
@Service
public class ThresholdRainfallServiceImpl extends ServiceImpl<ThresholdRainfallDao, ThresholdRainfall> implements ThresholdRainfallService {

    @Override
    public ThresholdRainfall getLevelByRainFallAndDuration(double rainFall, Integer duration) {

        duration = duration > 24 ? 24 : duration;
        List<ThresholdRainfall> list = list(new QueryWrapper<ThresholdRainfall>()
                .le("rainfall", rainFall)
                .eq("duration", duration));
        ThresholdRainfall thresholdRainfallMax = list.stream()
                .max(Comparator.comparingDouble(ThresholdRainfall::getRainfall)).orElse(null);

        ThresholdRainfall thresholdRainfall = new ThresholdRainfall();
        if (thresholdRainfallMax != null) {
            thresholdRainfall = thresholdRainfallMax;
        } else {
            thresholdRainfall.setRainfall(0.0);
            thresholdRainfall.setLevel(0);
            thresholdRainfall.setName(null);
        }
        return thresholdRainfall;
    }
}

