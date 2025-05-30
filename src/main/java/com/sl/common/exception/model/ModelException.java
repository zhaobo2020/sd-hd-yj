package com.sl.common.exception.model;

import com.sl.common.exception.ErrorCode;
import com.sl.common.exception.ServiceException;

public class ModelException extends ServiceException {


    public ModelException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ModelException(ErrorCode errorCode) {
        super(errorCode);
    }
}
