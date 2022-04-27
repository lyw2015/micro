package com.laiyw.micro.portal.api.client;

import com.laiyw.micro.portal.api.fallback.UserClientFallback;
import com.laiyw.micro.portal.api.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/20 10:51
 * @Description TODO
 */
@FeignClient(value = "portal", path = "/user", fallbackFactory = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/listUsers")
    List<UserVo> listUsers();

    @GetMapping("/getUserById")
    UserVo getUserById(Long id);

    @GetMapping("/deduction")
    boolean deduction(@RequestParam("id") Long id, @RequestParam("money") Long money);
}
