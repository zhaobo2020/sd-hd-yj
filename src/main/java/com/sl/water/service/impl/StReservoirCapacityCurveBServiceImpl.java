package com.sl.water.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.water.dto.ReservoirWCurveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sl.water.entity.StReservoirCapacityCurveB;
import com.sl.water.dao.StReservoirCapacityCurveBDao;
import com.sl.water.service.StReservoirCapacityCurveBService;

import java.util.Comparator;
import java.util.List;

/**
 * <h2>库(湖)容曲线表，用于存储水库（湖）水位和蓄水量的相关关系 服务实现类</h2>
 * <p>实现类生成时间：2025-05-15 09:10:42</p>
 * 
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * 
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-15 09:10:42 - 创建类并生成表实体。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-05-15
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class StReservoirCapacityCurveBServiceImpl extends ServiceImpl<StReservoirCapacityCurveBDao, StReservoirCapacityCurveB> implements StReservoirCapacityCurveBService {
    private final StReservoirCapacityCurveBDao stReservoirCapacityCurveBDao;


    /**
     * 根据水库编号获取库(湖)容曲线数据，对数据排序后封装到 {@link ReservoirWCurveDTO} 对象中。
     * <p>此方法会优先尝试从缓存中获取指定水库编号对应的库(湖)容曲线数据。若缓存中有有效数据，会直接返回；
     * 若缓存中无数据，则从数据库查询数据，对查询结果按测点编号排序，接着将水位和蓄水量分别提取出来，
     * 封装到一个 {@link ReservoirWCurveDTO} 对象中，最后将该对象存入缓存并返回。</p>
     * <p>若查询结果为空或者为 null，不会将结果存入缓存。</p>
     *
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return 一个 {@link ReservoirWCurveDTO} 对象，其中包含水库的水位数据列表和库容数据列表。
     *         水位数据列表存储在 {@code wLevelList} 属性中，每个元素为一个水位值，单位是 m；
     *         库容数据列表存储在 {@code wList} 属性中，每个元素为一个库容值，单位是 万m³。
     *         如果没有找到相关数据，列表可能为空。
     * @see StReservoirCapacityCurveBDao#getLatestCurve(String)
     */
    @Override
    @Cacheable(value = "reservoirCurveCache", key = "#stationCode", unless = "#result == null || #result.isEmpty()")
    public ReservoirWCurveDTO getCurve(String stationCode) {
        // 调用数据访问对象的方法，根据水库编号从数据库中获取最新的库(湖)容曲线数据，
        // 并将结果转换为流，按测点编号排序后收集为列表
        List<StReservoirCapacityCurveB> curveBS = stReservoirCapacityCurveBDao.getLatestCurve(stationCode)
                // 将查询结果转换为流，以便进行后续的处理操作
                .stream()
                // 根据测点编号对数据进行排序，确保结果按测点编号有序排列
                .sorted(Comparator.comparing(StReservoirCapacityCurveB::getPointNumber))
                .toList();
        // 创建并返回一个 ReservoirWCurveDTO 对象，将水位和库容数据分别存入对应的列表中
        return  new ReservoirWCurveDTO()
                .setWLevelList(curveBS.stream().map(StReservoirCapacityCurveB::getReservoirLevel).toList())
                .setWList(curveBS.stream().map(StReservoirCapacityCurveB::getStorageVolume).toList());
    }
}

