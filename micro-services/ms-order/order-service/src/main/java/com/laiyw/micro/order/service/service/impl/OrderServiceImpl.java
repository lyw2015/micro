package com.laiyw.micro.order.service.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laiyw.micro.id.api.client.IdClient;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/25 14:44
 * @Description TODO
 */

@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IdClient idClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private CommodityClient commodityClient;

    @GlobalTransactional
    @Override
    public Order buy() {
        log.info("全局事务ID: {}", RootContext.getXID());
        // 随机商品数量
        long number = RandomUtils.nextLong(1, 10);
        Order order = Order.builder()
                .orderId(Convert.toStr(idClient.cacheId()))
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
