package com.laiyw.micro.mybatis.strategy;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:38
 * @Description TODO
 */

public class RoundRobinSlaveChangeStrategy implements SlaveChangeStrategy {

    private final AtomicLong counter = new AtomicLong(0);
    private static final Long MAX_POOL = Long.MAX_VALUE;
    private final Lock lock = new ReentrantLock();

    @Override
    public int select(int slaveNum) {
        //轮询方式
        long currValue = counter.incrementAndGet();
        if ((currValue + 1) >= MAX_POOL) {
            lock.lock();
            try {
                if ((currValue + 1) >= MAX_POOL) {
                    counter.set(0);
                }
            } finally {
                lock.unlock();
            }
        }
        return (int) (currValue % slaveNum) + 1;
    }
}
