package com.jackpot.base.base;

/**
 * @Author: hanjt
 * @Date: 2019/6/24 10:56
 * @Description:
 */
public enum BaseEnum {

    DISABLED("0", "禁用"), ENABLED("1", "启用"),

    CATALOG("0", "目录"), MENU("1", "菜单"), BUTTON("2", "按钮"),

    ;


    private final String code;

    private final String msg;

    BaseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
