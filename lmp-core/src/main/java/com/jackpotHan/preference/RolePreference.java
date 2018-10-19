package com.jackpotHan.preference;

import com.google.common.collect.Lists;
import com.jackpotHan.enums.Shiro.StatusEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Reason: 角色首选项.
 *
 */
public class RolePreference {

    /**
     * Reason: 角色状态首选项.
     */
    public class RoleStatus implements Serializable {

        /**
         * 编码.
         */
        private String code;

        /**
         * 值.
         */
        private Integer value;

        /**
         * 名称.
         */
        private String name;

        public RoleStatus() {
        }

        public RoleStatus(String code, Integer value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        /**
         * 获取角色状态首选项.
         *
         * @return
         */
        public List<RoleStatus> preferences() {

            StatusEnum[] values = StatusEnum.values();

            List<RoleStatus> roleStatuses = Lists.newArrayList();
            RoleStatus roleStatus;
            for (StatusEnum enu : values) {
                roleStatus = new RoleStatus(enu.name(), enu.getCode(), enu.getMsg());
                roleStatuses.add(roleStatus);
            }
            return roleStatuses;
        }
    }

}
