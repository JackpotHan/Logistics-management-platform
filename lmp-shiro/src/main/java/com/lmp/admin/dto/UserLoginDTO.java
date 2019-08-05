package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String kaptcha;

   @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
