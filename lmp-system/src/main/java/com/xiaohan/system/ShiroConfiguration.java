package com.xiaohan.system;

import com.google.common.collect.Lists;
import com.xiaohan.system.shiro.MyShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 15:12
 * @Description:
 */
@Configuration
@Slf4j
public class ShiroConfiguration {

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:cache/ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "adminShiroRealm")
    public MyShiroRealm getAdminShiroRealm() {
        MyShiroRealm adminShiroRealm = new MyShiroRealm();
        return adminShiroRealm;
    }

    @Bean(name = "customAuthorizer")
    public ModularRealmAuthorizer getModularRealmAuthorizer() {
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        modularRealmAuthorizer.setRealms(Lists.newArrayList(getAdminShiroRealm()));
        return modularRealmAuthorizer;
    }

    @Bean(name = "customAuthenticator")
    public ModularRealmAuthenticator getModularRealmAuthenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setRealms(Lists.newArrayList(getAdminShiroRealm()));
        return modularRealmAuthenticator;
    }

    /**
     * Shiro's main business-tier object for web-enabled applications -
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setAuthorizer(getModularRealmAuthorizer());
        dwsm.setAuthenticator(getModularRealmAuthenticator());
        //<!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        return shiroFilterFactoryBean;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return aasa;
    }

}
