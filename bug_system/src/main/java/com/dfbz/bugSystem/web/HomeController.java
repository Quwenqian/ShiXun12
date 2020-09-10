package com.dfbz.bugSystem.web;

import com.dfbz.bugSystem.po.User;
import com.dfbz.bugSystem.util.CommonEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/403")
    public String error(){
        return "403";
    }



    @RequestMapping("/home")
    public String home(Model model){

        //登录成功后，获取用户登录信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        System.out.println("home  method:"+user);


        return "bugHome";

    }




}
