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
@TableName("b_risk_tip") // 对应数据库表名
@Accessors(chain = true)
public class BRiskTip extends Model<BRiskTip> {

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
     * 状态
     */
    @Schema(description = "状态")
    private String state = "未发送";
    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
    /**
     * 接收者
     */
    @Schema(description = "接收者")
    private String receiver;
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

