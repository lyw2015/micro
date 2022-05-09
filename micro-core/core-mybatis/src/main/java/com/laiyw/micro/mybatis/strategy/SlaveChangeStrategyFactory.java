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
        SlaveChangeStrategy scStrategy = STRATEGY_MAP.get(strategy);
        if (null == scStrategy) {
            throw new RuntimeException("从数据库没有策略" + strategy);
        }
        return scStrategy;
    }
}
