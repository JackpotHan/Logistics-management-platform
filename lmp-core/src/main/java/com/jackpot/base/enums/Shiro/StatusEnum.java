package com.jackpot.base.enums.Shiro;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:45
 * @Description:
 */
public enum  StatusEnum {
    ENABLED(0, "正常"),
    DISABLE(1, "停用");
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
