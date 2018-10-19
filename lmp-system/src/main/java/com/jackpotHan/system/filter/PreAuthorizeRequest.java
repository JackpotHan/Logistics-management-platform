package com.jackpotHan.system.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.entity.system.User;
import com.jackpotHan.exception.CacheException;
import com.jackpotHan.system.cache.SimpleCacheManage;
import com.jackpotHan.utils.RequestWrapper;
import com.jackpotHan.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
@Order(3)
public class PreAuthorizeRequest implements Filter {

    private static Logger logger = LoggerFactory.getLogger(PreAuthorizeRequest.class);

    private static final String RESTART_SERVER = "服务器异常请重启服务!";

    private static final String CHARACTER_ENCODING = "utf-8";

    private static final String IS_SUCCESS = "isSuccess";

    private static final String ZUUL_SESSION_ID = "zuulSessionId";

    /*过滤登录无权限认证*/
    private static final String SECURITY_USER_LOGIN = "/user/login";

    private static final String SECURITY_USER_IS_LOGIN = "/user/isLogin";

    private static final String SECURITY_USER_LOGOUT = "/user/logout";

    private static final String BNH_ADMIN_SERVICE = "/bnhAdminAgent/commonsAgent";

    private static final String USER_NAME = "accountName";

    private static final String CONTENT_TYPE = "content-type";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("权限验证");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String isFlag = (String) request.getAttribute("isSuccess");
        if(!"true".equals(isFlag)) filterChain.doFilter(request,response);
        String path = request.getServletPath();
        if (SimpleCacheManage.methodMap.isEmpty()) {
            if (logger.isErrorEnabled()) {
                logger.error("『异常』授权权限缓存为空");
            }

            throw new CacheException(RESTART_SERVER);
        }
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.equals(SECURITY_USER_IS_LOGIN, path)) { //是否登录
            BaseResult<?> result;
            if (subject.isAuthenticated()) {
                result = new BaseResult<>(BaseCode.OK.getCode(), "已登录");
            } else {
                result = new BaseResult<>(BaseCode.FAIL.getCode(), "未登录");
            }
            request.setAttribute(IS_SUCCESS,"false");
            ResponseUtil.response(response, JSON.toJSONString(result));
            return;
        } else if (StringUtils.equals(SECURITY_USER_LOGOUT, path)) {//注销
            subject.logout();
            request.setAttribute(IS_SUCCESS,"false");
            BaseResult<?> result = new BaseResult<>(BaseCode.OK.getCode());
            ResponseUtil.response(response,JSON.toJSONString(result));
            return;
        } else if(StringUtils.equals(BNH_ADMIN_SERVICE, path)){
            filterChain.doFilter(request,response);
            return;
        }else if (SimpleCacheManage.methodMap.containsKey(path)) {
            String permission = SimpleCacheManage.methodMap.get(path);
            try {
                subject.checkPermission(permission);
            } catch (UnauthenticatedException ex){
                ResponseUtil.response(response,BaseCode.UNAUTHENTICATED.getDesc());
                return;
            }catch (IllegalArgumentException ex){
                ResponseUtil.response(response,BaseCode.ASSERT_ERROR.getDesc());
                 return;
            }catch (AuthorizationException ex){
                ResponseUtil.response(response,BaseCode.UNAUTHORIZED.getDesc());
                return;
            }
        }
        User user = (User) subject.getPrincipal();
        RequestWrapper requestWrapper = new RequestWrapper(request);
        JSONObject jsonObject = new JSONObject();
        if(user != null){
            jsonObject.put("operatorId",user.getId() + "");
            jsonObject.put("operator",user.getRealName());
        }
       // jsonObject.put(ZUUL_SESSION_ID,request.getSession().getId());
        requestWrapper = new RequestWrapper(request,jsonObject);
        filterChain.doFilter(requestWrapper,response);
    }

    @Override
    public void destroy() {
        System.out.println("***************************************************************");
    }

    private void writeResponse(InputStream zin, OutputStream out) throws Exception {
        byte[] bytes = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = zin.read(bytes)) != -1) {
            try {
                out.write(bytes, 0, bytesRead);
                out.flush();
            }
            catch (IOException ex) {
                // ignore
            }
            // doubles buffer size if previous read filled it
            if (bytesRead == bytes.length) {
                bytes = new byte[bytes.length * 2];
            }
        }
    }
}
