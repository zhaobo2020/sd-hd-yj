package com.sl.water.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 降雨量信息响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "人工巡查")
@Accessors(chain = true)
public class BPatrolRecordStaDTO {

    /**
     * 语音上报统计值
     */
    @Schema(description = "语音上报统计值")
    private Integer voiceCount = 0;

    /**
     * 短信上报统计值
     */
    @Schema(description = "短信上报统计值")
    private Integer smsCount = 0;

    /**
     * 图像上报统计值
     */
    @Schema(description = "图像上报统计值")
    private Integer imageCount = 0;


    /**
     * 视频上报统计值
     */
    @Schema(description = "视频上报统计值")
    private Integer videoCount = 0;


}