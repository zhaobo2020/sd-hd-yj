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
@TableName("b_risk_log") // 对应数据库表名
@Accessors(chain = true)
public class BRiskLog extends Model<BRiskLog> {

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
     * 记录Id
     */
    @Schema(description = "记录Id")
    private Long recordId;
    /**
     * 记录时间
     */
    @Schema(description = "记录时间")
    private LocalDateTime recordTime;
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

