package com.sl.water;

import com.sl.water.entity.StReservoirFloodIndicatorB;
import com.sl.water.service.StReservoirFloodIndicatorBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class StReservoirFloodIndicatorBServiceTest {
    @Autowired
    private StReservoirFloodIndicatorBService reservoirFloodIndicatorBService;

    @Test
    public void testGetFloodIndicator() {
        reservoirFloodIndicatorBService.getOptById(1).ifPresentOrElse(
                dto -> System.out.println("查询成功：" + dto),
                () -> System.out.println("查询失败：ID=1的记录不存在")
        );

    }
}
