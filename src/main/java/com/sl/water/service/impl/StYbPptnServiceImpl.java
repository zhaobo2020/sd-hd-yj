package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.sl.water.entity.StYbPptn;
import com.sl.water.dao.StYbPptnDao;
import com.sl.water.service.StYbPptnService;

import java.time.LocalDateTime;

/**
 * <h2>降雨预报数据表 服务实现类</h2>
 * <p>实现类生成时间：2025-05-19 09:05:52</p>
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
 *
 * @author zhaobo2020
 * @version 1.0.0
 * @since 2025-05-19
 */
@Service
public class StYbPptnServiceImpl extends ServiceImpl<StYbPptnDao, StYbPptn> implements StYbPptnService {

    @Override
    public StYbPptn getFirstAfterTime(Long id, LocalDateTime startTime) {
        QueryWrapper<StYbPptn> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", id);
        queryWrapper.between("start_time", startTime, LocalDateTime.now());
        queryWrapper.orderByAsc("id").last("LIMIT 1");
        return this.getOne(queryWrapper);
    }
}

