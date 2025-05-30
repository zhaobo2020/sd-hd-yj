package com.sl.water.api.impl;

import com.sl.common.utils.MathUtils;
import com.sl.water.api.ReservoirApi;
import com.sl.water.dto.ReservoirWCurveDTO;
import com.sl.water.dto.ReservoirWaterInfoBaseDTO;
import com.sl.water.entity.StReservoirFloodIndicatorB;
import com.sl.water.service.StReservoirCapacityCurveBService;
import com.sl.water.service.StReservoirFloodIndicatorBService;
import com.sl.water.service.StReservoirFloodLimitWaterLevelBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <h2>水库服务接口的实现类</h2>
 * <p>该类实现了 {@link ReservoirApi} 接口，提供了一系列与水库相关的服务方法的具体实现。</p>
 * <p>通过调用不同的服务层对象，实现了获取水库水情信息、汛线水位等功能，解决了在实际应用中对水库数据的查询和整合问题。</p>
 * <p>类生成时间：2025-03-09 （可根据实际情况修改）</p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * <p><b>依赖服务：</b></p>
 * <ul>
 *     <li>{@link StReservoirFloodIndicatorBService}：用于获取水库的基础水情信息，如防洪高水位、设计洪水位等。</li>
 *     <li>{@link StReservoirFloodLimitWaterLevelBService}：用于获取水库的汛线水位。</li>
 *     <li>{@link StReservoirCapacityCurveBService}：用于处理水库的库容曲线相关信息。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-03-09
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ReservoirApiImpl implements ReservoirApi {
    /**
     * 用于获取水库的基础水情信息，如防洪高水位、设计洪水位等。
     */
    private final StReservoirFloodIndicatorBService stReservoirFloodIndicatorBService;

    /**
     * 用于获取水库的汛线水位。
     */
    private final StReservoirFloodLimitWaterLevelBService stReservoirFloodLimitWaterLevelBService;

    /**
     * 用于处理水库的库容曲线相关信息。
     */
    private final StReservoirCapacityCurveBService stReservoirCapacityCurveBService;

    /**
     * 根据水库编号，获取水库基础水情信息
     * <p>该方法通过调用 {@link StReservoirFloodIndicatorBService} 获取水库的基础水情信息，如水库名称、防洪高水位、设计洪水位等；
     * 调用 {@link StReservoirFloodLimitWaterLevelBService} 获取水库的汛线水位，并将这些信息封装到 {@link ReservoirWaterInfoBaseDTO} 对象中返回。</p>
     * <p>解决了在实际应用中需要整合水库多方面水情信息的问题，为用户提供一个完整的水库基础水情信息视图。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return 封装了水库水情信息的 {@link ReservoirWaterInfoBaseDTO} 对象，如果没有查询到相关信息，部分字段可能为 null。
     */
    @Override
    public ReservoirWaterInfoBaseDTO getReservoirBaseWaterInfo(String stationCode) {
        // 创建一个用于存储水库水情信息的 DTO 对象
        ReservoirWaterInfoBaseDTO resDto = new ReservoirWaterInfoBaseDTO();
        // 通过数据库查询 水库基础 水情信息，防洪高水位什么的
        Optional<StReservoirFloodIndicatorB> optById = stReservoirFloodIndicatorBService.getOptById(stationCode);
        // 如果查询到了水库的基础水情信息
        if (optById.isPresent()) {
            // 获取查询到的水库基础水情信息对象
            StReservoirFloodIndicatorB stReservoirFloodIndicatorB = optById.get();
            // 将水库的基础水情信息设置到 DTO 对象中
            resDto.setName(stReservoirFloodIndicatorB.getStationName())
                    .setStationCode(stReservoirFloodIndicatorB.getStationCode())
                    .setCheckFloodLevel(stReservoirFloodIndicatorB.getCheckFloodLevel())
                    .setDesignFloodLevel(stReservoirFloodIndicatorB.getDesignFloodLevel())
                    .setNormalHighWaterLevel(stReservoirFloodIndicatorB.getNormalHighWaterLevel())
                    .setDeadWaterLevel(stReservoirFloodIndicatorB.getDeadWaterLevel())
                    .setBeneficialWaterLevel(stReservoirFloodIndicatorB.getBeneficialWaterLevel());
        }
        // 获取水库的汛线水位
        Double wLevelLimit = stReservoirFloodLimitWaterLevelBService.getWLevelLimit(stationCode);
        // 如果汛线水位不为 null
        if (wLevelLimit != null) {
            // 将汛线水位设置到 DTO 对象中
            resDto.setWLevelLimit(wLevelLimit);
        }
        // 返回封装好的水库水情信息 DTO 对象
        return resDto;
    }

    /**
     * 根据水库编号，获取水库汛线水位
     * <p>该方法通过调用 {@link StReservoirFloodLimitWaterLevelBService} 的 {@code getWLevelLimit} 方法，获取指定水库的汛线水位。</p>
     * <p>解决了在防汛工作中需要快速获取水库汛线水位的问题，为防汛决策提供支持。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return 水库的汛线水位，如果没有查询到则返回 null。
     */
    @Override
    public Double getWLevelLimit(String stationCode) {

        return  stReservoirFloodLimitWaterLevelBService.getWLevelLimit(stationCode);
    }

    @Override
    public Double getCapacity(String stationCode, Double waterLevel) {
        ReservoirWCurveDTO dto = stReservoirCapacityCurveBService.getCurve(stationCode);
        return queryStorageVolume(dto.getWLevelList(), dto.getWList(),waterLevel);
    }

    /**
     * 根据水库编号和库容值查询对应的水位
     * <p>该方法首先根据传入的水库编号调用 {@link StReservoirCapacityCurveBService#getCurve(String)} 方法，获取该水库的水位 - 库容曲线数据，
     * 以 {@link ReservoirWCurveDTO} 对象形式返回。接着，从该对象中提取出水位列表和库容列表，
     * 并调用内部的 {@link #queryWaterLevel(List, List, Double)} 方法，根据传入的库容值查询对应的水位。</p>
     * <p>解决了在水利分析和规划中，需要根据特定水库的库容值来确定对应水位的问题，为水库管理和决策提供了数据支持。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @param w 库容值，用于查询对应的水位
     * @return 与传入库容值对应的水位值，如果未找到匹配结果，可能返回 null
     */
    @Override
    public Double getWaterLevel(String stationCode, Double w) {
        // 根据水库编号获取水库的水位 - 库容曲线数据
        ReservoirWCurveDTO dto = stReservoirCapacityCurveBService.getCurve(stationCode);
        // 调用内部方法根据水位列表、库容列表和给定的库容值查询对应的水位
        return queryWaterLevel(dto.getWLevelList(), dto.getWList(),w);
    }

    /**
     * 根据水库编号和库容值列表查询对应的水位值列表
     * <p>该方法先根据传入的水库编号调用 {@link StReservoirCapacityCurveBService#getCurve(String)} 方法，获取该水库的水位 - 库容曲线数据，
     * 封装在 {@link ReservoirWCurveDTO} 对象中。然后，对传入的库容值列表进行流式处理，
     * 针对每个库容值调用内部的 {@link #queryWaterLevel(List, List, Double)} 方法查询对应的水位值，
     * 最后将查询结果收集为一个新的列表返回。</p>
     * <p>解决了在模拟水库水位变化过程中，需要根据一系列库容值得到相应水位值序列的问题，对于水库的动态管理和预测有重要意义。</p>
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @param wList 库容值列表，每个元素代表一个时刻的库容值
     * @return 与传入库容值列表对应的水位值列表，列表中的元素与传入的库容值列表元素一一对应
     */
    @Override
    public List<Double> getWaterLevelList(String stationCode, List<Double> wList) {
        // 根据水库编号获取水库的水位 - 库容曲线数据
        ReservoirWCurveDTO dto = stReservoirCapacityCurveBService.getCurve(stationCode);
        // 对传入的库容值列表进行流式处理，查询每个库容值对应的水位值，并收集为新列表
        return wList.stream()
                .map(item -> queryWaterLevel(dto.getWLevelList(), dto.getWList(),item))
                .toList();
    }


    /**
     * 根据水位列表、库容列表和给定的水位值查询对应的库容值
     * <p>该方法调用 {@link MathUtils} 工具类方法，
     * 依据传入的水位列表、库容列表和给定的水位值，计算并返回对应的库容值。</p>
     * <p>适用于在已知水位 - 库容曲线数据的情况下，根据某个具体水位值查询其对应的库容，在水库调度和水资源管理中有应用。</p>
     * @param wLevelList 水位列表，存储了一系列的水位值
     * @param wList 库容列表，存储了与水位列表对应的库容值，元素与水位列表元素一一对应
     * @param waterLevel 给定的水位值，用于查询对应的库容
     * @return 与给定水位值对应的库容值，如果未找到匹配结果，可能返回 null 或根据计算逻辑得到的结果
     */
    private Double queryStorageVolume(List<Double> wLevelList, List<Double> wList, Double waterLevel) {
        return MathUtils.calculate(wLevelList,wList,waterLevel);
    }
    /**
     * 根据库容列表、水位列表和给定的库容值查询对应的水位值
     * <p>该方法调用 {@link MathUtils} 工具类方法，
     * 依据传入的库容列表、水位列表和给定的库容值，反向计算并返回对应的水位值。</p>
     * <p>常用于在已知水位 - 库容曲线数据时，根据某个具体库容值反推其对应的水位，在水库规划和预警等场景中有应用。</p>
     * @param wList 库容列表，存储了一系列的库容值
     * @param wLevelList 水位列表，存储了与库容列表对应的水位值，元素与库容列表元素一一对应
     * @param w 给定的库容值，用于查询对应的水位
     * @return 与给定库容值对应的水位值，如果未找到匹配结果，可能返回 null 或根据计算逻辑得到的结果
     */
    private Double queryWaterLevel(List<Double> wList, List<Double> wLevelList, Double w) {
        return MathUtils.calculateReverse(wList,wLevelList,w);
    }


}
