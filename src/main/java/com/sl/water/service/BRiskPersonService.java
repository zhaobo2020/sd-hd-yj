package com.sl.water.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskPerson;

public interface BRiskPersonService extends IService<BRiskPerson> {

    Page<BRiskPerson> pageList(PageQueryParam pageQueryParam);
}

