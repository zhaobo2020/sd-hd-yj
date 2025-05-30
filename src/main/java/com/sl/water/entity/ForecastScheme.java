package com.sl.water.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * <h2>预报方案库表 表实体类</h2>
 * <p>该类对应数据库中的表 {@code ForecastScheme}，用于处理与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-19 11:02:19</p>
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
 *                 <td>自增唯一标识</td>
 *                 <td>java.lang.Long</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>type</b></td>
 *                 <td>预报类型（固定长度，如降雨/风速等）</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>name</b></td>
 *                 <td>预报方案名称</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>resultJson</b></td>
 *                 <td>预报结果JSON（如{"rainfall": [10.5, 8.3, ...]}）</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>paramsJson</b></td>
 *                 <td>方案入参JSON（如{"duration": 48, "region": "A"}）</td>
 *                 <td>java.lang.String</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>createdTime</b></td>
 *                 <td>创建时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>updatedTime</b></td>
 *                 <td>最后更新时间</td>
 *                 <td>java.time.LocalDateTime</td>
 *             </tr>
 *         </list>
 *         <list >
 *             <tr>
 *                 <td><b>status</b></td>
 *                 <td>方案状态，0为等待，1为计算中，2为计算完成，3为失败</td>
 *                 <td>java.lang.Integer</td>
 *             </tr>
 *         </list>
 *     </tbody>
 * </table>
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-19 11:02:19 - 创建类并生成表实体。</li>
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
@TableName("forecast_scheme") // 对应数据库表名
@Builder
public class ForecastScheme extends Model<ForecastScheme> {

    /**
     * 自增唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 预报类型（固定长度，如降雨/风速等）
     */
    @Schema(description = "预报类型")
    private Integer type;
    /**
     * 预报方案名称
     */
    @Schema(description = "预报方案名称")
    private String name;
    /**
     * 预报结果JSON（如{"rainfall": [10.5, 8.3, ...]}）
     */
    @Schema(description = "预报结果JSON")
    private String resultJson;
    /**
     * 方案入参JSON（如{"duration": 48, "region": "A"}）
     */
    @Schema(description = "方案入参JSON")
    private String paramsJson;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
    /**
     * 方案状态，0为等待，1为计算中，2为计算完成，3为失败
     */
    @Schema(description = "方案状态")
    private Integer status;
}

