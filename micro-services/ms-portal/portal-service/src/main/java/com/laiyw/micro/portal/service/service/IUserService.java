package com.laiyw.micro.portal.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laiyw.micro.portal.service.domain.User;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:18
 * @Description TODO
 */

public interface IUserService extends IService<User> {

    List<User> listUsers();

    User getUserById(Long id);

    boolean deduction(Long id, Long money);
}
