package com.jackpotHan.web.from;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotNull;

/**
 * @author Chen Lingang
 * @version $Id: UserPermissionForm, v 0.1 17/4/1 上午11:40
 */
public interface UserPermissionForm {

    /**
     * Reason: 权限查询.
     */
    class Query extends BaseForm {

        private static final long serialVersionUID = -1983474793603758212L;

        @NotNull(message = "用户ID不能为空")
        private Integer userId;

        private Integer permissionStatus = NumberUtils.INTEGER_ZERO;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getPermissionStatus() {
            return permissionStatus;
        }

        public void setPermissionStatus(Integer permissionStatus) {
            this.permissionStatus = permissionStatus;
        }
    }

    /**
     * Reason: 权限查询.
     */
    class QueryPrinciple extends BaseForm {

        private static final long serialVersionUID = -1983474793603758212L;

        @NotNull(message = "用户ID不能为空")
        private Integer userId;

        private Integer permissionStatus = NumberUtils.INTEGER_ZERO;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getPermissionStatus() {
            return permissionStatus;
        }

        public void setPermissionStatus(Integer permissionStatus) {
            this.permissionStatus = permissionStatus;
        }
    }

}
