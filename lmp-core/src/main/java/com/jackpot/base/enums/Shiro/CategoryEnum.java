package com.jackpot.base.enums.Shiro;

/**
 * @author Chen Lingang
 * @version $Id: CategoryEnum, v 0.1 17/4/13 下午10:59
 */
public enum CategoryEnum {
    SYSTEM_MANAGEMENT("系统管理", "icon-set"),
    PARTNER_MANAGEMENT("用户管理", "icon-client"),
    TRANSACTION_MODE("交易管理","icon-transaction"),
    STATISTICAL_MODE("数据统计","icon-log"),
    BUSSINESS_CONFIGURATION("业务配置","icon-service"),
    CHANNEL_MANAGEMENT("渠道管理","icon-viewgallery"),
    OPERATION_MANAGEMENT("运营管理","icon-partner"),
    COLLECTION_MANAGEMENT("催收管理","icon-collection"),
    FINANCE_MANAGEMENT("财务管理","icon-finance");

    // 成员变量
    private String value;

    private String icon;

    /**
     * 默认构造方法
     *
     * @param value 枚举值
     */
    CategoryEnum(String value, String icon) {
        this.value = value;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public String getIcon() {
        return icon;
    }
}
