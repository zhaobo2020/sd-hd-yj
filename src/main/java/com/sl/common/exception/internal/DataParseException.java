package com.sl.common.exception.internal;

import com.sl.common.exception.ErrorCode;
import com.sl.common.exception.GlobalErrorCodeEnum;
import com.sl.common.exception.ServiceException;

// 自定义的数据解析异常类
public class DataParseException extends ServiceException {
    public DataParseException() {
        super(GlobalErrorCodeEnum.DATA_PARSE_ERROR.toErrorCode()) ;
    }

    public DataParseException(String message) {
        super(GlobalErrorCodeEnum.DATA_PARSE_ERROR.toErrorCode(), message) ;
    }

    public DataParseException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DataParseException(ErrorCode errorCode) {
        super(errorCode);
    }
}