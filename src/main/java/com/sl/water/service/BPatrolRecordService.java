package com.sl.water.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.plat.req.BPatrolRecordRequest;
import com.sl.water.dto.BPatrolRecordStaDTO;
import com.sl.water.entity.BPatrolRecord;

import java.util.List;

public interface BPatrolRecordService extends IService<BPatrolRecord> {

    Page<BPatrolRecord> pageList(BPatrolRecordRequest pageQueryParam);

    List<BPatrolRecord> listByKind(String kind);

    boolean updateState(Long id, String state);

    BPatrolRecordStaDTO sta();
}

