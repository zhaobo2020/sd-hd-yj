package com.sl.water.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sl.plat.req.PageQueryParam;
import com.sl.water.entity.BRiskMessage;

public interface BRiskMessageService extends IService<BRiskMessage> {

    Page<BRiskMessage> pageList(PageQueryParam pageQueryParam);

    boolean createRiskMessage(BRiskMessage bRiskMessage, Integer duration);
}

