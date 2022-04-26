package com.laiyw.micro.portal.api.client;

import com.laiyw.micro.frame.common.domain.AjaxResult;
import com.laiyw.micro.portal.api.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/20 10:51
 * @Description TODO
 */
@FeignClient(value = "portal", path = "/user", fallbackFactory = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/listUsers")
    AjaxResult listUsers();

    @GetMapping("/getUserById")
    AjaxResult getUserById(Long id);

    @GetMapping("/deduction")
    AjaxResult deduction(@RequestParam("id") Long id, @RequestParam("money") Long money);
}
