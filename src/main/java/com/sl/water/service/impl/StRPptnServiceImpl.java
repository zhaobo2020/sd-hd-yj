package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.response.RainfallInfoResponse;
import com.sl.water.dto.RainListDTO;
import org.springframework.stereotype.Service;
import com.sl.water.entity.StRPptn;
import com.sl.water.dao.StRPptnDao;
import com.sl.water.service.StRPptnService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h2>降水量实时监测表 服务实现类</h2>
 * <p>实现类生成时间：2025-05-18 12:31:28</p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 *
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 12:31:28 - 创建类并生成表实体。</li>
 * </ul>
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-18
 */
@Service
public class StRPptnServiceImpl extends ServiceImpl<StRPptnDao, StRPptn> implements StRPptnService {

    //    目前默认的 降雨站点
    private static final String STATION_CODE = "70311590";

    @Override
    public RainListDTO getRainByDuration(String stationCode, Integer rainDuration) {
        //        获取当前的整点时间
//        根据这个 sc 时长，以 当前时间为 起点 去计算 时间的 开始时间  和 结束时间
        LocalDateTime endTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        LocalDateTime startTime = endTime.minusHours(rainDuration);
//        查询 这个时间段 的降雨 过程
        List<StRPptn> list = list(new QueryWrapper<StRPptn>()
                .between("measurement_time", startTime, endTime)
                .eq("station_code", STATION_CODE));
        RainListDTO response = new RainListDTO();
        response.setStartTime(list.get(0).getMeasurementTime())
                .setEndTime(list.get(list.size() - 1).getMeasurementTime())
                .setTimeProcess(list.stream().map(StRPptn::getMeasurementTime).toList())
                .setRainfallProcess(list.stream().map(StRPptn::getRain).toList())
                .setRainDuration(Double.valueOf(rainDuration))
                .setRainfallTotal(list.stream().mapToDouble(StRPptn::getRain).sum())
        ;
        return response;
    }

    @Override
    public StRPptn getFirstAfterTime(String stationCode, Long id, LocalDateTime measurementTime) {
        QueryWrapper<StRPptn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("station_code", STATION_CODE);
        queryWrapper.gt("id", id);
        queryWrapper.between("measurement_time", measurementTime, LocalDateTime.now());
        queryWrapper.orderByAsc("id").last("LIMIT 1");
        return this.getOne(queryWrapper);
    }
}

