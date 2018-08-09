package com.xiaohan.web.from;

import com.xiaohan.Regex.Regex;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Chen Lingang
 * @version $Id: UserForm, v 0.1 17/4/1 上午11:40
 */
public interface UserForm {

    /**
     * Reason: 用户登录.
     */
    class Login implements Serializable {

        private static final long serialVersionUID = -4370774088960718643L;

        @NotNull(message = "登录名不能为空")
        private String userName;

        @NotNull(message = "密码不能为空")
        @Pattern(regexp = Regex.REGEX_PASSWORD, message = "密码必须6-16位")
        private String password;
        
        private String checkCode;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCheckCode() {
            return checkCode;
        }
        
        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Login, {");
            sb.append("userName=");
            sb.append(userName);
            sb.append(", password=");
            sb.append(password);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 新增用户信息更新.
     */
    class Insert extends BaseForm {

        private static final long serialVersionUID = 2867026373692278847L;

        /**
         * 登录名
         */
        @NotBlank(message = "登录名不能为空")
        @Length(min = 2, max = 32, message = "登录名长度限制在2-32个字符内")
        private String accountName;
        
        @NotNull(message = "密码不能为空")
        @Pattern(regexp = Regex.REGEX_PASSWORD, message = "密码必须6-16位")
        private String password;

        /**
         * 昵称
         */
        @NotBlank(message = "昵称不能为空")
        @Length(min = 2, max = 32, message = "昵称长度限制在2-32个字符内")
        private String nickName;

        /**
         * 真实姓名
         */
        @NotBlank(message = "真实姓名不能为空")
        @Length(min = 2, max = 32, message = "真实姓名长度限制在2-32个字符内")
        private String realName;

        /**
         * 用户类型
         */
        @NotNull(message = "用户类型不能为空")
        private Integer type;

        /**
         * 用户状态
         */
        @NotNull(message = "用户状态不能为空")
        private Integer status;

        /**
         * 用户状态说明
         */
        @Length(max = 255, message = "用户状态说明长度限制在255个字符内")
        private String statusExplain;

        /**
         * 组织机构
         */
        @NotBlank(message = " 组织机构不能为空")
        @Length(max = 32, message = "组织机构长度限制在32个字符内")
        private String organization;

        /**
         * 描述
         */
        private String description;

        /**
         * 邮箱地址
         */
        private String email;

        /**
         * 即使通讯工具
         */
        private String qq;

        /**
         * 固定电话
         */
        private String telephone;

        /**
         * 移动电话
         */
        private String mobile;

        /**
         * 座席号
         */
        private String agentNo;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusExplain() {
            return statusExplain;
        }

        public void setStatusExplain(String statusExplain) {
            this.statusExplain = statusExplain;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAgentNo() {
            return agentNo;
        }

        public void setAgentNo(String agentNo) {
            this.agentNo = agentNo;
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
         * 昵称
         */
        @NotBlank(message = "昵称不能为空")
        @Length(min = 2, max = 32, message = "登录名长度限制在2-32个字符内")
        private String nickName;

        /**
         * 真实姓名
         */
        @NotBlank(message = "真实姓名不能为空")
        @Length(min = 2, max = 32, message = "登录名长度限制在2-32个字符内")
        private String realName;

        /**
         * 用户类型
         */
        @NotNull(message = "用户类型不能为空")
        private Integer type;

        /**
         * 用户状态
         */
        @NotNull(message = "用户状态不能为空")
        private Integer status;

        /**
         * 用户状态说明
         */
        @Length(max = 255, message = "用户状态说明长度限制在255个字符内")
        private String statusExplain;

        /**
         * 组织机构
         */
        @NotBlank(message = " 组织机构不能为空")
        @Length(max = 32, message = "组织机构长度限制在32个字符内")
        private String organization;

        /**
         * 描述
         */
        private String description;

        /**
         * 邮箱地址
         */
        private String email;

        /**
         * 即使通讯工具
         */
        private String qq;

        /**
         * 固定电话
         */
        private String telephone;

        /**
         * 移动电话
         */
        private String mobile;

        /**
         * 座席号
         */
        private String agentNo;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusExplain() {
            return statusExplain;
        }

        public void setStatusExplain(String statusExplain) {
            this.statusExplain = statusExplain;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAgentNo() {
            return agentNo;
        }

        public void setAgentNo(String agentNo) {
            this.agentNo = agentNo;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Update, {");
            sb.append("id=");
            sb.append(id);
            sb.append(", nickName=");
            sb.append(nickName);
            sb.append(", realName=");
            sb.append(realName);
            sb.append(", type=");
            sb.append(type);
            sb.append(", status=");
            sb.append(status);
            sb.append(", statusExplain=");
            sb.append(statusExplain);
            sb.append(", organization=");
            sb.append(organization);
            sb.append(", description=");
            sb.append(description);
            sb.append(", email=");
            sb.append(email);
            sb.append(", qq=");
            sb.append(qq);
            sb.append(", telephone=");
            sb.append(telephone);
            sb.append(", mobile=");
            sb.append(mobile);
            sb.append(", agentNo=");
            sb.append(agentNo);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 修改密码.
     */
    class ChangePassword extends BaseForm {

        private static final long serialVersionUID = 6550335504036178748L;

        @NotNull(message = "新密码不能为空")
        @Pattern(regexp = Regex.REGEX_PASSWORD, message = "密码格式必须2-16个字母")
        private String password;

        @NotNull(message = "原始密码不能为空")
        private String oldPassword;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("ChangePassword, {");
            sb.append("password=");
            sb.append(password);
            sb.append(", oldPassword=");
            sb.append(oldPassword);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 重置用户密码.
     */
    class ResetPassword extends BaseForm {

        private static final long serialVersionUID = 6550335504036178748L;

        @NotNull(message = "重置密码用户ID不能为空")
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("resetPassword, {");
            sb.append("id=");
            sb.append(id);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Reason: 验证是否已被注册.
     */
    class CheckValidity implements Serializable {

        private static final long serialVersionUID = 6550335504036178748L;

        @NotNull(message = "登录名不能为空")
        private String accountName;

        private String oldAccountName;

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getOldAccountName() {
            return oldAccountName;
        }

        public void setOldAccountName(String oldAccountName) {
            this.oldAccountName = oldAccountName;
        }

        public boolean preValidity() {
            if (StringUtils.isNotBlank(accountName)) {
                return accountName.equals(oldAccountName) ? true : false;
            }
            return false;
        }
    }
}
