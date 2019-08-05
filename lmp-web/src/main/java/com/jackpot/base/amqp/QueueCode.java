package com.jackpot.base.amqp;

public final class QueueCode {

    public static final String BALANCE_PAY_ERROR = "BALANCE.PAY.ERROR";
    public static final String BALANCE_REPAY_ERROR = "BALANCE.REPAY.ERROR";
    public static final String BALANCE_SUCCESS = "BALANCE.SUCCESS";
    public static final String CREDIT_FEE_ERROR = "CREDIT.FEE.ERROR";
    public static final String CREDIT_PAY_ERROR = "CREDIT.PAY.ERROR";
    public static final String CREDIT_REPAY_ERROR = "CREDIT.REPAY.ERROR";
    public static final String CREDIT_SUCCESS = "CREDIT.SUCCESS";
    public static final String MESSAGE_REMINDER_SUCC = "MESSAGE.REMINDER.SUCC";

    public static final String MESSAGE_MODIFY_CUSTOMER_STATUS = "MESSAGE.MODIFY.CUSTOMER.STATUS";

    public static final String SHARE_USER_AUTH = "SHARE.USER.AUTH";
    public static final String SHARE_USER_TRANS = "SHARE.USER.TRANS";

    public static final String ACCOUNT_SERIAL = "ACCOUNT.SERIAL";
    public static final String BALANCE_SYNC = "BALANCE.SYNC";

    public static final String PRODUCT_OPEN = "BB.PRODUCT.OPEN";

    public static final String MESSAGE_SEND_MAIL = "MESSAGE.SEND.MAIL";
    public static final String BB_SEND_SMS = "BB.SEND.SMS";
    public static final String BB_SEND_SMS_SYS = "BB.SEND.SMS.SYS";
    public static final String BB_SEND_JPUSH = "BB.SEND.JPUSH";
    public static final String BB_SEND_REMIND_MSG = "BB.SEND.REMIND.MSG";
    public static final String BB_MODIFY_CARDAUTH = "BB.MODIFY.CARDAUTH";
    public static final String BB_NOTIFY_REPAY_CUSTOMER = "BB.NOTIFY.REPAY.CUSTOMER";
    public static final String BB_REPAY_QUATO_CALCULATE = "BB.REPAY.QUATO.CALCULATE";
    public static final String BB_PURCHASE_GOODS = "BB.PURCHASE.GOODS";

    public static final String ADD_AUTH_PROCESS = "ADD.AUTH.PROCESS";

    public static final String EARN_OR_SPEND_SCORE = "EARN.OR.SPEND.SCORE";//用户赚取或花费积分

    public static final String FINANCIAL_RECHARGE_CREATE = "BB_FINANCIAL_RECHARGE_CREATE";//二类户充值流水创建

    public static final String FINANCIAL_BALANCE_CONSUME_CREATE = "BB_FINANCIAL_BALANCE_CONSUME_CREATE";//余额消费，创建流水

    public static final String MODIFY_USER_SECOND_ACCOUNT_SIGNNO = "BB_MODIFY_USER_SECOND_ACCOUNT_SIGNNO";//修改二类户签约

    public static final String ACCT_BALANCE_TRANS = "ACCT_BALANCE_TRANS";//账户余额交易

    public static final String OPEN_VIP = "BB.OPEN.VIP";//开通vip
    public static final String PASS_CLOSURE = "BB.PASS.CLOSURE";//修改优惠券状态

    public static final String BB_BUY_COUPON = "BB.BUY.COUPON";//购买兑换券
}
