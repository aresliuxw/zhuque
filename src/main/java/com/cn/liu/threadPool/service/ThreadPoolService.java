package com.cn.liu.threadPool.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 自定义线程池测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:55
 **/
@Service
@Slf4j
public class ThreadPoolService {

    // 重写spring线程池
    @Async("customAsyncThreadPool") //指定使用哪个线程池配置，不然会使用spring默认的线程池
    public void executeAsync() {
        log.info("当前运行线程名称:{}", Thread.currentThread().getName());
    }




    // 自定义java线程池
    ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(
                    5,
                    10,
                    15,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(20),
                    new CustomThreadFactory("zhuque-threadpool"),
                    new ThreadPoolExecutor.AbortPolicy());

    public void executeTest() {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                log.info("当前运行线程名称:{}", Thread.currentThread().getName());
                try {
                    Thread.sleep(100);// 模拟耗时任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("当前核心线程数" + threadPoolExecutor.getCorePoolSize());
        System.out.println("当前线程池线程数" + threadPoolExecutor.getPoolSize());
        System.out.println("当前队列任务数" + threadPoolExecutor.getQueue().size());
        System.out.println("----------------------------------------------------");

    }

    /**
     * The custom thread factory
     * 自定义线程工厂 设置线程名称前缀
     */
    static class CustomThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            if (StringUtils.isEmpty(name))
                name = "pool";
            namePrefix = name + "-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}


