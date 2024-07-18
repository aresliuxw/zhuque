package com.cn.liu.controller;


import com.alibaba.fastjson.JSON;
import com.cn.liu.entity.PUser;
import com.cn.liu.service.DemoServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:54
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoServiceInf demoService;

    @GetMapping("/getUsers")
    public List<PUser> getUsers() {

        return demoService.getUsers();
    }

    @GetMapping("/exportUsers")
    public String exportUsers() {

        demoService.exportUsers();

        return "success";
    }


}


