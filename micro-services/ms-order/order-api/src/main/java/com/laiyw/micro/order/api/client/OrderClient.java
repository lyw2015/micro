package com.laiyw.micro.order.api.client;

import com.laiyw.micro.frame.common.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/20 11:03
 * @Description TODO
 */
@FeignClient(value = "order", path = "/order")
public interface OrderClient {

    @GetMapping("saveOrder")
    AjaxResult saveOrder();

    @GetMapping("/getOrderInfoById")
    AjaxResult getOrderInfoById(Long id);
}
