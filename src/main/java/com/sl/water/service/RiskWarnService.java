package com.sl.water.service;

import cn.hutool.json.JSONObject;
import com.sl.water.vo.ForecastSchemeVO;
import com.sl.water.vo.ReservoirLimitWarnVO;
import com.sl.water.vo.RiverLimitWarnVO;

public interface RiskWarnService {
    ReservoirLimitWarnVO queryReservoirWarn();

    RiverLimitWarnVO queryRiverWarn(String stcd);

    JSONObject queryOverflowWarn();

    ForecastSchemeVO queryForecastWarn();
}
