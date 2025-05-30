package com.sl.water.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("b_threshold_water") // 对应数据库表名
@Accessors(chain = true)
public class BThresholdWater extends Model<BThresholdWater> {

    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 类型
     */
    @Schema(description = "类型")
    private String kind;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 阀值
     */
    @Schema(description = "阀值")
    private Double threshold;
    /**
     * 级别
     */
    @Schema(description = "级别")
    private Integer level;
}

