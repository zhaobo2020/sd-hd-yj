package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.dto.ReservoirWCurveDTO;
import com.sl.water.entity.StReservoirCapacityCurveB;


/**
 * <h2>库(湖)容曲线表，用于存储水库（湖）水位和蓄水量的相关关系 服务接口</h2>
 * <p>类生成时间：2025-05-15 09:10:42</p>
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
public interface StReservoirCapacityCurveBService extends IService<StReservoirCapacityCurveB> {
    /**
     * <h2>根据水库编号获取库(湖)容曲线表，查询存储水库（湖）水位和蓄水量的相关关系</h2>
     * <p>此方法用于根据给定的水库编号，从数据库中查询并获取与该水库对应的库(湖)容曲线数据。</p>
     * <p>返回的数据封装到一个 {@link ReservoirWCurveDTO} 对象中</p>
     *
     * @param stationCode 水库编号，用于唯一标识一个水库
     * @return ReservoirWCurveDTO 对象，包含水库的库(湖)容曲线数据
     */
    ReservoirWCurveDTO getCurve(String stationCode);
}

