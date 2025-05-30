package com.sl.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.dto.RainListDTO;
import com.sl.water.entity.StRPptn;
import java.time.LocalDateTime;


/**
 * <h2>降水量实时监测表 服务接口</h2>
 * <p>类生成时间：2025-05-18 12:31:28</p>
 *
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 *
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-18 12:31:28 - 创建类并生成表实体。</li>
 * </ul>
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-18
 */
public interface StRPptnService extends IService<StRPptn> {
    RainListDTO getRainByDuration(String stationCode, Integer rainDuration);

    StRPptn getFirstAfterTime(String stationCode, Long id, LocalDateTime measurementTime);
}

