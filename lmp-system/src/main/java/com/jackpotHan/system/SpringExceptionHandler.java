package com.jackpotHan.system;

import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.exception.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class SpringExceptionHandler {

    @ExceptionHandler(CacheException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BaseResult<CacheException> handlerFieldErrorException(CacheException ex,
                                                          HttpServletRequest request) throws IOException {
        log.error("缓存异常", ex);
        return new BaseResult(BaseCode.ERR.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BaseResult<Exception> handlerSystemException(Exception ex, HttpServletRequest request)
            throws IOException {
        if (log.isErrorEnabled()) {
            log.error("『系统』异常未知: [{}]", ex);
        }
        return new BaseResult<>(BaseCode.ERR.getCode());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BaseResult<MissingServletRequestParameterException> handlerMissingParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) throws IOException {
        if (log.isErrorEnabled()) {
            log.error("『检测』参数缺失: [{}]", ex);
        }

        return new BaseResult<>(BaseCode.ERR_PARAM.getCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BaseResult<IllegalArgumentException> handlerIllegalArgumentException(IllegalArgumentException ex,
                                                                     HttpServletRequest request) throws IOException {
        if (log.isErrorEnabled()) {
            log.error("『断言』业务断言错误: [{}]", ex);
        }
        return new BaseResult<>(BaseCode.ASSERT_ERROR.getCode());
    }

}
