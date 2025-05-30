package com.sl.plat.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageQueryParam {

    @Schema(description = "分页page",example = "1")
    @Min(value = 1, message = "page必须大于0")
    private Integer page = 1;
    @Schema(description = "分页size",example = "10")
    private Integer size = 10;
}
