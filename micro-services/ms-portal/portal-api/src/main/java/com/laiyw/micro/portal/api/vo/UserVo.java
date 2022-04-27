package com.laiyw.micro.portal.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/27 9:15
 * @Description TODO
 */

@Data
public class UserVo {

    private Long id;

    private String name;

    private Long money;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
