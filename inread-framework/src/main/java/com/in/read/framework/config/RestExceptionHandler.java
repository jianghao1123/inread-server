package com.in.read.framework.config;

import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luyun on 2018/3/30.
 *
 * 全局异常处理
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public R baseErrorHandler(HttpServletRequest req, BusinessException e)  {
        logger.error("---ZGLException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        return R.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R defaultErrorHandler(HttpServletRequest req, Exception e)  {
        logger.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        return R.error(ApiErrorCode.SYS_ERROR);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class
            , TypeMismatchException.class
            , IllegalArgumentException.class
            , IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public R illegalStateException(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---IllegalStateException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        return R.error(ApiErrorCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = OptimisticLockingFailureException.class)
    @ResponseBody
    public R optimisticLockingFailureExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---OptimisticLockingFailureException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        return R.error(ApiErrorCode.BUSY_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public R httpMessageNotReadableExceptionExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---httpMessageNotReadableExceptionExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        return R.error(ApiErrorCode.PARAMETER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R methodArgumentNotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
        logger.error("---MethodArgumentNotValidExceptionHandler Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), ExceptionUtils.getStackTrace(e));
        if(e.getBindingResult() != null
                && e.getBindingResult().getAllErrors() != null
                && e.getBindingResult().getAllErrors().size() > 0
                && StringUtils.isNotBlank(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())) {
            return R.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        return R.error(ApiErrorCode.PARAMETER_ERROR);
    }
}
