package com.jackpotHan.base;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 11:33
 * @Description:
 */
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(BaseCode baseCode) {
        this(baseCode.getCode(), baseCode.getDesc());
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(BaseResult result){
        super(result.getRespDesc());
        this.code = result.getRespCode();
    }

    public BaseException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
