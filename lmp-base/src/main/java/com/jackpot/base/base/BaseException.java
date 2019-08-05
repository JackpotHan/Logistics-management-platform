package com.jackpot.base.base;


public class BaseException extends RuntimeException {
    private String code;

    public BaseException(BaseCode baseCode) {
        this(baseCode.getCode(), baseCode.getMsg());
    }

    public BaseException(BaseResult baseResult) {
        this(baseResult.getRespCode(), baseResult.getRespDesc());
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
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

    public BaseException(String message) {
        super(message);
        this.code = BaseCode.RESPCODE_FAIL.getCode();
    }

}
