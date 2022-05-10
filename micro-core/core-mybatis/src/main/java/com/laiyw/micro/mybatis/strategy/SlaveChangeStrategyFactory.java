package com.laiyw.micro.mybatis.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:37
 * @Description TODO
 */

public class SlaveChangeStrategyFactory {

    private static final Map<String, SlaveChangeStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put("loop", new RoundRobinSlaveChangeStrategy());
        STRATEGY_MAP.put("random", new RandomSlaveChangeStrategy());
    }

    public static SlaveChangeStrategy getSlaveChangeStrategy(String strategy) {
        SlaveChangeStrategy slaveChangeStrategy = STRATEGY_MAP.get(strategy);
        if (null == slaveChangeStrategy) {
            throw new IllegalArgumentException("无效的数据库选取策略: " + strategy);
        }
        return slaveChangeStrategy;
    }
}
