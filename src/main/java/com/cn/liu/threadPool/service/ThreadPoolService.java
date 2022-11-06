package com.cn.liu.threadPool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * 自定义线程池测试1
 *
 * @Author lxw
 * @Date 2022-04-05 17:55
 **/
@Service
public class ThreadPoolService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolService.class);

    @Async("customAsyncThreadPool") //指定使用哪个线程池配置，不然会使用spring默认的线程池
    public void executeAsync() {
//        System.out.println("executeAsync");
        LOGGER.info("当前运行线程名称:{}", Thread.currentThread().getName());
    }


}


