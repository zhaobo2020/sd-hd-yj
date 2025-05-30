package com.sl.common;




import com.sl.common.exception.ErrorCode;
import com.sl.common.exception.ErrorCodeEnum;
import com.sl.common.exception.GlobalErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData<T> implements Serializable {

    /**
     * 业务状态码，区别于HTTP协议状态码
     */
    private Integer code;

    /**
     * 业务提示消息，一般在增删改操作的时候返回
     */
    private String message;

    /**
     * 返回前端的交互数据
     */
    private T data;

    private boolean ok;

    /**
     * 默认构造函数
     */
    public ResponseData() {
    }

    /**
     * 全参构造函数
     */
    public ResponseData(Integer code, String message, T data , boolean ok) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.ok = ok;
    }

    /**
     * 成功返回的静态方法
     * @param data 返回的数据
     * @param <T> 数据类型
     * @return ResponseData
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(GlobalErrorCodeEnum.SUCCESS.getCode(), GlobalErrorCodeEnum.SUCCESS.getMessage(), data , true);
    }

    /**
     * 失败返回的静态方法
     * @param errorCode 错误码枚举
     * @param <T> 数据类型
     * @return ResponseData
     */
    public static <T> ResponseData<T> error(ErrorCodeEnum errorCode) {
        return new ResponseData<>(errorCode.getCode(), errorCode.getMessage(), null , false);
    }

    /**
     * 失败返回的静态方法，附带错误信息
     * @param errorCode 错误码枚举
     * @param message 错误消息
     * @param <T> 数据类型
     * @return ResponseData
     */
    public static <T> ResponseData<T> error(ErrorCodeEnum errorCode, String message) {
        return new ResponseData<>(errorCode.getCode(), message, null,false);
    }

    public static <T> ResponseData<T> error(ErrorCode errorCode, String message) {
        return new ResponseData<>(errorCode.getCode(), message, null ,false);
    }



}
