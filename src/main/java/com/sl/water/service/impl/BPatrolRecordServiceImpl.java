package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.req.BPatrolRecordRequest;
import com.sl.water.dao.BPatrolRecordDao;
import com.sl.water.dto.BPatrolRecordStaDTO;
import com.sl.water.entity.BPatrolRecord;
import com.sl.water.service.BPatrolRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BPatrolRecordServiceImpl extends ServiceImpl<BPatrolRecordDao, BPatrolRecord> implements BPatrolRecordService {

    @Override
    public Page<BPatrolRecord> pageList(BPatrolRecordRequest pageQueryParam) {
        LambdaQueryWrapper<BPatrolRecord> queryWrapper = new LambdaQueryWrapper<>();
        Page<BPatrolRecord> page = new Page<>(pageQueryParam.getPage(), pageQueryParam.getSize());
        if (StringUtils.isNoneEmpty(pageQueryParam.getKind())) {
            queryWrapper.eq(BPatrolRecord::getKind, pageQueryParam.getKind());
        }
        queryWrapper.eq(BPatrolRecord::getState, "未处理");
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BPatrolRecord> listByKind(String kind) {
        LambdaQueryWrapper<BPatrolRecord> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNoneEmpty(kind)) {
            queryWrapper.eq(BPatrolRecord::getKind, kind);
        }
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateState(Long id, String state) {
        BPatrolRecord bPatrolRecord = new BPatrolRecord();
        bPatrolRecord.setState(state);
        UpdateWrapper<BPatrolRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        return this.update(bPatrolRecord, updateWrapper);
    }

    @Override
    public BPatrolRecordStaDTO sta() {
        QueryWrapper<BPatrolRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("kind,count(1) as count");
        queryWrapper.eq("state", "未处理");
        queryWrapper.groupBy("kind");
        List<Map<String, Object>> list = this.listMaps(queryWrapper);
        BPatrolRecordStaDTO bPatrolRecordStaDTO = new BPatrolRecordStaDTO();
        for (Map<String, Object> map : list) {
            if ("语音".equals(String.valueOf(map.get("kind"))))
                bPatrolRecordStaDTO.setVoiceCount(Integer.parseInt(String.valueOf(map.get("count"))));
            else if ("短信".equals(String.valueOf(map.get("kind"))))
                bPatrolRecordStaDTO.setSmsCount(Integer.parseInt(String.valueOf(map.get("count"))));
            else if ("图片".equals(String.valueOf(map.get("kind"))))
                bPatrolRecordStaDTO.setImageCount(Integer.parseInt(String.valueOf(map.get("count"))));
            else if ("视频".equals(String.valueOf(map.get("kind"))))
                bPatrolRecordStaDTO.setVideoCount(Integer.parseInt(String.valueOf(map.get("count"))));
        }
        return bPatrolRecordStaDTO;
    }
}

