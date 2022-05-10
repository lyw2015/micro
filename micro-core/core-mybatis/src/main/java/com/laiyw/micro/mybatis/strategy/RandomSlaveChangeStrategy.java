package com.laiyw.micro.mybatis.strategy;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:38
 * @Description 随机
 */

public class RandomSlaveChangeStrategy implements SlaveChangeStrategy {

    @Override
    public int selectSlaveNode(int slaveCount) {
        return ThreadLocalRandom.current().nextInt(1, slaveCount + 1);
    }
}
