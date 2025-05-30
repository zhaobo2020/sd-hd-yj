package com.sl.water.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.water.dao.StYbPptnDao;
import com.sl.water.entity.StYbPptn;
import com.sl.water.entity.ThresholdRainfall;
import com.sl.water.service.RainFallYbService;
import com.sl.water.service.ThresholdRainfallService;
import com.sl.water.vo.RainfallYbVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @description:降雨预报接口
 * @author: duan
 * @time: 2025-05-23 11:36
 */
@Service
@RequiredArgsConstructor
public class RainFallYbServiceImpl extends ServiceImpl<StYbPptnDao, StYbPptn> implements RainFallYbService {
    private final ThresholdRainfallService thresholdRainfallService;

    @Override
    public RainfallYbVO getRainfallByDuration(LocalDateTime localDateTime, Integer duration) {

        // 获取当前的整点时间 根据这个时长，以 当前时间为 起点 去计算 时间的 开始时间  和 结束时间
        LocalDateTime endTime = localDateTime.plusHours(duration);
        LambdaQueryWrapper<StYbPptn> wrapper = Wrappers.lambdaQuery();
        //根据当前时间差当前小时的预报数据
        wrapper.eq(StYbPptn::getStartTime,
                LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER)
        );
        List<StYbPptn> list = list(wrapper);
        RainfallYbVO rainfallYbVO = new RainfallYbVO();
        if (list.size() == 0) {
            //没有预测数据
            rainfallYbVO.setRainfall(0.0)
                    .setDuration(duration)
                    .setStartTime(localDateTime)
                    .setEndTime(endTime)
                    .setLevel(1);
        } else {
            StYbPptn stYbPptn = list.get(0);
            String rainfallData = stYbPptn.getRainfallData();

            BigDecimal[] numbers = Arrays.stream(rainfallData.substring(1, rainfallData.length() - 1).split(",\\s*"))
                    .map(BigDecimal::new)
                    .toArray(BigDecimal[]::new);
            BigDecimal rainfall = Arrays.stream(numbers, 0, duration > 48 ? 48 : duration)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            rainfallYbVO.setRainfall(rainfall.doubleValue())
                    .setDuration(duration)
                    .setStartTime(localDateTime)
                    .setEndTime(endTime);
            if (rainfall.compareTo(BigDecimal.ZERO) > 0) {
                ThresholdRainfall thresholdRainfall = thresholdRainfallService.getLevelByRainFallAndDuration(rainfall.doubleValue(), duration);
                rainfallYbVO.setLevel(thresholdRainfall != null ? thresholdRainfall.getLevel() : 1)
                        .setLevelName(thresholdRainfall != null ? thresholdRainfall.getName() : null)
                        .setThreshold(thresholdRainfall != null ? thresholdRainfall.getRainfall() : 0.0);
            } else {
                rainfallYbVO.setLevel(0)
                        .setLevelName(null)
                        .setThreshold(0.0);
            }
        }
        return rainfallYbVO;
    }
}
