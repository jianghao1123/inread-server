package com.in.read.framework.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by luyun on 2019/1/6.
 */
public class UserAccessDeniedException extends AuthenticationException {
    public UserAccessDeniedException(String msg) {
        super(msg);
    }
}
