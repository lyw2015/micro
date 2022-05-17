package com.laiyw.micro.notify.api.client;

import com.laiyw.micro.notify.api.vo.SenderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 16:10
 * @Description TODO
 */
@FeignClient(value = "notify", path = "sender")
public interface NotifyClient {

    @PostMapping("/send")
    void send(@RequestBody SenderInfo senderInfo);

}
