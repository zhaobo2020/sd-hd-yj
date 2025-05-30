package com.sl.water.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.water.entity.BRiskLog;

import java.time.LocalDateTime;

public interface BRiskLogService extends IService<BRiskLog> {

    BRiskLog getBRiskLogByKind(String kind);

    boolean updateRiskLog(Long recordId, LocalDateTime recordTime, String kind);

}

