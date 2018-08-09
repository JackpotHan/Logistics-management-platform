package com.xiaohan.web.from;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen Lingang
 * @version $Id: RolePermissionForm, v 0.1 17/4/1 上午11:40
 */
public interface RolePermissionForm {

    /**
     * Reason: 权限查询.
     */
    class Query implements Serializable {

        private static final long serialVersionUID = -1983474793603758212L;

        private Integer roleId;

        private List<Integer> roleIds = Lists.newArrayList();

        private Integer permissionStatus = NumberUtils.INTEGER_ZERO;

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Integer getPermissionStatus() {
            return permissionStatus;
        }

        public void setPermissionStatus(Integer permissionStatus) {
            this.permissionStatus = permissionStatus;
        }

        public List<Integer> getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(List<Integer> roleIds) {
            this.roleIds = roleIds;
        }
    }

}
