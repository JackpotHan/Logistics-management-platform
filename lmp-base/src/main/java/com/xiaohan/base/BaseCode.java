package com.xiaohan.base;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 15:33
 * @Description: 返回状态码枚举
 */
public class BaseCode extends BaseObject {

    public static final BaseCode OK = new BaseCode("00", "操作成功");
    public static final BaseCode FAIL = new BaseCode("09", "失败");
    public static final BaseCode ERR = new BaseCode("10", "系统异常");
    public static final BaseCode ERR_PARAM = new BaseCode("11", "参数异常");
    public static final BaseCode DATA_ERR = new BaseCode("12","查询数据有异");
    public static final BaseCode TIME_OUT = new BaseCode("99", "系统请求超时");
    private String code;
    private String desc;

    public BaseCode() {}
    public BaseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
