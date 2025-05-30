package com.sl.water.api;


import com.sl.water.dto.ReservoirWaterInfoBaseDTO;

import java.util.List;


/**
 * <h2>水库服务接口</h2>
 * <p>该接口定义了一系列与水库相关的服务方法，用于获取水库的水情信息、汛线水位、根据水位查询库容、根据库容查询水位以及根据库容过程查询水位过程等。</p>
 * <p>通过这些方法，外部系统可以方便地与水库数据进行交互，解决了在实际应用中对水库相关数据查询和处理的问题，例如在水利监测、水资源管理等场景下，能够及时获取水库的关键信息以支持决策和分析。</p>
 * <p>类生成时间：2025-03-09 （可根据实际情况修改）</p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * <p><b>接口方法：</b></p>
 * <table border="1" cellpadding="5" cellspacing="0">
 *     <thead>
 *         <tr>
 *             <th>方法名称</th>
 *             <th>方法功能</th>
 *             <th>参数说明</th>
 *             <th>返回值说明</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>{@link #getReservoirBaseWaterInfo(String)}</td>
 *             <td>根据水库编号获取水库水情信息</td>
 *             <td>stationCode：水库编号，用于唯一标识一个水库</td>
 *             <td>水库水情信息，以 {@link ReservoirWaterInfoBaseDTO} 对象形式返回</td>
 *         </tr>
 *         <tr>
 *             <td>{@link #getWLevelLimit(String)}</td>
 *             <td>根据水库编号获取水库汛线水位</td>
 *             <td>stationCode：水库编号，用于唯一标识一个水库</td>
 *             <td>水库汛线水位，以 {@link Double} 类型返回</td>
 *         </tr>
 *         <tr>
 *             <td>{@link #getCapacity(String, Double)}</td>
 *             <td>根据水库编号和水位查询对应的库容</td>
 *             <td>stationCode：水库编号；waterLevel：水位值</td>
 *             <td>对应的库容，以 {@link Double} 类型返回</td>
 *         </tr>
 *         <tr>
 *             <td>{@link #getWaterLevel(String, Double)}</td>
 *             <td>根据水库编号和库容查询对应的水位</td>
 *             <td>stationCode：水库编号；w：库容值</td>
 *             <td>对应的水位，以 {@link Double} 类型返回</td>
 *         </tr>
 *         <tr>
 *             <td>{@link #getWaterLevelList(String, List)}</td>
 *             <td>根据水库编号和库容过程查询对应的水位过程</td>
 *             <td>stationCode：水库编号；wList：库容值数组，表示库容过程</td>
 *             <td>对应的水位过程，以 {@link List<Double>} 形式返回</td>
 *         </tr>
 *     </tbody>
 * </table>
 * @author zhaobo2020
 * @since 2025-03-09
 * @version 1.0.0
 */
public interface ReservoirApi {

    /**
     * 根据水库编号，获取水库基础水情信息
     * <p>该方法解决了在实际应用中需要获取特定水库水情信息的问题，例如在水利监测系统中，需要实时了解各个水库的水情状态。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return 水库水情信息，以 {@link ReservoirWaterInfoBaseDTO} 对象形式返回，包含了水库的各项水情数据。
     */
    ReservoirWaterInfoBaseDTO getReservoirBaseWaterInfo(String stationCode);

    /**
     * 根据水库编号，获取水库汛线水位
     * <p>该方法解决了在防汛工作中需要快速获取水库汛线水位的问题，有助于及时采取防汛措施。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return 水库汛线水位，以 {@link Double} 类型返回，单位通常为米。
     */
    Double getWLevelLimit(String stationCode);

    /**
     * 根据水库编号和水位查询库容
     * <p>该方法解决了在水资源管理和水利工程计算中，需要根据水位确定水库库容的问题，例如在水库调度、洪水演算等场景中具有重要作用。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @param waterLevel 水位值，用于查询对应的库容
     * @return 对应的库容，以 {@link Double} 类型返回，单位通常为立方米或万立方米。
     */
    Double getCapacity(String stationCode,Double waterLevel);

    /**
     * 根据水库编号和库容查询水位
     * <p>该方法解决了在一些水利分析和规划中，需要根据库容反推水位的问题，例如在水库库容规划、水位预警等方面有应用。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @param w 库容值，用于查询对应的水位
     * @return 对应的水位，以 {@link Double} 类型返回，单位通常为米。
     */
    Double getWaterLevel(String stationCode,Double w);


    /**
     * 根据水库编号和库容过程查询水位过程
     * <p>该方法解决了在模拟水库水位变化过程中，需要根据库容变化序列得到相应水位变化序列的问题，对于水库的动态管理和预测有重要意义。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @param wList 库容值数组，表示库容过程，列表中的每个元素代表一个时刻的库容值
     * @return 对应的水位过程，以 {@link List<Double>} 形式返回，列表中的元素与库容数组元素一一对应，单位通常为米。
     */
    List<Double> getWaterLevelList(String stationCode, List<Double> wList);
}
