package com.laiyw.micro.portal.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laiyw.micro.portal.service.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 11:41
 * @Description TODO
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
