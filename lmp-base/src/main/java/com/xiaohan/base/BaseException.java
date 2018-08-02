package com.xiaohan.base;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 11:33
 * @Description:
 */
public class BaseException extends RuntimeException {
    private String code;

    public BaseException(BaseCode baseCode) {
        this(baseCode.getCode(), baseCode.getDesc());
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(BaseResult result){
        super(result.getRespDesc());
        this.code = result.getRespCode();
    }

    public BaseException(String code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
