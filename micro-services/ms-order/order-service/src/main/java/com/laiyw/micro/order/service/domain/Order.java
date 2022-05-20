package com.laiyw.micro.order.service.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laiyw.micro.mybatis.domain.BaseModel;
import lombok.*;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 10:25
 * @Description TODO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("micro_order")
public class Order extends BaseModel<Order> {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderId;

    private Long userId;

    private Long commodityId;

    private Long number;

    private Long money;
}
