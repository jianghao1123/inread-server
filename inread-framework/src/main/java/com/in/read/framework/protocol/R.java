package com.in.read.framework.protocol;

import com.in.read.framework.constant.ErrorCode;
import com.in.read.framework.exception.ApiError;
import lombok.Data;


/**
 * Created by luyun on 2018/3/10.
 */
@Data
public class R<T> {

    private static final int CODE_SUCCESS = ErrorCode.SUCCESS;
    private static final int CODE_BUSINESS_ERROR = ErrorCode.BUSINESS_ERROR_CODE;
    private static final String SUCCESS ="success";
    // 缓存12个小时
    private static final int EXPIRE_TIME = 1000 * 60 * 60 * 12;

    private int code = CODE_SUCCESS;

    private String msg = SUCCESS;

    private T data;

    private long expireTime = 0;

    public R(){
    }

    public R(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public R(Throwable e){
        this.msg = e.getMessage();
        this.code = CODE_BUSINESS_ERROR;
    }

    public R cache(){
        this.expireTime = EXPIRE_TIME;
        return this;
    }

    public static final <T> R<T> ok(T data) {
        R<T> response = new R<>();
        response.setData(data);
        return response;
    }

    public static final R ok() {
        R<Object> response = new R<>();
        return response;
    }

    public static final R<Void> error(int code, String msg) {
        R<Void> response = new R<>(code,msg);
        return response;
    }

    public static final R<Void> error(String msg) {
        R<Void> response = new R<>(CODE_BUSINESS_ERROR,msg);
        return response;
    }

    public static final R<Void> error(ApiError error) {
        R<Void> response = new R<>(error.getCode(),error.getMsg());
        return response;
    }

}
