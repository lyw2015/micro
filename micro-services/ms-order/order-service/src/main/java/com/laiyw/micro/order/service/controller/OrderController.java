package com.laiyw.micro.order.service.controller;

import com.laiyw.micro.frame.common.controller.BaseController;
import com.laiyw.micro.frame.common.domain.AjaxResult;
import com.laiyw.micro.order.api.client.OrderClient;
import com.laiyw.micro.order.service.domain.Order;
import com.laiyw.micro.order.service.service.IOrderService;
import com.laiyw.micro.portal.api.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/19 14:56
 * @Description TODO
 */
@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController implements OrderClient {

    @Autowired
    private UserClient userClient;
    @Autowired
    private IOrderService orderService;

    @GetMapping("saveOrder")
    public AjaxResult saveOrder() {
        return AjaxResult.success(orderService.saveOrder(Order.builder().build()));
    }

    @Override
    public AjaxResult getOrderInfoById(Long id) {
        return AjaxResult.success(orderService.getOrderInfoById(id));
    }

    @GetMapping("/listOrder")
    public AjaxResult listOrder() {
        return AjaxResult.success(orderService.listOrder());
    }

    @GetMapping("/getOrderUser")
    public AjaxResult getOrderUser() {
        return userClient.listUsers();
    }
}
