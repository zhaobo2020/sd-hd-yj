package com.sl.common.exception;

public class SchemeNotFoundException extends ServiceException {
    /**
     * 无参构造（使用错误码默认消息）
     */
    public SchemeNotFoundException() {
        super(GlobalErrorCodeEnum.SCHEME_NOT_FOUND.toErrorCode());
    }



    /**
     * 带自定义消息的构造（兼容扩展场景）
     * @param message 额外说明消息
     */
    public SchemeNotFoundException(String message) {
        super(GlobalErrorCodeEnum.SCHEME_NOT_FOUND.toErrorCode(), message);
    }
}