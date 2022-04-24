package com.laiyw.micro.order.service.controller;

import com.laiyw.micro.frame.common.controller.BaseController;
import com.laiyw.micro.frame.common.domain.AjaxResult;
import com.laiyw.micro.order.api.client.OrderClient;
import com.laiyw.micro.portal.api.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @Override
    public AjaxResult getOrderInfo() {
        return AjaxResult.success(new Date());
    }

    @GetMapping("/getOrderUser")
    public AjaxResult getOrderUser() {
        return userClient.listUsers();
    }
}
