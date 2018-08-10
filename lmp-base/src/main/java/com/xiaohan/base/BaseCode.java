package com.xiaohan.base;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 15:33
 * @Description: 返回状态码枚举
 */
public class BaseCode extends BaseObject {

    public static final BaseCode OK = new BaseCode(00, "操作成功");
    public static final BaseCode FAIL = new BaseCode(10, "失败");
    public static final BaseCode ERR_PARAM = new BaseCode(11, "参数异常");
    public static final BaseCode DATA_ERR = new BaseCode(12,"查询数据有异");
    public static final BaseCode ERR_DB = new BaseCode(13, "据库异常");
    public static final BaseCode TOKEN_EXPIRED = new BaseCode(14, "系统token失效");
    public static final BaseCode ERR_SIGN = new BaseCode(15, "系统验签异常");

    public static final BaseCode ERR = new BaseCode(55, "系统异常");
    public static final BaseCode TIME_OUT = new BaseCode(99, "系统请求超时");
    public static final BaseCode UNAUTHORIZED = new BaseCode(111, "未经授权");
    public static final BaseCode UNAUTHENTICATED = new BaseCode(112, "未经身份验证");
    public static final BaseCode CODE_ERROR = new BaseCode(113, "验证码错误");
    public static final BaseCode ASSERT_ERROR = new BaseCode(114,"业务断言错误");
    public static final BaseCode UNPERMISSION = new BaseCode(115, "系统未发现权限集合");
    public static final BaseCode ACCOUNT_EXIST = new BaseCode(116, "账号已经存在");


    private Integer code;
    private String desc;

    public BaseCode() {}
    public BaseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean codeEquals(String respCode) {
        return this.code.equals(respCode);
    }
}
