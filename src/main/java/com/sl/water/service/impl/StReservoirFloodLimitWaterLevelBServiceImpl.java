package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.common.exception.internal.DataParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sl.water.entity.StReservoirFloodLimitWaterLevelB;
import com.sl.water.dao.StReservoirFloodLimitWaterLevelBDao;
import com.sl.water.service.StReservoirFloodLimitWaterLevelBService;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * <h2>库（湖）站汛限水位表，用于存储水库（湖）安全运行限制水位信息 服务实现类</h2>
 * <p>实现类生成时间：2025-05-18 10:16:23</p>
 * 
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * 
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 10:16:23 - 创建类并生成表实体。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-05-18
 * @version 1.0.0
 */
@Service
@Slf4j
public class StReservoirFloodLimitWaterLevelBServiceImpl extends ServiceImpl<StReservoirFloodLimitWaterLevelBDao, StReservoirFloodLimitWaterLevelB> implements StReservoirFloodLimitWaterLevelBService {


    /**
     * 获取指定测站的当前汛限水位
     *
     * @param stationCode 测站编码（必填）
     * @return 当前适用的汛限水位值（单位：米）
     * @throws DataParseException 当未找到有效汛限数据时抛出
     * @throws RuntimeException 当日期解析异常时抛出运行时异常
     */
    public Double getWLevelLimit(String stationCode) {
        // Step 1: 查询数据库获取所有汛限规则
        List<StReservoirFloodLimitWaterLevelB> rules = list(new QueryWrapper<StReservoirFloodLimitWaterLevelB>()
                .eq("station_code", stationCode));

        // Step 2: 处理查询结果
        Optional<Double> limitLevel = findApplicableRule(rules, LocalDate.now());

        // Step 3: 确定最终结果
        return limitLevel.orElseThrow(() ->
                new DataParseException("没找找到对应的汛线水位值 " + stationCode));
    }


    /**
     * 在给定的规则列表中查找适用于当前日期的汛限水位。
     * 如果找到匹配的规则，则返回对应的汛限水位；否则，返回所有规则中的最小汛限水位。
     *
     * <p>解决的问题：
     * 该方法旨在根据当前日期匹配适用的汛限水位规则，以确定当前的汛限水位。
     * 需要处理日期区间的匹配，包括跨年的情况，并在无匹配规则时提供默认值。
     *
     * <p>实现思路：
     * <ul>
     *   <li>遍历所有规则，逐一检查当前日期是否在规则的日期区间内。</li>
     *   <li>解析每条规则的开始和结束日期，支持跨年区间的判断。</li>
     *   <li>如果当前日期在某个规则的区间内，返回该规则的汛限水位。</li>
     *   <li>如果遍历完所有规则后没有匹配项，返回所有规则中的最小汛限水位。</li>
     * </ul>
     *
     * <p>输入：
     * <ul>
     *   <li>{@code rules}：包含多个{@link StReservoirFloodLimitWaterLevelB}对象的列表，每个对象定义了汛限水位的规则。</li>
     *   <li>{@code currentDate}：当前的日期，用于匹配规则。</li>
     * </ul>
     *
     * <p>输出：
     * <ul>
     *   <li>返回一个{@link Optional<Double>}，如果找到匹配的规则，则包含对应的汛限水位；否则为空。</li>
     *   <li>如果无匹配规则，最终返回所有规则中的最小汛限水位。</li>
     * </ul>
     *
     * <p>可能的异常：
     * <ul>
     *   <li>{@link DateTimeParseException}：日期格式解析错误，记录错误日志并继续处理下一个规则。</li>
     *   <li>{@link IllegalArgumentException}：日期格式不合法，抛出异常并终止方法。</li>
     * </ul>
     */
    private Optional<Double> findApplicableRule(List<StReservoirFloodLimitWaterLevelB> rules, LocalDate currentDate) {
        for (StReservoirFloodLimitWaterLevelB rule : rules) {
            try {
                // 解析日期边界
                MonthDay start = parseDate(rule.getBeginMonthDay());
                MonthDay end = parseDate(rule.getEndMonthDay());

                // 判断日期区间包含关系（支持跨年）
                if (isDateInRange(currentDate, start, end)) {
                    return Optional.of(rule.getFloodLimitWaterLevel());
                }
            } catch (DateTimeParseException e) {
                log.error("非法的时间写入: {}", rule, e);
            }
        }

        // 如果没有匹配规则，返回所有规则的最小值
        return rules.stream()
                .map(StReservoirFloodLimitWaterLevelB::getFloodLimitWaterLevel)
                .min(Comparator.comparing(Double::doubleValue));
    }

    /**
     * 解析月份的日（MMDD格式）字符串为{@link MonthDay}对象。
     *
     * <p>解决的问题：
     * 将MMDD格式的字符串转换为Java的{@link MonthDay}对象，以便进行日期比较。
     *
     * <p>实现思路：
     * <ul>
     *   <li>验证输入字符串是否为非空且长度为4。</li>
     *   <li>使用{@link DateTimeFormatter}按照"MMdd"模式解析字符串。</li>
     * </ul>
     *
     * <p>输入：
     * <ul>
     *   <li>{@code mmddStr}：表示月份的日的字符串，格式为"MMDD"。</li>
     * </ul>
     *
     * <p>输出：
     * <ul>
     *   <li>{@link MonthDay}对象，表示解析后的月份和日。</li>
     * </ul>
     *
     * <p>可能的异常：
     * <ul>
     *   <li>{@link IllegalArgumentException}：输入字符串为空、长度不为4或格式不正确。</li>
     * </ul>
     */
    private MonthDay parseDate(String mmddStr) {
        if (mmddStr == null || mmddStr.length() != 4) {
            throw new IllegalArgumentException("非法的时间格式");
        }
        return MonthDay.parse(mmddStr, DateTimeFormatter.ofPattern("MMdd"));
    }

    /**
     * 判断给定日期是否在指定的开始和结束{@link MonthDay}区间内。
     * 支持跨年的日期区间（例如，12月到次年1月）。
     *
     * <p>解决的问题：
     * 确定一个日期是否落在指定的月份日区间内，考虑到区间可能跨越年份的情况。
     *
     * <p>实现思路：
     * <ul>
     *   <li>如果开始{@link MonthDay}小于或等于结束{@link MonthDay}，则为非跨年区间。</li>
     *   <li>对于非跨年区间，将开始和结束的{@link MonthDay}转换为当年或相应年份的{@link LocalDate}进行比较。</li>
     *   <li>如果开始{@link MonthDay}大于结束{@link MonthDay}，则为跨年区间，需分别处理当年和次年的情况。</li>
     * </ul>
     *
     * <p>输入：
     * <ul>
     *   <li>{@code date}：需要判断的{@link LocalDate}对象。</li>
     *   <li>{@code start}：区间的开始{@link MonthDay}。</li>
     *   <li>{@code end}：区间的结束{@link MonthDay}。</li>
     * </ul>
     *
     * <p>输出：
     * <ul>
     *   <li>{@code true} 如果{@code date}在{@code start}和{@code end}之间；否则{@code false}。</li>
     * </ul>
     *
     */
    private boolean isDateInRange(LocalDate date, MonthDay start, MonthDay end) {
        if (start.compareTo(end) <= 0) { // 非跨年区间
            LocalDate startThisYear = start.atYear(date.getYear());
            LocalDate endThisYear = end.atYear(date.getYear());

            // 调整年份如果 startThisYear 在 date 之后
            if (startThisYear.isAfter(date)) {
                startThisYear = start.atYear(date.getYear() - 1);
            }

            // 调整年份如果 endThisYear 在 date 之前
            if (endThisYear.isBefore(date)) {
                endThisYear = end.atYear(date.getYear() + 1);
            }

            return !date.isBefore(startThisYear) && !date.isAfter(endThisYear);
        } else { // 跨年区间
            LocalDate startThisYear = start.atYear(date.getYear());
            LocalDate endNextYear = end.atYear(date.getYear() + 1);

            return (!date.isBefore(startThisYear) || !date.isAfter(endNextYear));
        }
    }
}

