package com.sl.water;

import com.sl.water.service.StReservoirFloodLimitWaterLevelBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StReservoirFloodLimitWaterLevelBServiceTest {
    @Autowired
    private StReservoirFloodLimitWaterLevelBService stReservoirFloodLimitWaterLevelBService;

    @Test
    public void testGetWLevelLimit() {
        Double wLevelLimit = stReservoirFloodLimitWaterLevelBService.getWLevelLimit("1");
        System.out.println(wLevelLimit);
    }
}
