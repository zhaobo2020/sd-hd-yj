package com.sl.water.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <h2>库（湖）站防洪指标表，用于存储水库（湖泊）防洪指标及水文特征信息 表实体类</h2>
 * <p>该类对应数据库中的表 {@code StReservoirFloodIndicatorB}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-18 09:42:13</p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * <p><b>表字段：</b></p>
 * <table border="1" cellpadding="5" cellspacing="0">
 *     <thead>
 *         <tr>
 *             <th>字段名称</th>
 *             <th>字段注释</th>
 *             <th>字段类型</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <list >
 *             <tr>
 *                 <td><b>stationCode</b></td>
 *                 <td>测站编码</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>stationName</b></td>
 *                 <td>水库名称</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>reservoirType</b></td>
 *                 <td>水库类型（如：1-大型水库、2-中型水库等）</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>damTopElevation</b></td>
 *                 <td>坝顶高程，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>checkFloodLevel</b></td>
 *                 <td>校核洪水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>designFloodLevel</b></td>
 *                 <td>设计洪水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>normalHighWaterLevel</b></td>
 *                 <td>正常高水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>deadWaterLevel</b></td>
 *                 <td>死水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>beneficialWaterLevel</b></td>
 *                 <td>兴利水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>totalStorage</b></td>
 *                 <td>总库容，单位：10000 m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>floodControlStorage</b></td>
 *                 <td>防洪库容，单位：10⁶ m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>beneficialStorage</b></td>
 *                 <td>兴利库容，单位：10⁶ m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>deadStorage</b></td>
 *                 <td>死库容，单位：10⁶ m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxReservoirLevel</b></td>
 *                 <td>历史最高库水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxStorage</b></td>
 *                 <td>历史最大蓄水量，单位：10⁶ m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxLevelTime</b></td>
 *                 <td>历史最高库水位出现时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxInflow</b></td>
 *                 <td>历史最大入流，单位：m³/s</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxInflowDuration</b></td>
 *                 <td>历史最大入流时段长，单位：小时</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxInflowTime</b></td>
 *                 <td>历史最大入流出现时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxOutflow</b></td>
 *                 <td>历史最大出流，单位：m³/s</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMaxOutflowTime</b></td>
 *                 <td>历史最大出流出现时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMinReservoirLevel</b></td>
 *                 <td>历史最低库水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMinLevelTime</b></td>
 *                 <td>历史最低库水位出现时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMinDailyInflow</b></td>
 *                 <td>历史最小日均入流，单位：m³/s</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>historicalMinDailyInflowTime</b></td>
 *                 <td>历史最小日均入流出现时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>lowWaterAlarmLevel</b></td>
 *                 <td>低水位告警值，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>forecastInitiationFlow</b></td>
 *                 <td>启动预报流量标准，单位：m³/s</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>updateTime</b></td>
 *                 <td>记录更新时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *     </tbody>
 * </table>
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 09:42:13 - 创建类并生成表实体。</li>
 * </ul>
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("st_reservoir_flood_indicator_b") // 对应数据库表名
public class StReservoirFloodIndicatorB extends Model<StReservoirFloodIndicatorB> {

    /**
     * 测站编码
     */
    @TableId(value = "station_code", type = IdType.AUTO)
    private String stationCode;
    /**
     * 水库名称
     */
    private String stationName;
    /**
     * 水库类型（如：1-大型水库、2-中型水库等）
     */
    private String reservoirType;
    /**
     * 坝顶高程，单位：m
     */
    private Double damTopElevation;
    /**
     * 校核洪水位，单位：m
     */
    private Double checkFloodLevel;
    /**
     * 设计洪水位，单位：m
     */
    private Double designFloodLevel;
    /**
     * 正常高水位，单位：m
     */
    private Double normalHighWaterLevel;
    /**
     * 死水位，单位：m
     */
    private Double deadWaterLevel;
    /**
     * 兴利水位，单位：m
     */
    private Double beneficialWaterLevel;
    /**
     * 总库容，单位：10000 m³
     */
    private Double totalStorage;
    /**
     * 防洪库容，单位：10⁶ m³
     */
    private Double floodControlStorage;
    /**
     * 兴利库容，单位：10⁶ m³
     */
    private Double beneficialStorage;
    /**
     * 死库容，单位：10⁶ m³
     */
    private Double deadStorage;
    /**
     * 历史最高库水位，单位：m
     */
    private Double historicalMaxReservoirLevel;
    /**
     * 历史最大蓄水量，单位：10⁶ m³
     */
    private Double historicalMaxStorage;
    /**
     * 历史最高库水位出现时间
     */
    private LocalDateTime historicalMaxLevelTime;
    /**
     * 历史最大入流，单位：m³/s
     */
    private Double historicalMaxInflow;
    /**
     * 历史最大入流时段长，单位：小时
     */
    private Double historicalMaxInflowDuration;
    /**
     * 历史最大入流出现时间
     */
    private LocalDateTime historicalMaxInflowTime;
    /**
     * 历史最大出流，单位：m³/s
     */
    private Double historicalMaxOutflow;
    /**
     * 历史最大出流出现时间
     */
    private LocalDateTime historicalMaxOutflowTime;
    /**
     * 历史最低库水位，单位：m
     */
    private Double historicalMinReservoirLevel;
    /**
     * 历史最低库水位出现时间
     */
    private LocalDateTime historicalMinLevelTime;
    /**
     * 历史最小日均入流，单位：m³/s
     */
    private Double historicalMinDailyInflow;
    /**
     * 历史最小日均入流出现时间
     */
    private LocalDateTime historicalMinDailyInflowTime;
    /**
     * 低水位告警值，单位：m
     */
    private Double lowWaterAlarmLevel;
    /**
     * 启动预报流量标准，单位：m³/s
     */
    private Double forecastInitiationFlow;
    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;
}

