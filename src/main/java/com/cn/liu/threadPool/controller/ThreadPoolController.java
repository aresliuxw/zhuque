package com.cn.liu.threadPool.controller;


import com.cn.liu.threadPool.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义线程池测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:54
 **/
@RestController
public class ThreadPoolController {

    @Autowired
    private ThreadPoolService threadPoolService;

    @GetMapping("/threadPoolTest")
    public void threadPoolTest() {
        for (int i = 0; i < 60; i++) { //线程数多于 配置项里的最大线程数+队列容量 则会触发拒绝策略
            threadPoolService.executeAsync();
        }
    }

}


