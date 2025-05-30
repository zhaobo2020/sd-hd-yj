package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.water.dao.BRiskLogDao;
import com.sl.water.entity.BRiskLog;
import com.sl.water.service.BRiskLogService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class BRiskLogServiceImpl extends ServiceImpl<BRiskLogDao, BRiskLog> implements BRiskLogService {

    @Override
    public BRiskLog getBRiskLogByKind(String kind) {
        QueryWrapper<BRiskLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind",kind);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean updateRiskLog(Long recordId, LocalDateTime recordTime, String kind) {
        BRiskLog bRiskLog = new BRiskLog();
        bRiskLog.setRecordId(recordId);
        bRiskLog.setRecordTime(recordTime);
        UpdateWrapper<BRiskLog> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("kind", kind);
        return this.update(bRiskLog, updateWrapper);
    }
}

