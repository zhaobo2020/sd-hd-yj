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
import lombok.experimental.Accessors;

/**
 * <h2>库(湖)容曲线表，用于存储水库（湖）水位和蓄水量的相关关系 表实体类</h2>
 * <p>该类对应数据库中的表 {@code StReservoirCapacityCurveB}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-15 09:10:35</p>
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
 *                 <td>测站编码，用于标识水库（湖泊）的代表水文站</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>measurementTime</b></td>
 *                 <td>库容曲线施测时间，代表该曲线的测量日期</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>pointNumber</b></td>
 *                 <td>库水位和蓄水量对应点在曲线中的顺序号，从1开始依次递增</td>
 *                 <td>java.lang.Integer</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>reservoirLevel</b></td>
 *                 <td>库（湖）内水位，单位：m</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>storageVolume</b></td>
 *                 <td>与库水位对应的蓄水量，单位：万m³</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>surfaceArea</b></td>
 *                 <td>与库水位对应的水面面积，单位：km²，精确到整数位</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>updateTime</b></td>
 *                 <td>记录最后一次更新时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *     </tbody>
 * </table>
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-15 09:10:35 - 创建类并生成表实体。</li>
 * </ul>
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("st_reservoir_capacity_curve_b") // 对应数据库表名
@Accessors(chain = true)
public class StReservoirCapacityCurveB extends Model<StReservoirCapacityCurveB> {

    /**
     * 测站编码，用于标识水库（湖泊）的代表水文站
     */
    @TableId(value = "station_code", type = IdType.AUTO)
    private String stationCode;
    /**
     * 库容曲线施测时间，代表该曲线的测量日期
     */
    private LocalDateTime measurementTime;
    /**
     * 库水位和蓄水量对应点在曲线中的顺序号，从1开始依次递增
     */
    private Integer pointNumber;
    /**
     * 库（湖）内水位，单位：m
     */
    private Double reservoirLevel;
    /**
     * 与库水位对应的蓄水量，单位：万m³
     */
    private Double storageVolume;
    /**
     * 与库水位对应的水面面积，单位：km²，精确到整数位
     */
    private Double surfaceArea;
    /**
     * 记录最后一次更新时间
     */
    private LocalDateTime updateTime;
}

