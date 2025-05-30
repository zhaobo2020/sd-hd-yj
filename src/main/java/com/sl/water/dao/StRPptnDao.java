package com.sl.water.dao;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.sl.water.entity.StRPptn;
/**
 * <h2>降水量实时监测表 表持久层类</h2>
 * <p>该类对应数据库中的表 {@code StRPptn}，用于存储与表相关的基本信息。</p>
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
 * @author zhaobo2020
 * @since 2025-05-18
 * @version 1.0.0
 */
@Repository
public interface StRPptnDao extends BaseMapper<StRPptn> {

}

