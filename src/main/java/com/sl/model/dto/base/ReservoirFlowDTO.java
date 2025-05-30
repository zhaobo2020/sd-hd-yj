package com.sl.model.dto.base;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class ReservoirFlowDTO {

    /**
     * 时间序列数据
     * <p>
     * 表示入流过程的时间节点集合
     * </p>
     * @see NotEmpty 注解约束该字段不能为空列表（至少包含一个时间节点）
     */
    @NotEmpty(message = "时间序列不能为空")
    private List<LocalDateTime> tm;

    /**
     * 入流过程流量数据单位为m³/s（立方米每秒）
     */
    @NotEmpty(message = "入流数据不能为空")
    private List<Double> inQ;

    /**
     * 入流过程流量数据单位为m³/s（立方米每秒）
     */
    @NotEmpty(message = "出流数据不能为空")
    private List<Double> ontQ;

    /**
     * 时段初库容
     */
    @NotEmpty(message = "初始库容不能为空")
    private Double initialStorage;




}
