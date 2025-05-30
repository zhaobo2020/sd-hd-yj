package com.sl.common.exception;

public enum GlobalErrorCodeEnum implements ErrorCodeEnum {

    // ========== 成功状态码 ==========

    SUCCESS(0, "操作成功"),

    // ========== 客户端错误段 ==========

    BAD_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "账号未登录"),
    FORBIDDEN(403, "没有该操作权限"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),
    LOCKED(423, "请求失败，请稍后重试"),
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

    // ========== 服务端错误段 ==========

    INTERNAL_SERVER_ERROR(500, "系统异常"),
    NOT_IMPLEMENTED(501, "功能未实现/未开启"),
    ERROR_CONFIGURATION(502, "错误的配置项"),

    // ========== 自定义错误段 ==========

    REPEATED_REQUESTS(900, "重复请求，请稍后重试"),
    DEMO_DENY(901, "演示模式，禁止写操作"),

    // ========== 其他常见错误 ==========
    UNKNOWN(999, "未知错误"),
    //    业务异常
    BUSINESS_ERROR(910, "业务异常"),

    // ======== 业务级错误码（2000-2999）====
    MODEL_INPUT_INVALID(2001, "模型输入参数无效"),
    MODEL_CALCULATION_FAILED(2002, "模型计算失败"),
    MODEL_RESULT_PARSE_ERROR(2003, "模型结果解析失败"),

    MODEL_SAVE_FAILED(2004, "模型输入参数无效"),

    SCHEME_NOT_FOUND (2005, "方案不存在"),
//    方案还没准备好
    SCHEME_NOT_READY(2006, "方案还没准备好"),
    // ======== 内部错误码（3000-3999）====
    DATA_PARSE_ERROR(3001, "自定义数据解析失败");




    private final Integer code; // 错误码
    private final String message; // 错误信息

    GlobalErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
