package com.in.read.framework.exception;

import com.in.read.framework.constant.ErrorCode;

/**
 * Created by luyun on 2019/1/6.
 */
public enum ApiErrorCode implements ApiError {

    SYS_ERROR(ErrorCode.SYSTEM_ERROR_CODE, "操作失败"),
    SUCCESS(ErrorCode.SUCCESS, "执行成功"),
    PARAMETER_ERROR(ErrorCode.PARAMETER_ERROR_CODE, "参数错误"),
    BUSY_ERROR(ErrorCode.BUSY_ERROR_CODE, "系统繁忙"),
    AUTH_ERROR(ErrorCode.AUTH_ERROR_CODE, "请重新登录"),
    AUTH_EXPIRE_ERROR(ErrorCode.AUTH_ERROR_CODE + 1, "刷新登录凭证"),
    ACCESS_ERROR(ErrorCode.ACCESS_ERROR, "非法操作"),
    BUSINESS_ERROR(ErrorCode.BUSINESS_ERROR_CODE, "系统异常");

    private final int code;

    private final String msg;

    private ApiErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", new Object[]{Long.valueOf(this.code), this.msg});
    }
}
