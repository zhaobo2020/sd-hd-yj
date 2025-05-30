package com.sl.water.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * @author duan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("b_threshold_rainfall") // 对应数据库表名
@Builder
public class ThresholdRainfall extends Model<ThresholdRainfall> {

    /**
     * 自增主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;
    /**
     * 预报总时长(小时)
     */
    private Integer duration;

    /**
     * 预报开始时间
     */
    private Double rainfall;
    /**
     * 预报结束时间
     */
    private Integer level;

}

