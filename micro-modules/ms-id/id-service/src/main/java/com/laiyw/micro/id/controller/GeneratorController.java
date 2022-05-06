package com.laiyw.micro.id.controller;

import com.laiyw.micro.id.api.client.IdClient;
import com.laiyw.micro.id.service.UidGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName uid-generator
 * @Author Laiyw
 * @CreateTime 2022/5/6 10:36
 * @Description TODO
 */

@RestController
@RequestMapping("/generator")
public class GeneratorController implements IdClient {

    @Resource(name = "cachedUidGenerator")
    private UidGenerator cacheGenerator;
    @Resource(name = "defaultUidGenerator")
    private UidGenerator defaultGenerator;

    @Override
    public Long cacheId() {
        return cacheGenerator.getUID();
    }

    @Override
    public Long defaultId() {
        return defaultGenerator.getUID();
    }
}
