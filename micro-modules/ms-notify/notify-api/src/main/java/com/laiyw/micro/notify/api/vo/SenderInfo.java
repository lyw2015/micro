package com.laiyw.micro.notify.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 16:33
 * @Description TODO
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SenderInfo implements Serializable {

    private Long userId;

    private Long groupId;

    private String roleKey;

    private String templateCode;

    private Object content;

    public static SenderInfo create() {
        return new SenderInfo();
    }
}
