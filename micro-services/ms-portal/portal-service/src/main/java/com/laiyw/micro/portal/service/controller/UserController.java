package com.laiyw.micro.portal.service.controller;

import com.laiyw.micro.frame.common.controller.BaseController;
import com.laiyw.micro.frame.common.utils.BeanUtils;
import com.laiyw.micro.portal.api.client.UserClient;
import com.laiyw.micro.portal.api.vo.UserVo;
import com.laiyw.micro.portal.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserVo> listUsers() {
        return userService.listUsers().stream()
                .map(user -> BeanUtils.createCopy(user, UserVo.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserVo getUserById(Long id) {
        return BeanUtils.createCopy(userService.getUserById(id), UserVo.class);
    }

    @Override
    public boolean deduction(Long id, Long money) {
        return userService.deduction(id, money);
    }

    @GetMapping("/getInitPassword")
    public String getInitPassword() {
        return initPassword;
    }
}
