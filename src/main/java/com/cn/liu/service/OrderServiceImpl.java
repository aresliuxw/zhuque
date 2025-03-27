package com.cn.liu.service;

import com.cn.liu.dao.OrderSequenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    @Resource
//    private OrderSequenceMapper orderSequenceMapper;

//    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public String generateOrderNo() {
//        String bizDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//
//        // 初始化当天序列记录（如果不存在）
//        orderSequenceMapper.initDateSequence(bizDate);
//
//        // 原子递增并获取序列值
//        orderSequenceMapper.incrementSequence(bizDate);
//        Long sequence = orderSequenceMapper.getCurrentSequence();
//
//        // 格式化订单号：RKDD + 日期 + 5位流水号
//        return String.format("RKDD%s%05d", bizDate, sequence);
//    }

    private final OrderSequenceMapper orderSequenceMapper;
    private final PlatformTransactionManager transactionManager;
    private final TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

    public String generateOrderNo() throws InterruptedException {
        String bizDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        return retryOnDeadlock(() -> {
            TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
            try {
                // 原子化操作：合并插入和更新
                orderSequenceMapper.upsertSequence(bizDate);
                Long sequence = orderSequenceMapper.getCurrentSequence();
                transactionManager.commit(status);
                return String.format("DD%s%05d", bizDate, sequence);
            } catch (Exception e) {
                transactionManager.rollback(status);
                throw e;
            }
        });
    }

    private <T> T retryOnDeadlock(Callable<T> task) throws InterruptedException {
        int retryCount = 0;
        while (retryCount < 3) {
            try {
                return task.call();
            } catch (DeadlockLoserDataAccessException e) {
                retryCount++;
                Thread.sleep(50L * retryCount); // 指数退避
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Deadlock retry failed");
    }

}
