package com.sl.water.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sl.water.entity.BThresholdWater;
import com.sl.water.entity.ForecastScheme;
import com.sl.water.entity.StReservoirFloodLimitWaterLevelB;
import com.sl.water.service.BThresholdWaterService;
import com.sl.water.service.ForecastSchemeService;
import com.sl.water.service.RiskWarnService;
import com.sl.water.service.StReservoirFloodLimitWaterLevelBService;
import com.sl.water.vo.ForecastSchemeVO;
import com.sl.water.vo.ReservoirLimitWarnVO;
import com.sl.water.vo.RiverLimitWarnVO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.*;

/**
 * @description:
 * @author: duan
 * @time: 2025-05-26 17:29
 */
@Service
@RequiredArgsConstructor
public class RiskWarnServiceImpl implements RiskWarnService {

    private final JdbcTemplate jdbcTemplate;
    private final StReservoirFloodLimitWaterLevelBService stReservoirFloodLimitWaterLevelBService;
    private final BThresholdWaterService bThresholdWaterService;
    private final ForecastSchemeService forecastSchemeService;

    @Override
    public ReservoirLimitWarnVO queryReservoirWarn() {
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        String nowDate = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER);
        String sql = "SELECT * FROM st_r_rsvr WHERE stcd=(SELECT station_code FROM st_station_b WHERE station_name='赵庄水库水位站') " +
                "AND tm<='" + nowDate + "' ORDER BY tm desc LIMIT 1";
        Map<String, Object> reservoirMap = this.jdbcTemplate.queryForMap(sql);
        ReservoirLimitWarnVO reservoirLimitWarnVO = new ReservoirLimitWarnVO();
        if (!Objects.isNull(reservoirMap)) {
            Double z = Double.parseDouble(reservoirMap.get("rz").toString());
            reservoirLimitWarnVO.setZ(z);
            List<StReservoirFloodLimitWaterLevelB> stReservoirFloodLimitWaterLevelBList = stReservoirFloodLimitWaterLevelBService.list(new LambdaQueryWrapper<StReservoirFloodLimitWaterLevelB>().orderByDesc(StReservoirFloodLimitWaterLevelB::getUpdateTime));
            if (stReservoirFloodLimitWaterLevelBList.size() > 0) {
                Double floodLimitWaterLevel = stReservoirFloodLimitWaterLevelBList.get(0).getFloodLimitWaterLevel();
                reservoirLimitWarnVO.setZLimit(floodLimitWaterLevel);
                BigDecimal dis = BigDecimal.valueOf(floodLimitWaterLevel).subtract(BigDecimal.valueOf(z));
                reservoirLimitWarnVO.setZDis(dis.doubleValue());
            }
            reservoirLimitWarnVO.setTm((LocalDateTime) reservoirMap.get("tm"));
        }
        return reservoirLimitWarnVO;

    }

    @Override
    public RiverLimitWarnVO queryRiverWarn(String stcd) {
        RiverLimitWarnVO riverLimitWarnVO = new RiverLimitWarnVO();
        String sql = "SELECT station_name FROM st_station_b WHERE station_code ='" + stcd + "'";
        Map<String, Object> station = this.jdbcTemplate.queryForMap(sql);
        if (station != null) {
            riverLimitWarnVO.setName(station.get("station_name").toString());
        }
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        String nowDate = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER);
        String riverSql = "SELECT * FROM st_r_river WHERE stcd='" + stcd + "'AND tm<='" + nowDate + "' ORDER BY tm desc LIMIT 1";
        Map<String, Object> riverMap = this.jdbcTemplate.queryForMap(riverSql);
        BigDecimal z = (BigDecimal) riverMap.get("z");
        riverLimitWarnVO.setZ(z);
        riverLimitWarnVO.setQ((BigDecimal) riverMap.get("q"));
        BThresholdWater bThresholdWater = bThresholdWaterService.list(new LambdaQueryWrapper<BThresholdWater>().eq(BThresholdWater::getKind, "河道水位").orderByDesc(BThresholdWater::getLevel)).stream().max(Comparator.comparingInt(BThresholdWater::getLevel)).orElse(null);
        if (bThresholdWater != null) {
            riverLimitWarnVO.setZLimit(BigDecimal.valueOf(bThresholdWater.getThreshold()));
            riverLimitWarnVO.setZDis(BigDecimal.valueOf(bThresholdWater.getThreshold()).subtract(z));
        }
        return riverLimitWarnVO;
    }

    @Override
    public JSONObject queryOverflowWarn() {
        String sql = "SELECT * FROM st_station_b WHERE station_name !='赵庄水库水位站'";
        List<Map<String, Object>> stationList = this.jdbcTemplate.queryForList(sql);
        JSONArray overflowArr = new JSONArray();
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        String nowDate = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER);
        int num = 0;
        for (Map<String, Object> stationMap : stationList) {
            String stationCode = stationMap.get("station_code").toString();
            String riverSql = "SELECT * FROM st_r_river WHERE stcd='" + stationCode + "'AND tm<='" + nowDate + "' ORDER BY tm desc LIMIT 1";
            Map<String, Object> riverMap = this.jdbcTemplate.queryForMap(riverSql);
            if (riverMap != null) {
                BigDecimal z = BigDecimal.valueOf(Double.parseDouble(riverMap.get("z").toString()));
                //对比【st_rvfcch_b】中的【ldkel左堤高程】【rdkel右堤高程】中的最小值，大于就属于漫溢点
                String rvfcchSql = "SELECT * FROM st_rvfcch_b WHERE (ldkel<'" + z + "' or rdkel<'" + z + "') AND stcd='" + stationCode + "'";
                List<Map<String, Object>> rvfcchList = jdbcTemplate.queryForList(rvfcchSql);
                if (rvfcchList.size() > 0) {
                    num++;
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.set("name", stationMap.get("station_name"));
                    jsonObject.set("x", stationMap.get("lon") != null ? stationMap.get("lon") : 0);
                    jsonObject.set("y", stationMap.get("lat") != null ? stationMap.get("lat") : 0);
                    overflowArr.add(jsonObject);
                }
            }
        }
        JSONObject result = new JSONObject();
        result.set("num", num);
        result.set("list", overflowArr);
        return result;
    }

    @Override
    public ForecastSchemeVO queryForecastWarn() {
        LambdaQueryWrapper<ForecastScheme> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ForecastScheme::getType, 1).eq(ForecastScheme::getStatus, 2).orderByDesc(ForecastScheme::getUpdatedTime);
        ForecastScheme forecastScheme = forecastSchemeService.getOne(lambdaQueryWrapper);
        JSONObject resultJson = JSONUtil.parseObj(forecastScheme.getResultJson());
        double maxWaterLevel = Double.parseDouble(resultJson.get("maxWaterLevel").toString());
        ForecastSchemeVO forecastSchemeVO = new ForecastSchemeVO();
        forecastSchemeVO.setZ(BigDecimal.valueOf(maxWaterLevel));
        return forecastSchemeVO;
    }
}
