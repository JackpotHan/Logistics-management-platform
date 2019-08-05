package com.jackpot.base.redis;

/**
 * Redis key常量类
 * @description 所有缓存相关key，均从本类中获取，方便维护
 * @author xiaowei 2019年1月22日 下午2:00:24
 */
public class RedisKeyConstants {

    /**
     * App过审信息     +auditCode +appVersion
     */
    public static final String APP_AUDIT = "AUDIT";
    /**
     * 通用配置        +configKey
     */
    public static final String CONFIG_KEY = "CONFIG";
    public static final String REPAY_CONFIG_KEY = "REPAY_CONFIG_";

    /**
     * 根据用户号获取随机的商户号       +userNo
     */
    public static final String RANDOM_MCHT = "RANDOM_MCHT_";

    /**
     * 查询所有推荐一内容模块
     */
    public static final String ALL_MODULES = "ALL_MODULES";

    public static final String ALL_PRODUCTS = "ALL_PRODUCTS";

    /**陶票票**/

    public static final String TICKET_CITYS = "TICKET_CITYS";

    public static final String CINEMA_MAP_CITYS = "CINEMA_MAP_CITYS";

    public static final String CINEMA_RECEIVED = "CINEMA_RECEIVED_";

    public static final String CINEMA_CACHE = "CINEMA_CACHE";

    public static final String CINEMA_CITY = "CINEMA_CITY_";

    public static final String CINEMA_SCHEDULES = "CINEMA_SCHEDULES_";

    public static final String CINEMA_COMMENT = "CINEMA_COMMENT_";

    /**京东**/
    public static final String JING_DONG_TOKEN = "JING_DONG_TOKEN";

    /**
     * 查询API +apiCode +apiVersion
     */
    public static final String COMMON_API = "COMMON_API";

    public static final String CODE = "CODE_";
    public static final String TOKEN = "TOKEN_";
    public static final String APPREPAY_MODULE = "APPREPAY_MODULE_";
    public static final String ORDER_INFO = "ORDERINFO_";
    public static final String NANJING_SECONDACCOUNT_SIGNCODE = "NANJING_SECONDACCOUNT_SIGNCODE";
    public static final String SKIP_TEMP = "SKIPTEMP";
    public static final String ORDER_SUBMIT = "ORDERSUBMIT";
    public static final String RISK_SERIALNO = "RISK_SERIALNO_";

    //有效期
    public static final long CODE_EXPIRE_TIME = 5 * 60L;
    public static final long TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60L;
    public static final long ORDER_INFO_TIME =  30 * 24 * 60 * 60L;
    public static final long ORDER_SUBMIT_TIME = 5L;
    public static final long RISK_SERIALNO_TIME = 5 * 60L;
    public static final long CINEMA_BIZ_TIME = 24 * 60 * 60L;
    public static final long CINEMA_CITY_TIME = 12 * 60 * 60L;
    public static final long CINEMA_SCHEDULES_TIME = 60 * 60L;

    public static final String WARN_PAY_ERROR_COUNT = "WARN_PAY_ERROR_COUNT";
    public static final String WARN_REPAY_ERROR_COUNT = "WARN_REPAY_ERROR_COUNT";

    /** 收款码重复提交校验  +userNo +productCode +transAmt */
    public static final String RECEIPT_QRCODE = "RECEIPT_QRCODE_";


    /** 产品信息 +productCode */
    public static final String PAYMENT_PRODUCT = "PAYMENT_PRODUCT_";
    /** 需要开通的产品信息 +productCode */
    public static final String NEED_OPEN_PAYMENT_PRODUCT = "NEED_OPEN_PAYMENT_PRODUCT";
    /** 查询APP展示的支付产品 */
    public static final String APP_PAYMENT_PRODUCT = "APP_PAYMENT_PRODUCT";
    /** 快捷支付重复提交校验 +cardNo */
    public static final String FAST_CARD_RECEIPT = "FAST_CARD_RECEIPT_";

    public static final String FINANCIAL_PAYMENT_CONFIG = "FINANCIAL_PAYMENT_CONFIG";
    public static final String FINANCIAL_MODE_CHANNEL = "FINANCIAL_MODE_CHANNEL";
    public static final String USER_DAILY_BALANCE_CONSUME = "USER_DAILY_BALANCE_CONSUME";
    
    /** websocket计数 */
    public static final String WEBSOCKET_COUNT = "WEBSOCKET_COUNT";

}
