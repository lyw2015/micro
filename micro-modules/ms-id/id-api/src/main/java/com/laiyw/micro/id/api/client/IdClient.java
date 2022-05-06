package com.laiyw.micro.id.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/6 16:36
 * @Description TODO
 */
@FeignClient(value = "id-generator", path = "/generator")
public interface IdClient {

    /**
     * CachedUidGenerator
     *
     * @return
     */
    @GetMapping("/cache")
    Long cacheId();

    /**
     * DefaultUidGenerator
     *
     * @return
     */
    @GetMapping("/default")
    Long defaultId();
}
