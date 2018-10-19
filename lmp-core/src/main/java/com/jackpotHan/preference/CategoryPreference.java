package com.jackpotHan.preference;

import com.google.common.collect.Lists;
import com.jackpotHan.enums.Shiro.CategoryEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Reason: 分类首选项.
 *
 */
public class CategoryPreference {

    /**
     * Reason: 分类状态首选项.
     */
    public class Category implements Serializable {

        /**
         * 编码.
         */
        private String code;

        /**
         * 值.
         */
        private String value;

        /**
         * 名称.
         */
        private String name;

        public Category() {
        }

        public Category(String code, String value, String name) {
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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 获取分类状态首选项.
         *
         * @return
         */
        public List<Category> preferences() {

            CategoryEnum[] values = CategoryEnum.values();

            List<Category> categories = Lists.newArrayList();
            Category category;
            for (CategoryEnum enu : values) {
                category = new Category(enu.name(), enu.getValue(), enu.getIcon());
                categories.add(category);
            }
            return categories;
        }
    }

}
