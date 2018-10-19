package com.jackpotHan.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.utils.DateUtil;
import com.jackpotHan.utils.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Order(1)
public class LoggerFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFilter.class);

    @Value("${ignore.logger.method}") private String[] ignoreLoggerMethod;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Date requestTime = DateUtil.getCurrentDate();
        String requestContent = "", returnContent = "", timestamp = DateUtil.formatNowTimeStamp();
        //忽略日志直接下一步
        if (isIgnoreLogger(request)){
            LOGGER.info(String.format("%s >>> %s", timestamp, request.getRequestURI()));
            chain.doFilter(request, response);
        } else {
            requestContent = JSON.toJSONString(request.getParameterMap());
            LOGGER.info(String.format("%s >>> %s", timestamp, requestContent));
            ResponseWrapper responseWrapper = new ResponseWrapper(response);
            chain.doFilter(request, responseWrapper);
            returnContent = responseWrapper.getResponseContent();
            //打印返回对象
            LOGGER.info(String.format("%s >>> %s", timestamp, returnContent));
        }
    }

    private boolean isIgnoreLogger(HttpServletRequest request){
        String method = request.getParameter("method");
        if (BaseUtil.isEmpty(method)) return true;
        for (String s : ignoreLoggerMethod){
            if (method.contains(s)) return true;
        }
        return false;
    }
}
