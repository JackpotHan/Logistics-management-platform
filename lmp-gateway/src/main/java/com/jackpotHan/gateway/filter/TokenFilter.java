package com.jackpotHan.gateway.filter;

import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.base.BaseUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Hanjt
 * @Date: 2018/8/10 14:42
 * @Description:
 */
@Component
public class TokenFilter extends ZuulFilter{

    private static final String IS_SUCCESS = "isSuccess";

    @Value("${ignore.gateway.urls}")
    private String[] ignoreGatewayUrls;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String url = ctx.getRequest().getRequestURL().toString();
        for (String path : ignoreGatewayUrls) {
            if (url.contains(path)) return false;
        }
        return (boolean) ctx.get("VAIL_TOKEN") && (boolean) ctx.get(IS_SUCCESS);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String appid = request.getParameter("appid");
        String token = request.getParameter("token");
        if (BaseUtil.isEmpty(token)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.setResponseBody(new BaseResult(BaseCode.TOKEN_EXPIRED.getDesc()).toString());
            ctx.set(IS_SUCCESS, false);
            return null;
        }
        //校验不通过 设置返回信息
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(HttpStatus.OK.value());
        ctx.setResponseBody(new BaseResult(BaseCode.TOKEN_EXPIRED).toString());
        ctx.set(IS_SUCCESS, false);
        return null;
    }

}
