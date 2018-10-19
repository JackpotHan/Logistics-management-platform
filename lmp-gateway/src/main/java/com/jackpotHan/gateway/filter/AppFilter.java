package com.jackpotHan.gateway.filter;

import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.encrypt.MD5;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: Hanjt
 * @Date: 2018/8/10 14:21
 * @Description:
 */
@Component
public class AppFilter extends ZuulFilter {

    private static final String IS_SUCCESS = "isSuccess";

    @Value("${ignore.sign.path}")
    private String[] ignoreSignPaths;

    @Value("${ignore.gateway.urls}")
    private String[] ignoreGatewayUrls;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set(IS_SUCCESS, true);
        String url = ctx.getRequest().getRequestURL().toString();
        for (String path : ignoreSignPaths) {
            if (url.contains(path)) return false;
        }
        for (String path : ignoreGatewayUrls) {
            if (url.contains(path)) return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String appid = request.getParameter("appid");
        String sign = request.getParameter("sign");
        String bundle = request.getParameter("bundle");
        if (BaseUtil.isEmpty(appid, sign)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.setResponseBody(new BaseResult(BaseCode.ERR_PARAM.getCode(), "appid或者sign不能为空").toString());
            ctx.set(IS_SUCCESS, false);
            return null;
        }

        //获取所有参数  校验非空参数
        Map<String, String> params = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        //组装签名串
        StringBuffer sbf = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!"sign".equals(entry.getKey())) {
                sbf.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //校验签名
        if (!sign.equalsIgnoreCase(MD5.encode(sbf.toString()))) {//系统验签失败
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.setResponseBody(new BaseResult(BaseCode.ERR_SIGN).toString());
            ctx.set(IS_SUCCESS, false);
            return null;
        }
        return null;
    }
}

