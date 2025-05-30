package com.sl.water.dao;


import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.sl.water.entity.StReservoirCapacityCurveB;
/**
 * <h2>库(湖)容曲线表，用于存储水库（湖）水位和蓄水量的相关关系 表持久层类</h2>
 * <p>该类对应数据库中的表 {@code StReservoirCapacityCurveB}，用于存储与表相关的基本信息。</p>
 * <p>类生成时间：2025-05-15 09:10:35</p>
 * 
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * 
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-15 09:10:35 - 创建类并生成表实体。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-05-15
 * @version 1.0.0
 */
@Repository
public interface StReservoirCapacityCurveBDao extends BaseMapper<StReservoirCapacityCurveB> {
    /**
     * <h2>获取指定测站的最新库容曲线数据</h2>
     * <p>此方法用于获取指定测站的最新库容曲线数据。</p>
     *
     * <p><b>参数说明：</b></p>
     * <ul>
     *     <li><code>stationCode</code>：测站唯一编码（主键组成部分）</li>
     * </ul>
     *
     * <p><b>返回值说明：</b></p>
     * <ul>
     *     <li>包含最新水文数据的测点信息列表</li>
     * </ul>
     *
     * <p><b>实现原理及关键步骤：</b></p>
     * <ol>
     *     <li>
     *         <b>多主键处理机制</b>
     *         <ul>
     *             <li>基于 <code>(station_code, measurement_time, point_number)</code> 复合主键设计。</li>
     *             <li>通过将 <code>measurement_time</code> 作为时间维度排序依据，确保每条记录的唯一性。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>子查询优化策略</b>
     *         <ul>
     *             <li>使用嵌套查询预先计算每个测点 <code>(point_number)</code> 的最新测量时间 <code>(latest_time)</code>。</li>
     *             <li>通过 <code>GROUP BY station_code</code> 和 <code>point_number</code> 实现测点级别的聚合。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>时间降序筛选</b>
     *         <ul>
     *             <li>使用 <code>MAX(measurement_time)</code> 函数自动筛选出各测点的历史测量中最新的数据记录。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>笛卡尔积过滤</b>
     *         <ul>
     *             <li>通过 <code>INNER JOIN</code> 将原始数据表与预计算的最晚时间结果进行匹配。</li>
     *             <li>仅保留满足最新时间条件的完整记录。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>字段选择性</b>
     *         <ul>
     *             <li>明确选择需要的业务字段，如 <code>storage_volume</code>、<code>surface_area</code> 等，避免使用 <code>SELECT *</code>。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>数据完整性保障</b>
     *         <ul>
     *             <li>主键约束确保了每条记录的唯一性，不会出现重复数据。</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>空值处理机制</b>
     *         <ul>
     *             <li>自然过滤掉无测量数据的测点（因 <code>INNER JOIN</code> 特性）。</li>
     *         </ul>
     *     </li>
     * </ol>
     */
    @Select("SELECT \n" +
            "    src.station_code,\n" +
            "\t\tsrc.measurement_time,\n" +
            "    src.point_number,\n" +
            "    src.reservoir_level,\n" +
            "    src.storage_volume,\n" +
            "    src.surface_area,\n" +
            "\t\tsrc.update_time\n" +
            "FROM \n" +
            "    st_reservoir_capacity_curve_b src\n" +
            "INNER JOIN (\n" +
            "    SELECT \n" +
            "        station_code,\n" +
            "        point_number,\n" +
            "        MAX(measurement_time) AS latest_time\n" +
            "    FROM \n" +
            "        st_reservoir_capacity_curve_b\n" +
            "    GROUP BY \n" +
            "        station_code, point_number\n" +
            ") AS latest ON \n" +
            "    src.station_code = latest.station_code \n" +
            "    AND src.point_number = latest.point_number \n" +
            "    AND src.measurement_time = latest.latest_time;")
    List<StReservoirCapacityCurveB> getLatestCurve(String stationCode);
}

