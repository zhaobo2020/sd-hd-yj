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
@TableName("b_patrol_record") // 对应数据库表名
@Accessors(chain = true)
public class BPatrolRecord extends Model<BPatrolRecord> {

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
     * 位置
     */
    @Schema(description = "位置")
    private String position;
    /**
     * 简报
     */
    @Schema(description = "简报")
    private String title;
    /**
     * 文本内容
     */
    @Schema(description = "文本内容")
    private String content;
    /**
     * 文件路径
     */
    @Schema(description = "文件路径")
    private String path;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userid;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String state = "未处理";
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

