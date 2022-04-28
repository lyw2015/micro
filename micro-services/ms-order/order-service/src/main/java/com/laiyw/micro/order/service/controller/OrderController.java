package com.laiyw.micro.order.service.controller;

import com.laiyw.micro.common.controller.BaseController;
import com.laiyw.micro.common.domain.AjaxResult;
import com.laiyw.micro.common.utils.BeanUtils;
import com.laiyw.micro.order.api.client.OrderClient;
import com.laiyw.micro.order.api.vo.OrderVo;
import com.laiyw.micro.order.service.domain.Order;
import com.laiyw.micro.order.service.service.IOrderService;
import com.laiyw.micro.portal.api.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/19 14:56
 * @Description TODO
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController implements OrderClient {

    @Autowired
    private UserClient userClient;
    @Autowired
    private IOrderService orderService;

    @Override
    public OrderVo getOrderInfoById(Long id) {
        Order order = orderService.getOrderInfoById(id);
        return BeanUtils.createCopy(order, OrderVo.class);
    }

    @GetMapping("saveOrder")
    public AjaxResult saveOrder() {
        return AjaxResult.success(orderService.saveOrder(Order.builder().build()));
    }

    @GetMapping("/listOrder")
    public AjaxResult listOrder() {
        return AjaxResult.success(orderService.listOrder());
    }

    @GetMapping("/getOrderUser")
    public AjaxResult getOrderUser() {
        return AjaxResult.success(userClient.listUsers());
    }
}
