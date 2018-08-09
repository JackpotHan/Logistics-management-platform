package com.xiaohan.web.from;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Chen Lingang
 * @version $Id: UserRoleForm, v 0.1 17/4/1 上午11:40
 */
public interface UserRoleForm {

    /**
     * Reason: 角色查询.
     */
    class Query implements Serializable {

        private static final long serialVersionUID = -1983474793603758212L;

        @NotNull(message = "用户ID不能为空")
        private Integer userId;

        private Integer roleStatus = NumberUtils.INTEGER_ZERO;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getRoleStatus() {
            return roleStatus;
        }

        public void setRoleStatus(Integer roleStatus) {
            this.roleStatus = roleStatus;
        }
    }

}
