package com.laiyw.micro.frame.common.exception;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 12:05
 * @Description TODO
 */

public class ServiceException extends RuntimeException {

    private int code = 500;
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
