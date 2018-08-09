package com.xiaohan.system.filter;

import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.system.cache.SimpleCacheManage;
import com.xiaohan.web.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Reason: Authentication、数据验证、选择源服务地址.
 *
 * @author Chen Lingang
 * @version $Id: PreRequest, v 0.1 17/2/14 下午11:11
 */
@Component
@Order(2)
public class PreCacheRequest extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(PreCacheRequest.class);

    private static final String CONTENT_TYPE = "content-type";

    private static final String CHARACTER_ENCODING = "utf-8";

    private static final String RESOLVE_EXCEPTION = "方法缓存设置异常";

    @Resource
    private PermissionService permissionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("打印请求参数");
        request.setCharacterEncoding("UTF-8");
        //生成当前时间戳  标识唯一请求
        String serial = System.currentTimeMillis()+"  ";
        //打印请求IP地址和请求路径
        logger.info(serial + request.getRemoteHost() + "----" + request.getRequestURI());
        //打印请求参数
        StringBuilder sbf= new StringBuilder(serial).append("[");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            sbf.append(entry.getKey()).append(":").append(entry.getValue()[0]).append(",");
        }
        logger.info(sbf.append("]").toString());
        response.setHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);response.setCharacterEncoding(CHARACTER_ENCODING);
        setUpPermissionCache();
        request.setAttribute("isSuccess","true");
        filterChain.doFilter(request,response);
    }

    private void setUpPermissionCache() {
        Permission ps = new Permission();
        ps.setStatus(NumberUtils.INTEGER_ZERO);
        BaseResult<?> result = new BaseResult<>(permissionService.getPermissions(ps));

        Assert.isTrue(StringUtils.equals(result.getRespCode().toString(), BaseCode.OK.getCode().toString()), result.getRespDesc());
        List<Permission> list = (List<Permission>) result.getRespData();
        try {
            for (Permission permission : list) {
                SimpleCacheManage.methodMap.put(permission.getPath(), permission.getValue());
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("『异常』方法缓存设置异常:", e);
            }
            Assert.isTrue(false, RESOLVE_EXCEPTION);
        }
    }
}
