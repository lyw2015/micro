package com.laiyw.micro.portal.service.service;

import com.laiyw.micro.portal.service.domain.UserInfo;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:18
 * @Description TODO
 */

public interface IUserService {

    List<UserInfo> listUsers();

    UserInfo getUserById(Long id);
}
