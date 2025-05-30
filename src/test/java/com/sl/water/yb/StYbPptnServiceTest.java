package com.sl.water.yb;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.water.entity.StYbPptn;
import com.sl.water.service.StYbPptnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StYbPptnServiceTest {
    @Autowired
    private StYbPptnService stYbPptnService;
    @Autowired
    private ObjectMapper objectMapper;
    // 预设的典型48小时降雨过程（示例模式：递增到峰值后递减）
    private static final List<Double> TYPICAL_RAINFALL = new ArrayList<>() {{
        // 前24小时：逐渐增加（峰值在第24小时）
        add(0.0);   add(0.3);   add(0.6);   add(1.0);   add(1.5);   add(2.1);   add(2.8);   add(3.6);
        add(4.5);   add(5.5);   add(6.6);   add(7.8);   add(9.1);   add(10.5);  add(12.0);  add(13.6);
        add(15.3);  add(17.1);  add(19.0);  add(21.0);  add(23.1);  add(25.3);  add(27.6);  add(30.0);  // 第24小时峰值30mm

        // 后24小时：逐渐减少（与前24小时对称）
        add(27.6);  add(25.3);  add(23.1);  add(21.0);  add(19.0);  add(17.1);  add(15.3);  add(13.6);
        add(12.0);  add(10.5);  add(9.1);   add(7.8);   add(6.6);   add(5.5);   add(4.5);   add(3.6);
        add(2.8);   add(2.1);   add(1.5);   add(1.0);   add(0.6);   add(0.3);   add(0.0);   add(0.0);
    }};

    /**
     * 按目标总降雨量缩放预设的典型降雨过程
     * @param targetTotal 目标总降雨量（mm，需>0）
     * @return 缩放后的48小时降雨过程列表（保留2位小数）
     * @throws IllegalArgumentException 当目标总降雨量≤0或预设列表总和为0时抛出
     */
    public static List<Double> generateScaledRainfall(double targetTotal) {
        // 校验目标总降雨量
        if (targetTotal <= 0) {
            throw new IllegalArgumentException("目标总降雨量必须大于0");
        }

        // 计算预设典型过程的总降雨量
        double baseTotal = TYPICAL_RAINFALL.stream().mapToDouble(Double::doubleValue).sum();
        if (baseTotal <= 0) {
            throw new IllegalArgumentException("预设典型降雨过程总降雨量异常（总和≤0）");
        }

        // 计算缩放因子
        double scaleFactor = targetTotal / baseTotal;

        // 对预设列表进行缩放并保留2位小数
        List<Double> scaledRainfall = new ArrayList<>();
        for (double rain : TYPICAL_RAINFALL) {
            double scaled = rain * scaleFactor;
            scaledRainfall.add(Math.round(scaled * 100.0) / 100.0);
        }

        return scaledRainfall;
    }
    @Test
    public void test() throws JsonProcessingException {
//        Double rainfallTotal = 1000.0;
        int totalRecords = 1000;  // 总记录数
        List<StYbPptn> pptnList = new ArrayList<>(totalRecords);
        LocalDateTime baseStartTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);  // 当前整点时间（去除分秒）
        for (int i = 0; i < totalRecords; i++) {
            // 生成20-120之间的随机降雨量（保留2位小数）
            double rainfallTotal = 20 + (120 - 20) * RandomUtil.randomDouble();
            rainfallTotal = Math.round(rainfallTotal * 100.0) / 100.0;

            // 计算当前记录的开始时间（基础时间 + i小时）
            LocalDateTime currentStartTime = baseStartTime.plusHours(i);
            LocalDateTime currentEndTime = currentStartTime.plusHours(48);  // 持续48小时

            // 生成缩放后的降雨过程数据
            List<Double> scaledRainfall = generateScaledRainfall(rainfallTotal);

            // 构建实体对象
            StYbPptn ybPptn = StYbPptn.builder()
                    .durationHours(48)
                    .rainfallData(objectMapper.writeValueAsString(scaledRainfall))
                    .startTime(currentStartTime)
                    .endTime(currentEndTime)
                    .createdAt(LocalDateTime.now())  // 或使用currentStartTime作为创建时间，根据需求调整
                    .build();

            pptnList.add(ybPptn);
        }

        stYbPptnService.saveBatch(pptnList);

    }
}
