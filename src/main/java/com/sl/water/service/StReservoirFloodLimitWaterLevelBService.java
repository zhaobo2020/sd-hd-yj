package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.entity.StReservoirFloodLimitWaterLevelB;


/**
 * <h2>库（湖）站汛限水位表，用于存储水库（湖）安全运行限制水位信息 服务接口</h2>
 * <p>类生成时间：2025-05-18 10:16:23</p>
 * 
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * 
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 10:16:23 - 创建类并生成表实体。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-05-18
 * @version 1.0.0
 */
public interface StReservoirFloodLimitWaterLevelBService extends IService<StReservoirFloodLimitWaterLevelB> {
    // 根据当前时间和水库id 查询水库的汛限水位信息
    Double getWLevelLimit(String stationCode);
}

