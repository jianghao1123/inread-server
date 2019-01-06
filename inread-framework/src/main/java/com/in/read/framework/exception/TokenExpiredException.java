package com.in.read.framework.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by luyun on 2019/1/6.
 */
public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String msg) {
        super(msg);
    }
}
