package com.laiyw.micro.portal.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laiyw.micro.common.exception.ServiceException;
import com.laiyw.micro.portal.service.domain.User;
import com.laiyw.micro.portal.service.mapper.UserMapper;
import com.laiyw.micro.portal.service.service.IUserService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/15 17:19
 * @Description TODO
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean deduction(Long id, Long money) {
        log.info("全局事务ID: {}", RootContext.getXID());
        User user = this.getById(id);
        if (user.getMoney() < money) {
            throw new ServiceException("余额不足");
        }
        user.setMoney(user.getMoney() - money);
        return updateById(user);
    }
}
