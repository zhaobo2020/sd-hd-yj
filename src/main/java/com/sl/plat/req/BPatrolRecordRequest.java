package com.sl.plat.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "人工巡查")
public class BPatrolRecordRequest extends PageQueryParam {

    @Schema(description = "类型（语音，短信，图片，视频）",example = "语音")
    private String kind;
}
