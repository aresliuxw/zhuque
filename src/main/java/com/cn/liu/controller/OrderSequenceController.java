package com.cn.liu.controller;

import com.cn.liu.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/OrderSequence")
public class OrderSequenceController {

    @Resource
    private OrderService orderService;

    @GetMapping("/getCode")
    public String getCode() throws Exception {
        String code = orderService.generateOrderNo();
        System.out.println("code==========:" + code);
        return code;
    }

    /**
     * 并发测试
     * @throws Exception
     */
    @GetMapping("/getCode2")
    public void getCode2() throws Exception {
        // 并发度
        int threadCount = 10000;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Set<String> orderNumbers = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                String orderNo = null;
                try {
                    orderNo = orderService.generateOrderNo();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                orderNumbers.add(orderNo);
                System.out.println("orderNo==========:" + orderNo);
            });
        }
    }


}
