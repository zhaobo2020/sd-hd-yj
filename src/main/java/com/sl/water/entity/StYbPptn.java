package com.sl.water.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <h2>降雨预报数据表 表实体类</h2>
 * <p>该类对应数据库中的表 {@code StYbPptn}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-19 09:05:52</p>
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
 *                 <td><b>forecastId</b></td>
 *                 <td>自增主键ID</td>
 *                 <td>java.lang.Long</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>durationHours</b></td>
 *                 <td>预报总时长(小时)</td>
 *                 <td>java.lang.Integer</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>rainfallData</b></td>
 *                 <td>降雨过程数据(JSON格式)</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>startTime</b></td>
 *                 <td>预报开始时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>endTime</b></td>
 *                 <td>预报结束时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>createdAt</b></td>
 *                 <td>创建时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *     </tbody>
 * </table>
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-19 09:05:52 - 创建类并生成表实体。</li>
 * </ul>
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("st_yb_pptn") // 对应数据库表名
@Builder
public class StYbPptn extends Model<StYbPptn> {

    /**
     * 自增主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 预报总时长(小时)
     */
    private Integer durationHours;
    /**
     * 降雨过程数据(JSON格式)
     */
    private String rainfallData;
    /**
     * 预报开始时间
     */
    private LocalDateTime startTime;
    /**
     * 预报结束时间
     */
    private LocalDateTime endTime;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}

