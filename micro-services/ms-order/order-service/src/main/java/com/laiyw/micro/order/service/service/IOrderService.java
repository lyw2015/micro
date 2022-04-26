package com.laiyw.micro.order.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laiyw.micro.order.service.domain.Order;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/25 14:44
 * @Description TODO
 */

public interface IOrderService extends IService<Order> {

    Order saveOrder(Order order);

    Order getOrderInfoById(Long id);

    List<Order> listOrder();


}
