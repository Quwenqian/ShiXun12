package com.dfbz.bugSystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
public class ProjectController {


    @RequestMapping("/showProject")
    @ResponseBody
    public String showProject(){
        return "success";
    }


}
