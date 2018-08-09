package com.xiaohan.web;


import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Hanjt
 * @Date: 2018/8/5 15:33
 * @Description:
 */
@ControllerAdvice
public class ToManyExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToManyExceptionHandler.class);

    @ExceptionHandler({TooManyResultsException.class})
    @ResponseBody
    public BaseResult exceptionErrorHandler(HttpServletRequest req, TooManyResultsException e) throws Exception {
        LOGGER.error(e.getMessage(), e);
        return new BaseResult(BaseCode.ERR.getCode(), "系统错误,请确认参数是否正确");
    }

}
