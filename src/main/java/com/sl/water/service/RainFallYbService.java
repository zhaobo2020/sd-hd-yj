package com.sl.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.entity.StYbPptn;
import com.sl.water.vo.RainfallYbVO;

import java.time.LocalDateTime;

/**
 * @description:降水预报
 * @author: duan
 * @time: 2025-05-23 11:31
 */

public interface RainFallYbService extends IService<StYbPptn> {
    RainfallYbVO getRainfallByDuration(LocalDateTime localDateTime, Integer duration);
}
