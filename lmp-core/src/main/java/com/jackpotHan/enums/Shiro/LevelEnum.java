package com.jackpotHan.enums.Shiro;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:44
 * @Description:
 */
public enum LevelEnum {
    NORMAL(0, "普通"),
    IMPORTANT(1, "重要");
    private Integer code;
    private String msg;

    LevelEnum(Integer code, String msg) {
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
