package com.sl.common.exception;

public interface ErrorCodeEnum {
    /**
     * 获取错误码
     * @return 错误码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();

    /**
     * 转换为统一的 ErrorCode 对象
     * @return ErrorCode 对象
     */
    default ErrorCode toErrorCode() {
        return new ErrorCode(getCode(), getMessage());
    }
}
