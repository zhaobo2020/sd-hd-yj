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
@TableName("b_risk_person") // 对应数据库表名
@Accessors(chain = true)
public class BRiskPerson extends Model<BRiskPerson> {

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
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;
    /**
     * 单位
     */
    @Schema(description = "单位")
    private String dept;
    /**
     * 地址
     */
    @Schema(description = "地址")
    private String address;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
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

