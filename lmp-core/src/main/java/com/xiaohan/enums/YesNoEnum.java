package com.xiaohan.enums;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:41
 * @Description:
 */

public enum YesNoEnum {
    YES(1, "是"),
    NO(2, "否"),;
    private Integer code;
    private String msg;

    YesNoEnum(Integer code, String msg) {
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
