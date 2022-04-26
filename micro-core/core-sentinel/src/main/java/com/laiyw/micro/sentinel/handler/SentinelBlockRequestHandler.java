package com.laiyw.micro.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 14:26
 * @Description TODO
 */
public class SentinelBlockRequestHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding(Charset.defaultCharset().name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        response.getWriter().write(JSON.toJSONString(new HashMap<String, Object>(2) {{
            put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
            put("msg", String.format("%s: %s", getMessage(e), e.getRule().getResource()));
        }}));
    }

    private String getMessage(BlockException e) {
        if (e instanceof FlowException) {
            return "您的访问过于频繁，请稍后重试";
        } else if (e instanceof DegradeException) {
            return "调用服务响应异常,已进行降级";
        } else if (e instanceof ParamFlowException) {
            return "您对热点参数访问过于频繁，请稍后重试";
        } else if (e instanceof SystemBlockException) {
            return "已触碰系统负载保护，请稍后重试";
        } else if (e instanceof AuthorityException) {
            return "授权规则检测不同，请检查访问参数";
        } else {
            return "非法访问，请稍后重试";
        }
    }
}
