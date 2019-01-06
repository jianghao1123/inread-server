package com.in.read.framework.constant;

/**
 * Created by luyun on 2019/1/6.
 */
public interface ErrorCode {
    /**
     * 成功码
     */
    int SUCCESS = 0;
    /**
     * 一般的业务异常以10000开头
     */
    int BUSINESS_ERROR_CODE = 10000;

    /**
     * 系统繁忙
     */
    int BUSY_ERROR_CODE = 10001;

    /**
     * 非法操作
     */
    int ACCESS_ERROR = 10002;

    /**
     * 一般的参数异常以20000开头
     */
    int PARAMETER_ERROR_CODE = 20000;

    /**
     * 认证相关
     */
    int AUTH_ERROR_CODE = 30000;

    /**
     * 系统异常以40000开头
     */
    int SYSTEM_ERROR_CODE = 40000;
}
