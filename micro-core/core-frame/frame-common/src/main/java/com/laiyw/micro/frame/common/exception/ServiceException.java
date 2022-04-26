package com.laiyw.micro.frame.common.exception;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 12:05
 * @Description TODO
 */

public class ServiceException extends RuntimeException {

    private int code = 500;
    private String msg;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
