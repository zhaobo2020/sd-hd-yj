package com.sl.water.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h2>降水量实时监测表 表实体类</h2>
 * <p>该类对应数据库中的表 {@code StRPptn}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-18 12:31:28</p>
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
 *                 <td><b>id</b></td>
 *                 <td>主键ID，自增</td>
 *                 <td>java.lang.Long</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>stationCode</b></td>
 *                 <td>测站编码</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>measurementTime</b></td>
 *                 <td>时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>rain</b></td>
 *                 <td>时段降水量 (mm)</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>intervalLength</b></td>
 *                 <td>时段长 (小时)</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>precipitationDuration</b></td>
 *                 <td>降水历时 (小时)</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>dailyRain</b></td>
 *                 <td>日降水量 (mm)</td>
 *                 <td>java.lang.Double</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>weather</b></td>
 *                 <td>天气状况</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>createdAt</b></td>
 *                 <td>创建时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>updatedAt</b></td>
 *                 <td>更新时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *     </tbody>
 * </table>
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 12:31:28 - 创建类并生成表实体。</li>
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
@TableName("st_r_pptn") // 对应数据库表名
@Accessors(chain = true)
public class StRPptn extends Model<StRPptn> {

    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 测站编码
     */
    @Schema(description = "测站编码")
    private String stationCode;
    /**
     * 时间
     */
    @Schema(description = "时间")
    private LocalDateTime measurementTime;
    /**
     * 时段降水量 (mm)
     */
    @Schema(description = "时段降水量")
    private Double rain;
    /**
     * 时段长 (小时)
     */
    @Schema(description = "时段长")
    private Double intervalLength;
    /**
     * 降水历时 (小时)
     */
    @Schema(description = "降水历时")
    private Double precipitationDuration;
    /**
     * 日降水量 (mm)
     */
    @Schema(description = "日降水量")
    private Double dailyRain;
    /**
     * 天气状况
     */
    @Schema(description = "天气状况")
    private String weather;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}

