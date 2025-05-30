package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.dao.BRiskMessageDao;
import com.sl.water.entity.BRiskMessage;
import com.sl.water.service.BRiskMessageService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class BRiskMessageServiceImpl extends ServiceImpl<BRiskMessageDao, BRiskMessage> implements BRiskMessageService {

    @Override
    public Page<BRiskMessage> pageList(PageQueryParam pageQueryParam) {
        Page<BRiskMessage> page = new Page<>(pageQueryParam.getPage(), pageQueryParam.getSize());
        return this.page(page);
    }

    @Override
    public boolean createRiskMessage(BRiskMessage bRiskMessage, Integer duration) {
        LocalDateTime localDateTime = LocalDateTime.now().withHour(LocalDateTime.now().getHour()).withMinute(0).withSecond(0);
        QueryWrapper<BRiskMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kind", bRiskMessage.getKind());
        queryWrapper.orderByDesc("id").last("LIMIT 1");
        BRiskMessage last = this.getOne(queryWrapper);
        if (duration > 0 && last != null && last.getLevel().equals(bRiskMessage.getLevel()) && bRiskMessage.getWarnStime().minusHours(duration).isBefore(last.getWarnStime())) {
            BRiskMessage updateRiskMessage = new BRiskMessage();
            updateRiskMessage.setWarnEtime(bRiskMessage.getWarnStime());
            UpdateWrapper<BRiskMessage> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", last.getId());
            return this.update(last, updateWrapper);
        } else {
            return this.save(bRiskMessage);
        }
    }
}

