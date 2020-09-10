package com.dfbz.bugSystem.config;

import com.dfbz.bugSystem.shiro.ShiroRealm;

import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Properties;


/**
 * shiro配置类
 */
@Configuration

public class ShiroConfig {

    /**
     * 生命周期进程管理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    /**
     * 配置安全校验管理器
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("rememberMeManager") RememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());//登录认证
        //securityManager.setRememberMeManager(rememberMeManager);//设置cookie管理器
        return securityManager;
    }



    /**
     * 配置FactoryBean对象，是shiro过滤工厂类
     */
   @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){


        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/user/login");//登录url
        factoryBean.setSuccessUrl("/home");//登录成功后跳转url
        factoryBean.setUnauthorizedUrl("/403");//未授权

        LinkedHashMap<String,String> filterChainMap = new LinkedHashMap<>();



       filterChainMap.put("/css/**","anon");
        filterChainMap.put("/js/**","anon");
        filterChainMap.put("/images/**","anon");
        filterChainMap.put("/fonts/**","anon");

        filterChainMap.put("/user/doLogin","anon");
        filterChainMap.put("/home","anon");
       filterChainMap.put("/druid/**","anon");

       filterChainMap.put("/**","authc");

        factoryBean.setFilterChainDefinitionMap(filterChainMap);

        return factoryBean;
    }


    /**
     * 自定义Realm
     */
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }


    /**
     * 方法初始化对象，在使用反射生成Security对象时，为对象设置校验管理器
     * @param securityManager
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager){
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager);
        return bean;
    }


    /**
     * 异常处理
     * @return
     */
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mapping = new Properties();
        mapping.setProperty("DatabaseException","databaseError");
        mapping.setProperty("UnauthorizedException","403");
        //可继续配置需要由shiro处理的其它异常及其关键字
        resolver.setExceptionMappings(mapping);
        resolver.setDefaultErrorView("error");
        resolver.setExceptionAttribute("ex");
        return resolver;
    }


    /**
     * cookie对象
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");//与登录页面中，记住我的input的name属性一致
        cookie.setMaxAge(6000);//cookie 最长有效时间 单位秒
        return cookie;

    }

    /**
     * cookie管理器对象
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //cookieRememberMeManager.setCipherKey(Base64.decode("4dhd73jdsDF5ejG"));//设置cookie加密密钥
        return cookieRememberMeManager;
    }

    /**
     * 开启注解通知实现权限校验
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor
        authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }







}
