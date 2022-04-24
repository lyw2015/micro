package com.laiyw.micro.portal.service.service.impl;

import com.laiyw.micro.portal.service.domain.UserInfo;
import com.laiyw.micro.portal.service.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:19
 * @Description TODO
 */

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public List<UserInfo> listUsers() {
        return Collections.singletonList(UserInfo.builder().id(1L).name(RandomStringUtils.randomAlphanumeric(6)).build());
    }

    @Override
    public UserInfo getUserById(Long id) {
        return UserInfo.builder().id(id).name(RandomStringUtils.randomAlphanumeric(6)).build();
    }
}
