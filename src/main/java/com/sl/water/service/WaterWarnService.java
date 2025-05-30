package com.sl.water.service;

import com.sl.water.vo.ReservoirWarnVO;
import com.sl.water.vo.RiverWarn;
import com.sl.water.vo.RiverWarnVO;
import com.sl.water.vo.RsvrWarn;

import java.time.LocalDateTime;
import java.util.List;

public interface WaterWarnService {
    /**
     * 查询水库水情预警
     * @return 水库水情预警情况
     */
    ReservoirWarnVO getReservoirWarnVO();

    List<RiverWarnVO> getRiverWarnVOList();

    RsvrWarn getRsvrWarnAfterTime(Long id, LocalDateTime time);

    RiverWarn getRiverWarnAfterTime(Long id, LocalDateTime time);

}
