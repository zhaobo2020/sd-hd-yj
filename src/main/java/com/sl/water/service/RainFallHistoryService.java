package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.vo.RainfallHistoryVO;
import com.sl.water.entity.StRPptn;
import java.time.LocalDateTime;


public interface RainFallHistoryService extends IService<StRPptn> {
    RainfallHistoryVO getRainfallByDuration(LocalDateTime endTime, Integer duration);
}

