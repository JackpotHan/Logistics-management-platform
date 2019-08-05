package com.lmp.admin.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseResult;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroLoginFilter extends FormAuthenticationFilter{

    /**
     * 这个方法是未登录需要执行的方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            //设置响应头
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json");
            //设置返回的数据
            BaseResult resultData = new BaseResult();
            resultData.setRespCode(BaseCode.SESSION_LOSE.getCode());
            resultData.setRespDesc(BaseCode.SESSION_LOSE.getMsg());
            resultData.setRespData(null);
            //写回给客户端
            PrintWriter out = httpResponse.getWriter();
            out.write(JSONObject.toJSONString(resultData));
            //刷新和关闭输出流
            out.flush();
            out.close();
        } else {
            //设置响应头
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json");
            //设置返回的数据
            BaseResult resultData = new BaseResult();
            resultData.setRespCode(BaseCode.UNAUTHORIZED_ERR.getCode());
            resultData.setRespDesc(BaseCode.UNAUTHORIZED_ERR.getMsg());
            resultData.setRespData(null);
            //写回给客户端
            PrintWriter out = httpResponse.getWriter();
            out.write(JSONObject.toJSONString(resultData));
            //刷新和关闭输出流
            out.flush();
            out.close();
        }
        return false;
    }
}
