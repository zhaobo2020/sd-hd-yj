package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.entity.ThresholdRainfall;


public interface ThresholdRainfallService extends IService<ThresholdRainfall> {

    ThresholdRainfall getLevelByRainFallAndDuration(double rainFall, Integer duration);
}

