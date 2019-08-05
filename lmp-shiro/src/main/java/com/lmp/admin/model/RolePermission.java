package com.lmp.admin.model;

import com.jackpot.base.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_admin_role_permission")
public class RolePermission extends BaseObject {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 权限编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 资源编码
     */
    @Column(name = "per_code")
    private String perCode;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取权限编码
     *
     * @return role_code - 权限编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置权限编码
     *
     * @param roleCode 权限编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取资源编码
     *
     * @return per_code - 资源编码
     */
    public String getPerCode() {
        return perCode;
    }

    /**
     * 设置资源编码
     *
     * @param perCode 资源编码
     */
    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}