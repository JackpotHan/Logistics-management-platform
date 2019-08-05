package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class RolePermAssignDTO implements Serializable {

    /**
     * 用户id
     */
    private String roleCode;
    /**
     * 权限编码
     */
    private List<Long> perIds;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<Long> getPerIds() {
        return perIds;
    }

    public void setPerIds(List<Long> perIds) {
        this.perIds = perIds;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
