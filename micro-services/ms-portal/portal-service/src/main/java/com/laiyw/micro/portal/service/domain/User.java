package com.laiyw.micro.portal.service.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laiyw.micro.mybatis.domain.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/24 14:23
 * @Description TODO
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("micro_user")
public class User extends BaseModel<User> {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long money;
}
