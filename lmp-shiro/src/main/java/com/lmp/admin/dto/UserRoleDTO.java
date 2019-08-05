package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huangqi
 * @Package com.zm.ft.dto
 * @Description: UserRoleDTO
 * @date 2019-02-20 10:52
 */
@Data
public class UserRoleDTO implements Serializable {

    /**
     * 用户id
     */
    private Long roleId;

    /**
     * 角色编码
     */
    private String roleCode;


    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
