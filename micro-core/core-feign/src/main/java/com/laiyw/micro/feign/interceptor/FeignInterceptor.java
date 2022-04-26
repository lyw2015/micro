package com.laiyw.micro.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/20 14:12
 * @Description TODO
 */

@Slf4j
@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

    }
}
