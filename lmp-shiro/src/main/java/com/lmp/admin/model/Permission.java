package com.lmp.admin.model;

import com.jackpot.base.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_admin_permission")
public class Permission extends BaseObject {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 资源编码
     */
    @Column(name = "per_code")
    private String perCode;

    /**
     * 资源路径
     */
    @Column(name = "per_url")
    private String perUrl;

    /**
     * 资源名称
     */
    @Column(name = "per_name")
    private String perName;

    /**
     * 父资源id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 资源类型：0:目录 1:菜单 2.按钮
     */
    @Column(name = "per_type")
    private String perType;

    @Column(name = "per_category")
    private String perCateGory;

    @Column(name = "icon")
    private String iconf;

    public String getPerCateGory() {
        return perCateGory;
    }

    public void setPerCateGory(String perCateGory) {
        this.perCateGory = perCateGory;
    }

    public String getIconf() {
        return iconf;
    }

    public void setIconf(String iconf) {
        this.iconf = iconf;
    }

    /**
     * 状态 0禁用 1启用
     */
    @Column(name = "status")
    private String status;

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
     * 获取资源路径
     *
     * @return per_url - 资源路径
     */
    public String getPerUrl() {
        return perUrl;
    }

    /**
     * 设置资源路径
     *
     * @param perUrl 资源路径
     */
    public void setPerUrl(String perUrl) {
        this.perUrl = perUrl;
    }

    /**
     * 获取资源名称
     *
     * @return per_name - 资源名称
     */
    public String getPerName() {
        return perName;
    }

    /**
     * 设置资源名称
     *
     * @param perName 资源名称
     */
    public void setPerName(String perName) {
        this.perName = perName;
    }

    /**
     * 获取父资源id
     *
     * @return parent_id - 父资源id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父资源id
     *
     * @param parentId 父资源id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源类型：0:目录 1:菜单 2.按钮
     *
     * @return per_type - 资源类型：0:目录 1:菜单 2.按钮
     */
    public String getPerType() {
        return perType;
    }

    /**
     * 设置资源类型：0:目录 1:菜单 2.按钮
     *
     * @param perType 资源类型：0:目录 1:菜单 2.按钮
     */
    public void setPerType(String perType) {
        this.perType = perType;
    }

    /**
     * 获取状态 0禁用 1启用
     *
     * @return status - 状态 0禁用 1启用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 0禁用 1启用
     *
     * @param status 状态 0禁用 1启用
     */
    public void setStatus(String status) {
        this.status = status;
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