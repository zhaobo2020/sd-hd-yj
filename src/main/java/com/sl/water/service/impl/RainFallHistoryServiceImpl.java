package com.sl.water.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.water.dao.StRPptnDao;
import com.sl.water.entity.ThresholdRainfall;
import com.sl.water.vo.RainfallHistoryVO;
import com.sl.water.entity.StRPptn;
import com.sl.water.service.RainFallHistoryService;
import com.sl.water.service.ThresholdRainfallService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author duan
 * @description:历史降雨量
 */
@Service
@RequiredArgsConstructor
public class RainFallHistoryServiceImpl extends ServiceImpl<StRPptnDao, StRPptn> implements RainFallHistoryService {
    //    目前默认的 降雨站点
    private static final String STATION_CODE = "70311590";
    private final ThresholdRainfallService thresholdRainfallService;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public RainfallHistoryVO getRainfallByDuration(LocalDateTime endTime, Integer duration) {

        // 获取当前的整点时间 根据这个时长，以 当前时间为 起点 去计算 时间的 开始时间  和 结束时间
        LocalDateTime startTime = endTime.minusHours(duration);

//        LambdaQueryWrapper<StRPptn> wrapper = Wrappers.lambdaQuery();
//        wrapper.between(StRPptn::getMeasurementTime,
//                        LocalDateTimeUtil.format(startTime, DatePattern.NORM_DATETIME_FORMATTER),
//                        LocalDateTimeUtil.format(endTime, DatePattern.NORM_DATETIME_FORMATTER))
//                .eq(StRPptn::getStationCode, STATION_CODE);
//        //查询 这个时间段 的降雨 过程
//        List<StRPptn> list = list(wrapper);
//        BigDecimal rainfall = list.stream()
//                .map(StRPptn::getRain)
//                .map(BigDecimal::valueOf)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
        String sql = "SELECT SUM(rain) AS rainfall FROM st_r_pptn WHERE station_code='" + STATION_CODE + "' " +
                "AND  measurement_time BETWEEN '" + startTime + "' AND '" + endTime + "'";
        double rainfall = Double.parseDouble(jdbcTemplate.queryForMap(sql).get("rainfall").toString());


        RainfallHistoryVO rainfallHistoryVO = new RainfallHistoryVO();
        rainfallHistoryVO.setStartTime(startTime)
                .setEndTime(endTime)
                .setDuration(duration)
                .setRainfall(rainfall);
        if (Double.compare(rainfall, 0) > 0) {
            //根据降雨量跟时长去b_threshold_rainfall查询出对应的级别
            ThresholdRainfall thresholdRainfall = thresholdRainfallService.getLevelByRainFallAndDuration(rainfall, duration);
            rainfallHistoryVO.setLevel(thresholdRainfall != null ? thresholdRainfall.getLevel() : 1)
                    .setLevelName(thresholdRainfall != null ? thresholdRainfall.getName() : null)
                    .setThreshold(thresholdRainfall != null ? thresholdRainfall.getRainfall() : 0.0);
        } else {
            rainfallHistoryVO.setLevel(0)
                    .setLevelName(null)
                    .setThreshold(0.0);
        }

        return rainfallHistoryVO;
    }
}