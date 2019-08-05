package com.jackpot.base.enums.Shiro;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:45
 * @Description:
 */
public enum TypeEnum {

//    用户类型
    EMPLOYEE(0, "普通员工"),
    DEPARTMENT(1, "部门管理员"),
    ADMINISTRATOR(2, "系统管理员"),
    CHANNEL(3,"对接渠道账号"),

//    权限类型
    GENERAL_CONTROL(0, "通用权限"),
    MENU_CONTROL(1, "菜单控制权限"),
    SYSTEM_CONTROL(2, "系统功能控制权限");

    private Integer code;
    private String msg;

    TypeEnum(Integer code, String msg) {
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
