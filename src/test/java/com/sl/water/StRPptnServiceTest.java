package com.sl.water;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sl.plat.response.RainfallInfoResponse;
import com.sl.water.dto.RainListDTO;
import com.sl.water.entity.StRPptn;
import com.sl.water.service.StRPptnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class StRPptnServiceTest {
    @Autowired
    private  StRPptnService stRPptnService;

    @Test
    public void testGetRainByTime(){
        RainListDTO rainByDuration = stRPptnService.getRainByDuration("70311590", 5);
        System.out.println("rainByDuration = " + rainByDuration);
    }

    @Test
    public void testGetWLevelLimit() {
        StRPptn one = stRPptnService.getOne(new QueryWrapper<StRPptn>()
                .orderBy(true, false, "measurement_time")
                .eq("station_code", "70311590")
                .last("limit 1")
        );
        System.out.println("one.getMeasurementTime() = " + one.getMeasurementTime());
        LocalDateTime localDateTime = one.getMeasurementTime().minusYears(1);
        LocalDateTime of = LocalDateTime.of(2023, 1, 1, 0, 0);
        List<StRPptn> stationCode = stRPptnService.list(new QueryWrapper<StRPptn>()
                .orderBy(true, true, "measurement_time")
                        .gt("measurement_time", localDateTime )
                .eq("station_code", "70311590"));
//        System.out.println("stationCode = " + stationCode);
//        System.out.println("stationCode = " + stationCode);
            stRPptnService.removeBatchByIds(stationCode);
//        List<StRPptn> list = stationCode.stream().map(e ->
//                new StRPptn()
//                        .setRain(e.getRain())
//                        .setStationCode(e.getStationCode())
//                        .setMeasurementTime(e.getMeasurementTime().plusYears(1))
//                        .setIntervalLength(e.getIntervalLength())
//                        .setPrecipitationDuration(e.getPrecipitationDuration())
//                        .setUpdatedAt(LocalDateTime.now())
//                        .setCreatedAt(LocalDateTime.now())
//        ).toList();
//        System.out.println("list.size() = " + list.size());
//
//        stRPptnService.saveBatch(list);

    }
}
