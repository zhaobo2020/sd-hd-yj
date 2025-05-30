package com.sl.common.exception.model;

import com.sl.common.exception.GlobalErrorCodeEnum;
/**
 * 模型结果解析异常
 */
public class ModelResultParseException extends ModelException {
    public ModelResultParseException() {
        super(GlobalErrorCodeEnum.MODEL_RESULT_PARSE_ERROR.toErrorCode());
    }

    public ModelResultParseException(String message) {
        super(GlobalErrorCodeEnum.MODEL_RESULT_PARSE_ERROR.toErrorCode(), message);
    }
}