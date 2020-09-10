package com.dfbz.bugSystem.config;

import com.dfbz.bugSystem.filter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用于进行登录拦截器配置的核心配置类
 * 会在@SpringBootApplication启动项目后，对项目中所有的配置进行加载
 */
//@Configuration
public class LoginConfig implements WebMvcConfigurer {


    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**").excludePathPatterns("/user/login","/user/doLogin","/home",
                "/**/*.html","/**/*.css","/**/*.js","/**/*.png","/**/*.ico");
    }
}
