package com.laiyw.micro.notify.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 16:33
 * @Description TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenderInfo implements Serializable {

    private Long userId;

    private Long groupId;

    private String roleKey;

    private String templateCode;

    private Object content;
}
