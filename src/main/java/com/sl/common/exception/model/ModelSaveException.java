package com.sl.common.exception.model;

import com.sl.common.exception.GlobalErrorCodeEnum;

public class ModelSaveException extends ModelException {
    public ModelSaveException() {
        super(GlobalErrorCodeEnum.MODEL_SAVE_FAILED.toErrorCode());
    }

    public ModelSaveException(String message) {
        super(GlobalErrorCodeEnum.MODEL_SAVE_FAILED.toErrorCode(), message);
    }
}
