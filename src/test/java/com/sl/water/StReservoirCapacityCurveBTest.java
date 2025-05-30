package com.sl.water;

import com.sl.water.dto.ReservoirWCurveDTO;
import com.sl.water.entity.StReservoirCapacityCurveB;
import com.sl.water.service.StReservoirCapacityCurveBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
public class StReservoirCapacityCurveBTest {
    @Autowired
    private StReservoirCapacityCurveBService service;


    @Test
    public void testSave() {
        String  filePath  = "D:\\2——code\\sd\\sd_hd\\src\\test\\resources\\krqx";
        ArrayList<StReservoirCapacityCurveB> curveBS = new ArrayList<>();
        // 使用 try-with-resources 自动关闭文件流
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(filePath),
                StandardCharsets.UTF_8)) {

            String line;
            int lineNum = 0;  // 记录当前行号（用于错误提示）
            int index = 1;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();  // 去除行首尾空白

                // 跳过空行
                if (line.isEmpty()) {
                    continue;
                }

                // 按制表符分割数据（\\t 转义为实际制表符）
                String[] parts = line.split("\\t");
                if (parts.length != 2) {
                    throw new RuntimeException(
                            "第 " + lineNum + " 行格式错误，需包含水位和库容两列（用制表符分隔）");
                }

                try {
                    // 解析水位（可能是小数，如49.3）
                    double waterLevel = Double.parseDouble(parts[0]);
                    // 解析库容（用户数据中是整数，用long存储）
                    double capacity = Long.parseLong(parts[1]);
                    StReservoirCapacityCurveB curveB = new StReservoirCapacityCurveB();
                    curveB.setReservoirLevel(waterLevel)
                            .setStationCode("1")
                            .setMeasurementTime(LocalDateTime.now())
                            .setPointNumber(index)
                            .setUpdateTime(LocalDateTime.now())
                            .setStorageVolume(capacity / 10000.0);
                    curveBS.add(curveB);
                    index = index +1;
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "第 " + lineNum + " 行数值格式错误: " + e.getMessage());
                }
            }

            if (curveBS.size() > 0){
                service.saveBatch(curveBS);
            }
        } catch (Exception e) {
            throw new RuntimeException("读取文件失败: " + e.getMessage(), e);
        }
    }

    @Test
    public void testGetCurve() {
        String stationCode = "1";
        ReservoirWCurveDTO curve = service.getCurve(stationCode);
        System.out.println(curve);
    }

}
