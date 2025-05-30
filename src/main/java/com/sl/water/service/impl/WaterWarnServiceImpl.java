package com.sl.water.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sl.water.entity.BThresholdWater;
import com.sl.water.entity.ForecastScheme;
import com.sl.water.service.BThresholdWaterService;
import com.sl.water.service.ForecastSchemeService;
import com.sl.water.service.WaterWarnService;
import com.sl.water.vo.ReservoirWarnVO;
import com.sl.water.vo.RiverWarn;
import com.sl.water.vo.RiverWarnVO;
import com.sl.water.vo.RsvrWarn;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: duan
 * @time: 2025-05-26 15:05
 */
@Service
@RequiredArgsConstructor
public class WaterWarnServiceImpl implements WaterWarnService {

    private final JdbcTemplate jdbcTemplate;
    private final ForecastSchemeService forecastSchemeService;
    private final BThresholdWaterService bThresholdWaterService;
    private final String[] STATIONS = {"吴家闸", "刘家闸"};

    @Override
    public ReservoirWarnVO getReservoirWarnVO() {
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        String nowDate = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER);
        String sql = "SELECT * FROM st_r_rsvr WHERE stcd=(SELECT station_code FROM st_station_b WHERE station_name='赵庄水库水位站') " +
                "AND tm<='" + nowDate + "' ORDER BY tm desc LIMIT 1";
        Map<String, Object> reservoirMap = this.jdbcTemplate.queryForMap(sql);
        //查询水库水情预警情
        ReservoirWarnVO reservoirWarnVO = new ReservoirWarnVO();
        if (!Objects.isNull(reservoirMap)) {
            Double z = Double.parseDouble(reservoirMap.get("rz").toString());
            Double otq = Double.parseDouble(reservoirMap.get("otq").toString());
            reservoirWarnVO.setZ(z);
            reservoirWarnVO.setInq(Double.parseDouble(reservoirMap.get("inq").toString()));
            reservoirWarnVO.setOtq(otq);
            //去forecastScheme表中查询预报水位
            Double zYb = this.getMaxWaterLevel();
            reservoirWarnVO.setZYb(zYb);
            //根据水库水位查询预警级别
            BThresholdWater zBThresholdWater = bThresholdWaterService.getByKindAndThreshold("水库水位", z);
            //根据预报水位查询预警级别
            BThresholdWater ybBThresholdWater = bThresholdWaterService.getByKindAndThreshold("水库水位", zYb);
            //根据出库流量查询预警级别
            BThresholdWater otpBThresholdWater = bThresholdWaterService.getByKindAndThreshold("水库出库流量", otq);
            reservoirWarnVO.setZLevel(zBThresholdWater.getLevel());
            reservoirWarnVO.setZLevelName(zBThresholdWater.getName());
            reservoirWarnVO.setZYbLevel(ybBThresholdWater.getLevel());
            reservoirWarnVO.setZYbLevelName(ybBThresholdWater.getName());
            reservoirWarnVO.setOtqLevel(otpBThresholdWater.getLevel());
            reservoirWarnVO.setOtqLevelName(otpBThresholdWater.getName());
        }
        return reservoirWarnVO;
    }

    private Double getMaxWaterLevel() {
        LambdaQueryWrapper<ForecastScheme> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ForecastScheme::getType, 1).eq(ForecastScheme::getStatus, 2).orderByDesc(ForecastScheme::getUpdatedTime);
        ForecastScheme forecastScheme = forecastSchemeService.getOne(lambdaQueryWrapper);
        JSONObject resultJson = JSONUtil.parseObj(forecastScheme.getResultJson());
        return Double.parseDouble(resultJson.get("maxWaterLevel").toString());
    }

    @Override
    public List<RiverWarnVO> getRiverWarnVOList() {
        List<RiverWarnVO> riverWarnVOList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        String nowDate = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER);
        for (String station : STATIONS) {

            String sql = "SELECT * FROM st_r_river WHERE stcd=(SELECT station_code FROM st_station_b WHERE " +
                    "station_name='" + station + "') AND tm<='" + nowDate + "' ORDER BY tm desc LIMIT 1";
            Map<String, Object> riverMap = this.jdbcTemplate.queryForMap(sql);
            if (!Objects.isNull(riverMap)) {
                RiverWarnVO riverWarnVO = new RiverWarnVO();
                riverWarnVO.setName(riverMap.get("stcd").toString());
                Double z = Double.parseDouble(riverMap.get("z").toString());
                Double q = Double.parseDouble(riverMap.get("q").toString());
                //去forecastScheme表中查询预报水位
                Double qYb = this.getMaxWaterLevel();
                riverWarnVO.setZ(z);
                riverWarnVO.setQ(q);
                riverWarnVO.setQYb(qYb);
                //根据水位查询预警级别
                BThresholdWater zBThresholdWater = bThresholdWaterService.getByKindAndThreshold("河道水位", z);
                //根据预报水位查询预警级别
                BThresholdWater qYbBThresholdWater = bThresholdWaterService.getByKindAndThreshold("河道流量", qYb);
                //根据河道流量查询预警级别
                BThresholdWater qBThresholdWater = bThresholdWaterService.getByKindAndThreshold("河道流量", q);
                riverWarnVO.setZLevel(zBThresholdWater.getLevel());
                riverWarnVO.setZLevelName(zBThresholdWater.getName());
                riverWarnVO.setQLevel(qBThresholdWater.getLevel());
                riverWarnVO.setQLevelName(qBThresholdWater.getName());
                riverWarnVO.setQYbLevel(qYbBThresholdWater.getLevel());
                riverWarnVO.setQYbLevelName(qYbBThresholdWater.getName());
                riverWarnVOList.add(riverWarnVO);
            }
        }
        return riverWarnVOList;
    }

    @Override
    public RsvrWarn getRsvrWarnAfterTime(Long id, LocalDateTime time) {
        //查询水库水情预警情
        RsvrWarn rsvrWarn = new RsvrWarn();
        String startTime = LocalDateTimeUtil.format(time, DatePattern.NORM_DATETIME_FORMATTER);
        String endTime = LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_FORMATTER);
        String sql = "SELECT id,tm,rz,otq FROM st_r_rsvr WHERE id>" + id + " and tm between '" + startTime + "' and  '" + endTime + "' ORDER BY id asc LIMIT 1";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        if (list.size() < 1) {
            return null;
        }
        Map<String, Object> rsvrMap = list.get(0);
        if (!Objects.isNull(rsvrMap)) {
            Double z = Double.parseDouble(String.valueOf(rsvrMap.get("rz")));
            Double otq = Double.parseDouble(String.valueOf(rsvrMap.get("otq")));
            rsvrWarn.setId(Long.parseLong(String.valueOf(rsvrMap.get("id"))));
            rsvrWarn.setTm(LocalDateTime.parse(String.valueOf(rsvrMap.get("tm"))));
            rsvrWarn.setZ(z);
            rsvrWarn.setOtq(otq);
            //根据水库水位查询预警级别
            BThresholdWater zBThresholdWater = bThresholdWaterService.getByKindAndThreshold("水库水位", z);
            //根据出库流量查询预警级别
            BThresholdWater otpBThresholdWater = bThresholdWaterService.getByKindAndThreshold("水库出库流量", otq);
            rsvrWarn.setZLevel(zBThresholdWater.getLevel());
            rsvrWarn.setZLevelName(zBThresholdWater.getName());
            rsvrWarn.setZThreshold(zBThresholdWater.getThreshold());
            rsvrWarn.setOtqLevel(otpBThresholdWater.getLevel());
            rsvrWarn.setOtqLevelName(otpBThresholdWater.getName());
            rsvrWarn.setOtqThreshold(otpBThresholdWater.getThreshold());
        }
        return rsvrWarn;
    }

    @Override
    public RiverWarn getRiverWarnAfterTime(Long id, LocalDateTime time) {
        RiverWarn riverWarn = new RiverWarn();
        String startTime = LocalDateTimeUtil.format(time, DatePattern.NORM_DATETIME_FORMATTER);
        String endTime = LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_FORMATTER);
        String sql = "SELECT id,tm,z,q,(SELECT station_name FROM st_station_b s WHERE  s.station_code=r.stcd) as n FROM st_r_river r where id>" + id + " and tm between '" + startTime + "' and  '" + endTime + "' ORDER BY id asc LIMIT 1";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        if (list.size() < 1) {
            return null;
        }
        Map<String, Object> riverMap = list.get(0);
        if (!Objects.isNull(riverMap)) {
            riverWarn.setId(Long.parseLong(String.valueOf(riverMap.get("id"))));
            riverWarn.setName(riverMap.get("n").toString());
            riverWarn.setTm(LocalDateTime.parse(String.valueOf(riverMap.get("tm"))));
            Double z = Double.parseDouble(riverMap.get("z").toString());
            Double q = Double.parseDouble(riverMap.get("q").toString());
            riverWarn.setZ(z);
            riverWarn.setQ(q);
            //根据水位查询预警级别
            BThresholdWater zBThresholdWater = bThresholdWaterService.getByKindAndThreshold("河道水位", z);
            //根据河道流量查询预警级别
            BThresholdWater qBThresholdWater = bThresholdWaterService.getByKindAndThreshold("河道流量", q);
            riverWarn.setZLevel(zBThresholdWater.getLevel());
            riverWarn.setZLevelName(zBThresholdWater.getName());
            riverWarn.setZThreshold(zBThresholdWater.getThreshold());
            riverWarn.setQLevel(qBThresholdWater.getLevel());
            riverWarn.setQLevelName(qBThresholdWater.getName());
            riverWarn.setQThreshold(qBThresholdWater.getThreshold());
        }
        return riverWarn;
    }
}
