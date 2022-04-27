package com.laiyw.micro.portal.api.fallback;

import com.laiyw.micro.portal.api.client.UserClient;
import com.laiyw.micro.portal.api.vo.UserVo;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/24 13:46
 * @Description TODO
 */

@Component
public class UserClientFallback implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(final Throwable cause) {
        return new UserClient() {
            @Override
            public List<UserVo> listUsers() {
                return Collections.emptyList();
            }

            @Override
            public UserVo getUserById(Long id) {
                return new UserVo();
            }

            @Override
            public boolean deduction(Long id, Long money) {
                return false;
            }
        };
    }
}
