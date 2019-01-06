package com.in.read.framework.exception;

import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class BusinessException extends Exception{
    private int code;
    private String msg;

    public BusinessException(){

    }

    public BusinessException(ApiError apiError){
        this.code = apiError.getCode();
        this.msg = apiError.getMsg();
    }
}
