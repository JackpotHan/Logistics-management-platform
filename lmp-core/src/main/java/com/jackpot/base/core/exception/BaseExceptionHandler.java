package com.jackpot.base.core.exception;

import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseException;
import com.jackpot.base.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    public BaseExceptionHandler() {}

    @ExceptionHandler({BaseException.class})
    @ResponseBody
    public BaseResult jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
        log.error(e.getMessage(), e);
        return new BaseResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({ParamsException.class})
    @ResponseBody
    public BaseResult paramsErrorHandler(HttpServletRequest req, ParamsException e) throws Exception {
        log.error(e.getMessage(), e);
        return new BaseResult(BaseCode.PARAM_ERR.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResult exceptionErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        return new BaseResult(BaseCode.RESPCODE_FAIL.getCode(), e.getMessage());
    }
}
