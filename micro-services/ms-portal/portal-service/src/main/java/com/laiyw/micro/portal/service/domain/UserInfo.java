package com.laiyw.micro.portal.service.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/24 14:23
 * @Description TODO
 */

@Data
@Builder
public class UserInfo {

    private Long id;
    private String name;
}
