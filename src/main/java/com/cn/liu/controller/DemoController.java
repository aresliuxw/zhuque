package com.cn.liu.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:54
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test")
    public String test() {

        return "succ";
    }


}


