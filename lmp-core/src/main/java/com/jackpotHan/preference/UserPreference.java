package com.jackpotHan.preference;

import com.google.common.collect.Lists;
import com.jackpotHan.enums.Shiro.StatusEnum;
import com.jackpotHan.enums.Shiro.TypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Reason: 用户首选项.
 *
 */
public class UserPreference {

    /**
     * Reason: 用户状态首选项.
     */
    public class UserStatus implements Serializable {

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

        public UserStatus() {
        }

        public UserStatus(String code, Integer value, String name) {
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
         * 获取用户状态首选项.
         *
         * @return
         */
        public List<UserStatus> preferences() {

            StatusEnum[] values = StatusEnum.values();

            List<UserStatus> userStatuses = Lists.newArrayList();
            UserStatus userStatus;
            for (StatusEnum enu : values) {
                userStatus = new UserStatus(enu.name(), enu.getCode(), enu.getMsg());
                userStatuses.add(userStatus);
            }
            return userStatuses;
        }
    }

    /**
     * Reason: 用户类型首选项.
     */
    public class UserType implements Serializable {

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

        public UserType() {
        }

        public UserType(String code, Integer value, String name) {
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
         * 获取用户类型首选项.
         *
         * @return
         */
        public List<UserType> preferences() {

            TypeEnum[] values = TypeEnum.values();

            List<UserType> userTypes = Lists.newArrayList();
            UserType userType;
            for (TypeEnum enu : values) {
                userType = new UserType(enu.name(), enu.getCode(), enu.getMsg());
                userTypes.add(userType);
            }
            return userTypes;
        }
    }

}
