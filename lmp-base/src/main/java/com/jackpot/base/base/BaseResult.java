package com.jackpot.base.base;

import java.io.Serializable;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 14:57
 * @Description:
 */
public class BaseResult<T> implements Serializable {

    private String respCode;//response code, use EmmetCode
    private String respDesc;//response desc
    private Object respData;//response java object

    public BaseResult() {}

    public BaseResult(BaseCode baseCode) {
        this(baseCode, null);
    }

    public BaseResult(Object respData) {
        this(BaseCode.OK, respData);
    }

    public BaseResult(BaseCode BaseCode, Object respData) {
        this(BaseCode.getCode(), BaseCode.getCode(), respData);
    }

    public BaseResult(String respCode, String respDesc) {
        this(respCode, respDesc, null);
    }

    public BaseResult(String respCode, String respDesc, Object respData) {
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.respData = respData;
    }

    public boolean isSuccess() {
        return BaseCode.OK.getCode().equals(this.getRespCode());
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public Object getRespData() {
        return respData;
    }

    public void setRespData(Object respData) {
        this.respData = respData;
    }
}


