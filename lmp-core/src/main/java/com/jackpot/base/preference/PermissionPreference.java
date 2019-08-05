package com.jackpot.base.preference;

import com.google.common.collect.Lists;
import com.jackpot.base.enums.Shiro.LevelEnum;
import com.jackpot.base.enums.Shiro.StatusEnum;
import com.jackpot.base.enums.Shiro.TypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Reason: 权限首选项.
 *
 */
public class PermissionPreference {

    /**
     * Reason: 权限状态首选项.
     */
    public class PermissionStatus implements Serializable {

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

        public PermissionStatus() {
        }

        public PermissionStatus(String code, Integer value, String name) {
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
         * 获取权限状态首选项.
         *
         * @return
         */
        public List<PermissionStatus> preferences() {

            StatusEnum[] values = StatusEnum.values();

            List<PermissionStatus> permissionStatuses = Lists.newArrayList();
            PermissionStatus permissionStatus;
            for (StatusEnum enu : values) {
                permissionStatus = new PermissionStatus(enu.name(), enu.getCode(), enu.getMsg());
                permissionStatuses.add(permissionStatus);
            }
            return permissionStatuses;
        }
    }

    /**
     * Reason: 权限类型首选项.
     */
    public class PermissionType implements Serializable {

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

        public PermissionType() {
        }

        public PermissionType(String code, Integer value, String name) {
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
         * 获取权限类型首选项.
         *
         * @return
         */
        public List<PermissionType> preferences() {

            TypeEnum[] values = TypeEnum.values();

            List<PermissionType> permissionTypes = Lists.newArrayList();
            PermissionType permissionType;
            for (TypeEnum enu : values) {
                permissionType = new PermissionType(enu.name(), enu.getCode(), enu.getMsg());
                permissionTypes.add(permissionType);
            }
            return permissionTypes;
        }
    }

    /**
     * Reason: 权限等级首选项.
     */
    public class PermissionLevel implements Serializable {

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

        public PermissionLevel() {
        }

        public PermissionLevel(String code, Integer value, String name) {
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
         * 获取权限等级首选项.
         *
         * @return
         */
        public List<PermissionLevel> preferences() {

            LevelEnum[] values = LevelEnum.values();

            List<PermissionLevel> permissionLevels = Lists.newArrayList();
            PermissionLevel permissionLevel;
            for (LevelEnum enu : values) {
                permissionLevel = new PermissionLevel(enu.name(), enu.getCode(), enu.getMsg());
                permissionLevels.add(permissionLevel);
            }
            return permissionLevels;
        }
    }

}
