package com.laiyw.micro.portal.service.controller;

import com.laiyw.micro.frame.common.controller.BaseController;
import com.laiyw.micro.frame.common.domain.AjaxResult;
import com.laiyw.micro.portal.api.client.UserClient;
import com.laiyw.micro.portal.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:17
 * @Description TODO
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController extends BaseController implements UserClient {

    @Value("${user.init-password}")
    private String initPassword;
    @Autowired
    private IUserService userService;

    @GetMapping("/getInitPassword")
    public String getInitPassword() {
        return initPassword;
    }

    @Override
    public AjaxResult listUsers() {
        return AjaxResult.success(userService.listUsers());
    }
}
