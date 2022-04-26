package com.laiyw.micro.portal.api.fallback;

import com.laiyw.micro.frame.common.domain.AjaxResult;
import com.laiyw.micro.portal.api.client.UserClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/24 13:46
 * @Description TODO
 */

@Component
public class UserClientFallback implements FallbackFactory<UserClient> {

    private AjaxResult error(final Throwable cause) {
        return AjaxResult.error(cause.getMessage());
    }

    @Override
    public UserClient create(final Throwable cause) {
        return new UserClient() {
            @Override
            public AjaxResult listUsers() {
                return error(cause);
            }

            @Override
            public AjaxResult getUserById(Long id) {
                return error(cause);
            }

            @Override
            public AjaxResult deduction(Long id, Long money) {
                return error(cause);
            }
        };
    }
}
