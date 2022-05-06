package com.laiyw.micro.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.laiyw.micro.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/6 14:05
 * @Description TODO
 */

@Slf4j
@RefreshScope
public class UidGenerator {

    @Value("${uid-generator.server}")
    private String server;
    @Value("${uid-generator.token}")
    private String token;

    public String getUID() {
        HttpRequest request = HttpUtil.createGet(server);
        request.addHeaders(MapUtil.of("token", token));
        HttpResponse response = request.execute();
        if (response.getStatus() == HttpStatus.HTTP_OK) {
            return response.body();
        }
        throw new ServiceException("获取分布式ID异常: " + response.body());
    }

    public Long getLongUID() {
        return Convert.toLong(getUID());
    }
}
