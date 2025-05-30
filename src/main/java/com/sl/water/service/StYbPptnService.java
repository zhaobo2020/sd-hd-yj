package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.entity.StYbPptn;
import java.time.LocalDateTime;


/**
 * <h2>降雨预报数据表 服务接口</h2>
 * <p>类生成时间：2025-05-19 09:05:52</p>
 * 
 * <p><b>版本信息：</b></p>
 * <ul>
 *     <li>版本 1.0.0 - 初始版本</li>
 * </ul>
 * 
 * <p><b>更新时间：</b></p>
 * <ul>
 *     <li>2025-05-19 09:05:52 - 创建类并生成表实体。</li>
 * </ul>
 * @author zhaobo2020
 * @since 2025-05-19
 * @version 1.0.0
 */
public interface StYbPptnService extends IService<StYbPptn> {

    StYbPptn getFirstAfterTime(Long id, LocalDateTime startTime);
}

