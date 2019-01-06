package com.in.read.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.TokenExpiredException;
import com.in.read.framework.protocol.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luyun on 2018/3/11.
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Autowired
    public DefaultAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.OK.value());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof TokenExpiredException) {
            mapper.writeValue(response.getWriter(), R.error(ApiErrorCode.AUTH_EXPIRE_ERROR));
        } else {
            mapper.writeValue(response.getWriter(), R.error(ApiErrorCode.AUTH_ERROR));
        }
    }
}
