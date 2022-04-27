package com.laiyw.micro.order.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laiyw.micro.order.service.domain.Order;
import com.laiyw.micro.order.service.mapper.OrderMapper;
import com.laiyw.micro.order.service.service.IOrderService;
import com.laiyw.micro.portal.api.client.UserClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/25 14:44
 * @Description TODO
 */

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderMapper orderMapper;

    @GlobalTransactional
    @Override
    public Order saveOrder(Order order) {
        log.info("全局事务ID: {}", RootContext.getXID());
        order = Order.builder()
                .name(RandomStringUtils.randomAlphanumeric(6))
                .number(RandomUtils.nextLong(1, 99999))
                .description(RandomStringUtils.randomAlphanumeric(50))
                .build();
        orderMapper.insert(order);
        userClient.deduction(1L, RandomUtils.nextLong(0, 100));
        return order;
    }

    @Override
    public Order getOrderInfoById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> listOrder() {
        return orderMapper.selectList(new QueryWrapper<>());
    }
}
