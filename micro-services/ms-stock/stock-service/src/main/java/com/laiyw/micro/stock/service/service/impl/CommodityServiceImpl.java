package com.laiyw.micro.stock.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laiyw.micro.common.exception.ServiceException;
import com.laiyw.micro.common.utils.SpringUtils;
import com.laiyw.micro.stock.service.domain.Commodity;
import com.laiyw.micro.stock.service.mapper.CommodityMapper;
import com.laiyw.micro.stock.service.service.ICommodityService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 10:26
 * @Description TODO
 */
@Slf4j
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private CommodityMapper commodityMapper;

    @CachePut(cacheNames = "commodity_detail", key = "#result.id")
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Commodity randomCommodity() {
        Commodity commodity = Commodity.builder()
                .name(RandomStringUtils.randomAlphanumeric(6))
                .number(RandomUtils.nextLong(1, 1000))
                .description(RandomStringUtils.randomAlphanumeric(50))
                .build();
        commodityMapper.insert(commodity);
        return commodity;
    }

    @Cacheable(cacheNames = "commodity_detail", key = "#id")
    @Override
    public Commodity getCommodityById(Long id) {
        return getById(id);
    }

    @Override
    public List<Commodity> listCommodity() {
        return commodityMapper.selectList(new QueryWrapper<>());
    }

    @CachePut(cacheNames = "commodity_detail", key = "#result.id")
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Commodity updateCommodity(Commodity commodity) {
        updateById(commodity);
        return commodity;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateCommodityStock(Long id, Long number) {
        log.info("全局事务ID: {}", RootContext.getXID());
        RLock rLock = redissonClient.getLock(String.format("commodity_%d", id));
        try {
            rLock.lock();
            // 处理同类内部方法调用缓存不生效问题
            Commodity commodity = SpringUtils.getBean(this.getClass()).getCommodityById(id);
            log.info("商品【{}】当前库存: {}", commodity.getName(), commodity.getNumber());
            log.info("扣减库存: {}", number);
            if (commodity.getNumber() < number) {
                throw new ServiceException("库存不足");
            }
            commodity.setNumber(commodity.getNumber() - number);
            SpringUtils.getBean(this.getClass()).updateCommodity(commodity);
            return true;
        } finally {
            rLock.unlock();
        }
    }

    @CacheEvict(cacheNames = {"commodity_detail"}, key = "#id", condition = "#result eq true")
    @Override
    public boolean removeCommodityById(Long id) {
        return removeById(id);
    }
}
