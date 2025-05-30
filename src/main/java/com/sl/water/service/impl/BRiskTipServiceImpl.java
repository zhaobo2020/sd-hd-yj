package com.sl.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.dao.BRiskTipDao;
import com.sl.water.entity.BRiskTip;
import com.sl.water.service.BRiskTipService;
import org.springframework.stereotype.Service;


@Service
public class BRiskTipServiceImpl extends ServiceImpl<BRiskTipDao, BRiskTip> implements BRiskTipService {

    @Override
    public Page<BRiskTip> pageList(PageQueryParam pageQueryParam) {
        Page<BRiskTip> page = new Page<>(pageQueryParam.getPage(), pageQueryParam.getSize());
        return this.page(page);
    }

    @Override
    public boolean updateState(Long id, String state) {
        BRiskTip bRiskTip = new BRiskTip();
        bRiskTip.setState(state);
        UpdateWrapper<BRiskTip> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        return this.update(bRiskTip, updateWrapper);
    }
}

