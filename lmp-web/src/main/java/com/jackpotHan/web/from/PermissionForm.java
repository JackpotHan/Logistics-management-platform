package com.jackpotHan.web.from;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Chen Lingang
 * @version $Id: PermissionForm, v 0.1 17/4/1 上午11:40
 */
public interface PermissionForm {

    /**
     * Reason: 权限检测.
     */
    class Query implements Serializable {

        private static final long serialVersionUID = -1983474793603758212L;

        @NotNull(message = "状态不能为空")
        private Integer status;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Query, {");
            sb.append("status=");
            sb.append(status);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 新增权限信息更新.
     */
    class Insert extends BaseForm {

        private static final long serialVersionUID = 2867026373692278847L;

        /**
         * 权限名
         */
        @NotBlank(message = "权限名称不能为空")
        @Length(min = 2, max = 64, message = "权限名称长度限制在2-64个字符内")
        private String name;

        /**
         * 权限值
         */
        @NotBlank(message = "权限值不能为空")
        @Length(min = 2, max = 64, message = "权限值长度限制在2-64个字符内")
        private String value;

        /**
         * 方法路径
         */
        @NotBlank(message = "方法路径不能为空")
        @Length(min = 2, max = 64, message = "方法路径长度限制在2-64个字符内")
        private String path;

        /**
         * 权限类型
         */
        @NotNull(message = "权限类型不能为空")
        private Integer type;

        /**
         * 权限等级
         */
        @NotNull(message = "权限等级不能为空")
        private Integer level;

        /**
         * 角色状态
         */
        @NotNull(message = "角色状态不能为空")
        private Integer status;

        /**
         * 所属大类
         */
        private String category;

        /**
         * 排序
         */
        @NotNull(message = "排序不能为空")
        private Integer seqNo;

        /**
         * 描述
         */
        private String description;

        /**
         * 父级权限  菜单控制权限时有效
         */
        private Integer parentId;

        /**
         * 菜单路径  菜单控制权限时有效
         */
        private String menuPath;

        /**
         * 菜单图标  菜单控制权限时有效
         */
        private String menuIcon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(Integer seqNo) {
            this.seqNo = seqNo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMenuPath() {
            return menuPath;
        }

        public void setMenuPath(String menuPath) {
            this.menuPath = menuPath;
        }

        public String getMenuIcon() {
            return menuIcon;
        }

        public void setMenuIcon(String menuIcon) {
            this.menuIcon = menuIcon;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }

    /**
     * Reason: 更新权限信息更新.
     */
    class Update extends BaseForm {

        private static final long serialVersionUID = -4370774088960718643L;

        /**
         * 主键
         */
        @NotNull(message = "主键唯一约束错误!")
        private Integer id;

        /**
         * 权限名
         */
        @NotBlank(message = "权限名称不能为空")
        @Length(min = 2, max = 64, message = "权限名称长度限制在2-64个字符内")
        private String name;

        /**
         * 权限值
         */
        @NotBlank(message = "权限值不能为空")
        @Length(min = 2, max = 64, message = "权限值长度限制在2-64个字符内")
        private String value;

        /**
         * 方法路径
         */
        @NotBlank(message = "方法路径不能为空")
        @Length(min = 2, max = 64, message = "方法路径长度限制在2-64个字符内")
        private String path;

        /**
         * 权限类型
         */
        @NotNull(message = "权限类型不能为空")
        private Integer type;

        /**
         * 权限等级
         */
        @NotNull(message = "权限等级不能为空")
        private Integer level;

        /**
         * 角色状态
         */
        @NotNull(message = "角色状态不能为空")
        private Integer status;

        /**
         * 所属大类
         */
        private String category;

        /**
         * 排序
         */
        @NotNull(message = "排序不能为空")
        private Integer seqNo;

        /**
         * 描述
         */
        private String description;

        /**
         * 父级权限  菜单控制权限时有效
         */
        private Integer parentId;

        /**
         * 菜单路径  菜单控制权限时有效
         */
        private String menuPath;

        /**
         * 菜单图标  菜单控制权限时有效
         */
        private String menuIcon;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(Integer seqNo) {
            this.seqNo = seqNo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMenuPath() {
            return menuPath;
        }

        public void setMenuPath(String menuPath) {
            this.menuPath = menuPath;
        }

        public String getMenuIcon() {
            return menuIcon;
        }

        public void setMenuIcon(String menuIcon) {
            this.menuIcon = menuIcon;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }

    /**
     * Reason: 验证是否已存在.
     */
    class CheckValidity implements Serializable {

        private static final long serialVersionUID = 6550335504036178748L;

        @NotNull(message = "权限值不能为空")
        private String value;

        private String oldValue;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOldValue() {
            return oldValue;
        }

        public void setOldValue(String oldValue) {
            this.oldValue = oldValue;
        }

        public boolean preValidity() {
            if (StringUtils.isNotBlank(value)) {
                return value.equals(oldValue) ? true : false;
            }
            return false;
        }
    }

}
