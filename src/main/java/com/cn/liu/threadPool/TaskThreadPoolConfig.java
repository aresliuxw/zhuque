package com.cn.liu.threadPool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程配置属性类
 *
 * @Author lxw
 * @Date 2022-04-05 17:20
 **/
@Data
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveTime;
    private int queueCapacity;
    private int awaitTerminationSeconds;
    private String threadNamePrefix;
}

