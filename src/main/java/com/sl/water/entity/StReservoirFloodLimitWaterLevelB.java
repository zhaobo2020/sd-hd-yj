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
 * <h2>库（湖）站汛限水位表，用于存储水库（湖）安全运行限制水位信息 表实体类</h2>
 * <p>该类对应数据库中的表 {@code StReservoirFloodLimitWaterLevelB}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-18 10:16:23</p>
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
 *                 <td><b>beginMonthDay</b></td>
 *                 <td>汛限阶段的开始月日，格式：MMDD</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>endMonthDay</b></td>
 *                 <td>汛限阶段的结束月日，格式：MMDD</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>floodLimitWaterLevel</b></td>
 *                 <td>汛限水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>floodLimitStorage</b></td>
 *                 <td>汛限库容，单位：10⁶ m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>floodSeasonType</b></td>
 *                 <td>汛期类别（如：1-主汛期、2-非汛期等）</td>
 *                 <td>java.lang.String</td>
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
 *     <li>2025-05-18 10:16:23 - 创建类并生成表实体。</li>
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
@TableName("st_reservoir_flood_limit_water_level_b") // 对应数据库表名
public class StReservoirFloodLimitWaterLevelB extends Model<StReservoirFloodLimitWaterLevelB> {
    /**
     * 测站编码
     */
    private String stationCode;
    /**
     * 汛限阶段的开始月日，格式：MMDD
     */
    private String beginMonthDay;
    /**
     * 汛限阶段的结束月日，格式：MMDD
     */
    private String endMonthDay;
    /**
     * 汛限水位，单位：m
     */
    private Double floodLimitWaterLevel;
    /**
     * 汛限库容，单位：10⁶ m³
     */
    private Double floodLimitStorage;
    /**
     * 汛期类别（如：1-主汛期、2-非汛期等）
     */
    private String floodSeasonType;
    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;
}

