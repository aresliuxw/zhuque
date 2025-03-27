package com.cn.liu;

import com.cn.liu.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

//    @Test
//    void testConcurrentGeneration() throws InterruptedException {
//        int threadCount = 10;
//        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//        Set<String> orderNumbers = Collections.synchronizedSet(new HashSet<>());
//
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> {
//                String orderNo = orderService.generateOrderNo();
//                assertThat(orderNumbers.add(orderNo)).isTrue();
//            });
//        }
//
//        executor.shutdown();
//        assertThat(executor.awaitTermination(1, TimeUnit.MINUTES)).isTrue();
//        assertThat(orderNumbers).hasSize(threadCount);
//    }

}
