package com.sl.water.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <h2>水库库容曲线数据传输对象（DTO）类</h2>
 * <p>该类用于在不同层之间传输水库库容曲线相关的数据，主要包含水位列表和库容列表信息。</p>
 * <p>类生成时间：2025-03-09 </p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * <p><b>类中属性：</b></p>
 * <table border="1" cellpadding="5" cellspacing="0">
 *     <thead>
 *         <tr>
 *             <th>属性名称</th>
 *             <th>属性注释</th>
 *             <th>属性类型</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>{@code wLevelList}</td>
 *             <td>存储水库的水位数据列表，每个元素为一个水位值（单位是 m）。</td>
 *             <td>{@code List<Double>}</td>
 *         </tr>
 *         <tr>
 *             <td>{@code wList}</td>
 *             <td>存储水库的库容数据列表，每个元素为一个库容值（单位是 万m3）。</td>
 *             <td>{@code List<Double>}</td>
 *         </tr>
 *     </tbody>
 * </table>
 * @author zhaobo2020
 * @since 2025-03-09
 * @version 1.0.0
 */
@Data
@Accessors(chain = true) // 关键注解
@Schema(description = "降雨数据及对应时间信息的传输对象", requiredProperties = {"rainList", "timeList"})
public class ReservoirWCurveDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // 可选但推荐添加
    /**
     * 存储水库的水位数据列表，每个元素为一个水位值,单位是 m。
     */
    @Schema(description = "存储水库的水位数据列表，每个元素为一个水位值，单位是 m")
    private List<Double> wLevelList;

    /**
     * 存储水库的库容数据列表，每个元素为一个库容值（单位是 万m3）。
     */
    @Schema(description = "存储水库的库容数据列表，每个元素为一个库容值，单位是 万m3")
    private List<Double> wList;

    public Boolean isEmpty() {
        return wLevelList == null || wLevelList.isEmpty() || wList == null || wList.isEmpty();
    }
}
