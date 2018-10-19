package com.jackpotHan.web.from;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Chen Lingang
 * @version $Id: RoleForm, v 0.1 17/4/1 上午11:40
 */
public interface RoleForm {



    /**
     * Reason: 新增用户信息更新.
     */
    class Insert extends BaseForm {

        private static final long serialVersionUID = 2867026373692278847L;

        /**
         * 登录名
         */
        @NotBlank(message = "角色名称不能为空")
        @Length(min = 2, max = 32, message = "角色名称长度限制在2-32个字符内")
        private String name;

        /**
         * 昵称
         */
        @NotBlank(message = "角色代码不能为空")
        @Length(min = 2, max = 32, message = "角色代码长度限制在2-32个字符内")
        private String code;

        /**
         * 角色状态
         */
        @NotNull(message = "角色状态不能为空")
        private Integer status;

        /**
         * 描述
         */
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Insert, {");
            sb.append("name=");
            sb.append(name);
            sb.append(", code=");
            sb.append(code);
            sb.append(", status=");
            sb.append(status);
            sb.append(", description=");
            sb.append(description);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 更新用户信息更新.
     */
    class Update extends BaseForm {

        private static final long serialVersionUID = -4370774088960718643L;

        /**
         * 主键
         */
        @NotNull(message = "主键唯一约束错误!")
        private Integer id;

        /**
         * 登录名
         */
        @NotBlank(message = "角色名称不能为空")
        @Length(min = 2, max = 32, message = "角色名称长度限制在2-32个字符内")
        private String name;

        /**
         * 昵称
         */
        @NotBlank(message = "角色代码不能为空")
        @Length(min = 2, max = 32, message = "角色代码长度限制在2-32个字符内")
        private String code;

        /**
         * 角色状态
         */
        @NotNull(message = "角色状态不能为空")
        private Integer status;

        /**
         * 描述
         */
        private String description;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Update, {");
            sb.append("id=");
            sb.append(id);
            sb.append(", name=");
            sb.append(name);
            sb.append(", code=");
            sb.append(code);
            sb.append(", status=");
            sb.append(status);
            sb.append(", description=");
            sb.append(description);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 验证是否已存在.
     */
    class CheckValidity implements Serializable {

        private static final long serialVersionUID = 6550335504036178748L;

        @NotNull(message = "角色代码不能为空")
        private String code;

        private String oldCode;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOldCode() {
            return oldCode;
        }

        public void setOldCode(String oldCode) {
            this.oldCode = oldCode;
        }

        public boolean preValidity() {
            if (StringUtils.isNotBlank(code)) {
                return code.equals(oldCode) ? true : false;
            }
            return false;
        }
    }
}
