package com.sl.dispatch.job;

import com.sl.water.entity.BRiskLog;
import com.sl.water.entity.BRiskMessage;
import com.sl.water.entity.StRPptn;
import com.sl.water.entity.StYbPptn;
import com.sl.water.service.*;
import com.sl.water.vo.*;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
public class ScheduledTasks {

    private static final String messageFormat = "%s，检测值%s，超过%s，阈值为%s";
    private static final String messageFormat2 = "%s，%s，检测值%s，超过%s，阈值为%s";

    @Resource
    private BRiskLogService bRiskLogService;
    @Resource
    private BRiskMessageService bRiskMessageService;
    @Resource
    private StRPptnService stRPptnService;
    @Resource
    private StYbPptnService stYbPptnService;
    @Resource
    private RainFallYbService rainFallYbService;
    @Resource
    private RainFallHistoryService rainFallHistoryService;
    @Resource
    private WaterWarnService waterWarnService;

    @Value("${task.r_pptn.duration:-1}")
    private Integer rPptnDuration;
    @Value("${task.r_pptn.duration:-1}")
    private Integer ybPptnDuration;
    @Value("${task.r_rsvr.duration:-1}")
    private Integer rRsvrDuration;
    @Value("${task.r_river.duration:-1}")
    private Integer rRiverDuration;

    /**
     * 监测降雨(间隔时间：3分钟)
     */
    @Scheduled(cron = "${task.r_pptn.cron}")
    public void r_pptnTask() {
        BRiskLog bRiskLog = bRiskLogService.getBRiskLogByKind("监测降雨");
        StRPptn stRPptn = stRPptnService.getFirstAfterTime(null, bRiskLog.getRecordId(), bRiskLog.getRecordTime());
        if (stRPptn != null) {
            RainfallHistoryVO rainfallHistoryVO = rainFallHistoryService.getRainfallByDuration(stRPptn.getMeasurementTime(), 1);
            if (rainfallHistoryVO.getLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("监测降雨");
                bRiskMessage.setWarnStime(stRPptn.getMeasurementTime());
                bRiskMessage.setWarnEtime(stRPptn.getMeasurementTime());
                bRiskMessage.setLevel(rainfallHistoryVO.getLevel());
                bRiskMessage.setContent(String.format(messageFormat, "监测降雨", formatNumber(rainfallHistoryVO.getRainfall()), calc(rainfallHistoryVO.getRainfall(), rainfallHistoryVO.getThreshold()), formatNumber(rainfallHistoryVO.getThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, rPptnDuration);
            }
            bRiskLogService.updateRiskLog(stRPptn.getId(), stRPptn.getMeasurementTime(), "监测降雨");
            r_pptnTask();
        }
    }

    /**
     * 预报降雨(间隔时间：30分钟
     */
    @Scheduled(cron = "${task.yb_pptn.cron}")
    public void yb_pptnTask() {
        BRiskLog bRiskLog = bRiskLogService.getBRiskLogByKind("预报降雨");
        StYbPptn stYbPptn = stYbPptnService.getFirstAfterTime(bRiskLog.getRecordId(), bRiskLog.getRecordTime());
        if (stYbPptn != null) {
            RainfallYbVO rainfallYbVO = rainFallYbService.getRainfallByDuration(stYbPptn.getStartTime(), 48);
            if (rainfallYbVO.getLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("预报降雨");
                bRiskMessage.setWarnStime(stYbPptn.getStartTime());
                bRiskMessage.setWarnEtime(stYbPptn.getStartTime());
                bRiskMessage.setLevel(rainfallYbVO.getLevel());
                bRiskMessage.setContent(String.format(messageFormat, "预报降雨", formatNumber(rainfallYbVO.getRainfall()), calc(rainfallYbVO.getRainfall(), rainfallYbVO.getThreshold()), formatNumber(rainfallYbVO.getThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, ybPptnDuration);
            }
            bRiskLogService.updateRiskLog(stYbPptn.getId(), stYbPptn.getStartTime(), "预报降雨");
            yb_pptnTask();
        }
    }

    /**
     * 水库水位、水库出流(间隔时间：30分钟，数据中间隔是1小时)
     */
    @Scheduled(cron = "${task.r_rsvr.cron}")
    public void r_rsvrTask() {
        BRiskLog bRiskLog = bRiskLogService.getBRiskLogByKind("水库水位、水库出流");
        RsvrWarn rsvrWarn = waterWarnService.getRsvrWarnAfterTime(bRiskLog.getRecordId(), bRiskLog.getRecordTime());
        if (rsvrWarn != null) {
            if (rsvrWarn.getZLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("水库水位");
                bRiskMessage.setWarnStime(rsvrWarn.getTm());
                bRiskMessage.setWarnEtime(rsvrWarn.getTm());
                bRiskMessage.setLevel(rsvrWarn.getZLevel());
                bRiskMessage.setContent(String.format(messageFormat, "水库水位", formatNumber(rsvrWarn.getZ()), calc(rsvrWarn.getZ(), rsvrWarn.getZThreshold()), formatNumber(rsvrWarn.getZThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, rRsvrDuration);
            }
            if (rsvrWarn.getOtqLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("水库出流");
                bRiskMessage.setWarnStime(rsvrWarn.getTm());
                bRiskMessage.setWarnEtime(rsvrWarn.getTm());
                bRiskMessage.setLevel(rsvrWarn.getOtqLevel());
                bRiskMessage.setContent(String.format(messageFormat, "水库出流", formatNumber(rsvrWarn.getOtq()), calc(rsvrWarn.getOtq(), rsvrWarn.getOtqThreshold()), formatNumber(rsvrWarn.getOtqThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, rRsvrDuration);
            }
            bRiskLogService.updateRiskLog(rsvrWarn.getId(), rsvrWarn.getTm(), "水库水位、水库出流");
            r_rsvrTask();
        }
    }

    /**
     * 河道水位、河道流量(间隔时间：30分钟，数据中间隔是1小时)
     */
    @Scheduled(cron = "${task.r_river.cron}")
    public void r_riverTask() {
        BRiskLog bRiskLog = bRiskLogService.getBRiskLogByKind("河道水位、河道流量");
        RiverWarn riverWarn = waterWarnService.getRiverWarnAfterTime(bRiskLog.getRecordId(), bRiskLog.getRecordTime());
        if (riverWarn != null) {
            if (riverWarn.getZLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("河道水位");
                bRiskMessage.setWarnStime(riverWarn.getTm());
                bRiskMessage.setWarnEtime(riverWarn.getTm());
                bRiskMessage.setLevel(riverWarn.getZLevel());
                bRiskMessage.setContent(String.format(messageFormat2, riverWarn.getName(), "河道水位", formatNumber(riverWarn.getZ()), calc(riverWarn.getZ(), riverWarn.getZThreshold()), formatNumber(riverWarn.getZThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, rRiverDuration);
            }
            if (riverWarn.getQLevel() >= 1) {
                BRiskMessage bRiskMessage = new BRiskMessage();
                bRiskMessage.setKind("河道流量");
                bRiskMessage.setWarnStime(riverWarn.getTm());
                bRiskMessage.setWarnEtime(riverWarn.getTm());
                bRiskMessage.setLevel(riverWarn.getQLevel());
                bRiskMessage.setContent(String.format(messageFormat2, riverWarn.getName(), "河道流量", formatNumber(riverWarn.getQ()), calc(riverWarn.getQ(), riverWarn.getQThreshold()), formatNumber(riverWarn.getQThreshold())));
                bRiskMessageService.createRiskMessage(bRiskMessage, rRiverDuration);
            }
            bRiskLogService.updateRiskLog(riverWarn.getId(), riverWarn.getTm(), "河道水位、河道流量");
            r_riverTask();
        }
    }

    private String calc(Double a, Double b) {
        BigDecimal r = BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b));
        return formatNumber(r.doubleValue());
    }

    private String formatNumber(double num) {
        if (ObjectUtils.isEmpty(num)) return "";
        return BigDecimal.valueOf(num).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

}
