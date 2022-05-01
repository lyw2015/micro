package com.laiyw.micro.order.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laiyw.micro.order.service.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 15:06
 * @Description TODO
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
