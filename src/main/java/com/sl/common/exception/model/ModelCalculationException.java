package com.sl.common.exception.model;

import com.sl.common.exception.GlobalErrorCodeEnum;

/**
 * 模型计算异常
 */
public class ModelCalculationException extends ModelException {
    public ModelCalculationException() {
        super(GlobalErrorCodeEnum.MODEL_CALCULATION_FAILED.toErrorCode());
    }

    public ModelCalculationException(String message) {
        super(GlobalErrorCodeEnum.MODEL_CALCULATION_FAILED.toErrorCode(), message);
    }


}