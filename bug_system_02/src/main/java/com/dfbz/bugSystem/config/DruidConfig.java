package com.dfbz.bugSystem.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfig {


   @Bean
    public ServletRegistrationBean druidServlet(){
       ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
       servletRegistrationBean.addInitParameter("allow","127.0.0.1");
       servletRegistrationBean.addInitParameter("loginUsername","admin");
       servletRegistrationBean.addInitParameter("loginPassword","admin");
       servletRegistrationBean.addInitParameter("restEnable","false");

       return servletRegistrationBean;
    }

   @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
       filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }




}
