package com.dfbz.bugSystem.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 基于负载均衡的跨项目数据访问
     * @return
     */

    @RequestMapping("/findUser")
    public String findUser(){
        String projectResutl = restTemplate.getForEntity("http://bug-system-project/bug/project/showProject",String.class).getBody();
        return projectResutl;

    }







}
