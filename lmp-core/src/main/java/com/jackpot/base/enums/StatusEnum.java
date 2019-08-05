package com.jackpot.base.enums;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:59
 * @Description:
 */
public enum StatusEnum {
    SUCCESS(1, "成功"),
    FAIL(2, "失败"),;
    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
