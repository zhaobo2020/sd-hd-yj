package com.sl.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeUtils {

    /**
     * 使用Stream生成逐小时时间序列
     *
     * @param start 起始时间
     * @param length 时间序列的长度（小时数）
     * @return 包含逐小时时间点的列表
     */
    public static List<LocalDateTime> generateHourlySequenceStream(LocalDateTime start, int length) {
        if (length <= 0) {
            return new ArrayList<>();
        }

        // 调整到下一个整点
        LocalDateTime adjustedStart = start.plusHours(1).truncatedTo(ChronoUnit.HOURS);

        return Stream.iterate(adjustedStart, time -> time.plusHours(1))
                .limit(length)
                .collect(Collectors.toList());
    }


}
