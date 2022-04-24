package com.laiyw.micro.portal.service.service.impl;

import com.laiyw.micro.portal.service.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:19
 * @Description TODO
 */

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public Object listUsers() {
        return Arrays.asList("Test1", "Test2");
    }
}
