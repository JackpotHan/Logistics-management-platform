package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class UserRoleAssignDTO implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 权限编码
     */
    private List<String> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
