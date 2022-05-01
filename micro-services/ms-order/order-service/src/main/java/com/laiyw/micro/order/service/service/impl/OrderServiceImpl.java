package com.laiyw.micro.order.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laiyw.micro.order.service.domain.Order;
import com.laiyw.micro.order.service.mapper.OrderMapper;
import com.laiyw.micro.order.service.service.IOrderService;
import com.laiyw.micro.portal.api.client.UserClient;
import com.laiyw.micro.stock.api.client.CommodityClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    private CommodityClient commodityClient;

    @GlobalTransactional
    @Override
    public Order buy() {
        log.info("全局事务ID: {}", RootContext.getXID());
        // 随机商品数量
        long number = RandomUtils.nextLong(0, 10);
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .commodityId(1L)
                .number(number)
                .money(number * 2).build();
        // 保存订单
        save(order);
        // 更新库存
        commodityClient.updateCommodityStock(order.getCommodityId(), number);
        // 扣款
        userClient.deduction(1L, order.getMoney());
        return order;
    }

}
