package com.sl.water.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.dao.BRiskPersonDao;
import com.sl.water.entity.BRiskPerson;
import com.sl.water.service.BRiskPersonService;
import org.springframework.stereotype.Service;


@Service
public class BRiskPersonServiceImpl extends ServiceImpl<BRiskPersonDao, BRiskPerson> implements BRiskPersonService {

    @Override
    public Page<BRiskPerson> pageList(PageQueryParam pageQueryParam) {
        Page<BRiskPerson> page = new Page<>(pageQueryParam.getPage(),pageQueryParam.getSize());
        return this.page(page);
    }
}

