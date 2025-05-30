package com.sl.common.exception.model;

import com.sl.common.exception.GlobalErrorCodeEnum;

/**
 * 模型输入参数无效异常
 */
public class ModelInputException extends ModelException {
    public ModelInputException() {
        super(GlobalErrorCodeEnum.MODEL_INPUT_INVALID.toErrorCode());
    }

    public ModelInputException(String message) {
        super(GlobalErrorCodeEnum.MODEL_INPUT_INVALID.toErrorCode(), message);
    }


}