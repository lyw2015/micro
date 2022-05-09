package com.laiyw.micro.mybatis.strategy;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:38
 * @Description TODO
 */

public class RandomSlaveChangeStrategy implements SlaveChangeStrategy {

    @Override
    public int select(int slaveNum) {
        return ThreadLocalRandom.current().nextInt(1, slaveNum + 1);
    }
}
