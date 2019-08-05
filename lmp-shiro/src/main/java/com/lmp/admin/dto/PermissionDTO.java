package com.lmp.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackpot.base.base.BaseObject;

import java.util.Date;

public class PermissionDTO extends BaseObject{
    private Long id;

    /**
     * 资源编码
     */
    private String perCode;

    /**
     * 资源路径
     */
    private String perUrl;

    /**
     * 资源名称
     */
    private String perName;

    /**
     * 父资源id
     */
    private Long parentId;

    /**
     * 所属类别
     */
    private String perCategory;

    /**
     * 图标
     */
    private String icon;

    /**
     * 资源类型：0:目录 1:菜单 2.按钮
     */
    private String perType;

    /**
     * 状态 0禁用 1启用
     */
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public String getPerCategory() {
        return perCategory;
    }

    public void setPerCategory(String perCategory) {
        this.perCategory = perCategory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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