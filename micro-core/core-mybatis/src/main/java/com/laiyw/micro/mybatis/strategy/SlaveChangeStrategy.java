package com.laiyw.micro.mybatis.strategy;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:37
 * @Description TODO
 */

public interface SlaveChangeStrategy {

    /**
     * 选取slave节点
     *
     * @param slaveCount slave节点总数
     * @return
     */
    int selectSlaveNode(int slaveCount);
}
