package com.xiaohan.lmpweb;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Hanjt
 * @Date: 2018/8/4 10:33
 * @Description:
 */
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //登陆查看新的是账户密码
        servletRegistrationBean.addInitParameter("loginUsername","root");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js.*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
