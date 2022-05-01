package com.laiyw.micro.stock.service.domain;

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
 * @CreateTime 2022/4/25 14:46
 * @Description Commodity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("micro_commodity")
public class Commodity extends BaseModel<Commodity> {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long number;

    private String description;
}
