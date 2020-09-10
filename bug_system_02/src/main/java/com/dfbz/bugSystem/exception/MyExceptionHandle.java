package com.dfbz.bugSystem.exception;

import com.dfbz.bugSystem.util.CommonEnum;
import com.dfbz.bugSystem.util.ResultBody;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.x509.AuthorityInfoAccessExtension;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * 自定义全局异常处理类
 *  对项目中各种需要单独处理的异常类型进行方法定义
 */
@ControllerAdvice
public class MyExceptionHandle {



    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandle(HttpServletRequest request,NullPointerException e){

        System.out.println("发生了空指针异常，原因是："+e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }


    @ExceptionHandler(value = AuthorizationException.class)
    //@ResponseBody
    public String exceptionHandle(HttpServletRequest request, AuthorizationException e){

        System.out.println("shiro权限访问异常，原因是："+e);
        //ResultBody.error(CommonEnum.NOT_PERMISSION)
        return "403";
    }




    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandle(HttpServletRequest request,Exception e){

        System.out.println("发生了未知异常，原因是："+e);
        e.printStackTrace();
        return ResultBody.error(CommonEnum.SERVER_BUSY);
    }


}
