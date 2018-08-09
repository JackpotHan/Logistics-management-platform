package com.xiaohan.system.filter;

import com.xiaohan.base.BaseResult;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.enums.StatusEnum;
import com.xiaohan.system.cache.SimpleCacheManage;
import com.xiaohan.web.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class AdminServletContextListener implements ServletContextListener{
    private static Logger logger = LoggerFactory.getLogger(AdminServletContextListener.class);
    @Resource
    private PermissionService permissionService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("监听器开始执行");
        Permission ps = new Permission();
        ps.setStatus(NumberUtils.INTEGER_ZERO);
        BaseResult result = new BaseResult(permissionService.getPermissions(ps));

        Assert.isTrue(StringUtils.equals(result.getRespCode().toString(), StatusEnum.SUCCESS.getCode().toString()), result.getRespDesc());
        List<Permission> list = (List<Permission>) result.getRespData();
        try {
            for (Permission permission : list) {
                SimpleCacheManage.methodMap.put(permission.getPath(), permission.getValue());
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("『异常』方法缓存设置异常:", e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
