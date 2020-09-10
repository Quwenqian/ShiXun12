package com.dfbz.bugSystem.filter;

import com.dfbz.bugSystem.po.User;
import com.dfbz.bugSystem.util.Contacts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 请求处理之前进行调用的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("LoginInterceptor pre method....");

        User user = (User) request.getSession().getAttribute(Contacts.SESSION_KEY_USER);
        if(user!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/user/login");
        return false;
    }


    /**
     * 在请求处理之后调用的方法，并且在视图渲染之前执行
     *  可以进行视图对象的通用性处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginInterceptor post method ....");
    }


    /**
     * 在请求结束之后调用的方法，并且调用时DispatcherServlet完成了对视图的渲染工作
     *  进行资源回收操作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginInterceptor after method ....");

    }
}
