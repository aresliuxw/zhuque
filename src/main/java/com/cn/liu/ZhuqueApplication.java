package com.cn.liu;

import com.cn.liu.threadPool.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //开启异步线程支持 线程池
@EnableConfigurationProperties({TaskThreadPoolConfig.class}) //开启配置属性支持 线程池
@SpringBootApplication
public class ZhuqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuqueApplication.class, args);
    }

}
