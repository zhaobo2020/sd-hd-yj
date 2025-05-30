package com.sl.water.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("b_risk_message") // 对应数据库表名
@Accessors(chain = true)
public class BRiskMessage extends Model<BRiskMessage> {

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
     * 级别
     */
    @Schema(description = "级别")
    private Integer level;
    /**
     * 预警详情
     */
    @Schema(description = "预警详情")
    private String content;
    /**
     * 预警首次时间
     */
    @Schema(description = "预警首次时间")
    private LocalDateTime warnStime;
    /**
     * 预警最新时间
     */
    @Schema(description = "预警最新时间")
    private LocalDateTime warnEtime;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

}

