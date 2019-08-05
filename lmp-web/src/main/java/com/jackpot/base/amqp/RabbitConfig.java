package com.jackpot.base.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 发送邮件mail
     **/
    @Bean
    public Queue sendMailQueue() {
        return new Queue(QueueCode.MESSAGE_SEND_MAIL);
    }

    /**
     * 余额代偿扣款失败
     */
    @Bean
    public Queue balancePayErrorQueue() {
        return new Queue(QueueCode.BALANCE_PAY_ERROR);
    }

    /**
     * 余额代偿放款失败
     */
    @Bean
    public Queue balanceRepayErrorQueue() {
        return new Queue(QueueCode.BALANCE_REPAY_ERROR);
    }

    /**
     * 余额代偿成功
     */
    @Bean
    public Queue balanceSuccessQueue() {
        return new Queue(QueueCode.BALANCE_SUCCESS);
    }

    /**
     * 信用代偿手续费扣取失败
     */
    @Bean
    public Queue creditFeeErrorQueue() {
        return new Queue(QueueCode.CREDIT_FEE_ERROR);
    }

    /**
     * 信用代偿放款失败
     */
    @Bean
    public Queue creditRepayErrorQueue() {
        return new Queue(QueueCode.CREDIT_REPAY_ERROR);
    }

    /**
     * 信用代偿收款失败
     */
    @Bean
    public Queue creditPayErrorQueue() {
        return new Queue(QueueCode.CREDIT_PAY_ERROR);
    }

    /**
     * 信用代偿成功
     */
    @Bean
    public Queue creditSuccessQueue() {
        return new Queue(QueueCode.CREDIT_SUCCESS);
    }

    /**
     * 催款成功推送
     **/
    @Bean
    public Queue reminderSucc() {
        return new Queue(QueueCode.MESSAGE_REMINDER_SUCC);
    }

    /**
     * 修改客户状态
     **/
    @Bean
    public Queue modifyCustomerStatus() {
        return new Queue(QueueCode.MESSAGE_MODIFY_CUSTOMER_STATUS);
    }

    /**
     * APP邀请账户认证获得奖励
     **/
    @Bean
    public Queue accountAuthInvite() {
        return new Queue(QueueCode.SHARE_USER_AUTH);
    }

    /**
     * APP详情单交易成功流程
     **/
    @Bean
    public Queue accountTransInvite() {
        return new Queue(QueueCode.SHARE_USER_TRANS);
    }

    /**
     * 订单审核失败发送短信通知
     **/
    @Bean
    public Queue accountSerial() {
        return new Queue(QueueCode.ACCOUNT_SERIAL);
    }

    /**
     * 余额还款同步执行结果
     **/
    @Bean
    public Queue balanceSync() {
        return new Queue(QueueCode.BALANCE_SYNC);
    }

    /**
     * 开通大码产品
     **/
    @Bean
    public Queue productOpen() {
        return new Queue(QueueCode.PRODUCT_OPEN);
    }

    /**
     * 添加或修改卡片的鉴权信息
     **/
    @Bean
    public Queue modifyCardAuth() {
        return new Queue(QueueCode.BB_MODIFY_CARDAUTH);
    }

    /**身份认证完成通知代还业务**/
    @Bean
    public Queue repayCustomerReveiver(){return new Queue(QueueCode.BB_NOTIFY_REPAY_CUSTOMER);}

//    /****/
//    @Bean
//    public Queue repayCustomerQuatoReveiver(){return new Queue(QueueCode.BB_REPAY_QUATO_CALCULATE);}

    /** 余额充值 **/
    @Bean
    public Queue financialRechargeCreate() {
        return new Queue(QueueCode.FINANCIAL_RECHARGE_CREATE);
    }

    /**
     * 余额消费
     **/
    @Bean
    public Queue financialBalanceConsumeCreate() {
        return new Queue(QueueCode.FINANCIAL_BALANCE_CONSUME_CREATE);
    }

    /**
     * 修改二类户签约
     **/
    @Bean
    public Queue modifyUserSecondAccoutSignNo() {
        return new Queue(QueueCode.MODIFY_USER_SECOND_ACCOUNT_SIGNNO);
    }

    /**
     * 用户赚取或花费积分
     * @return
     */
    @Bean
    public Queue earnOrSpendScore(){return new Queue(QueueCode.EARN_OR_SPEND_SCORE);}

    /**
     * 互金认证结果插入认证进程表
     * @return
     */
    @Bean
    public Queue addAuthProcess(){
        return new Queue(QueueCode.ADD_AUTH_PROCESS);
    }

    /**
     * 购买商品异步通知
     * @return
     */
    @Bean
    public Queue purchaseGoods(){ return new Queue(QueueCode.BB_PURCHASE_GOODS);}

    /**
     * 帮贝优惠券使用更改状态
     * @return
     */
    @Bean
    public Queue closurePassBb() {
        return new Queue(QueueCode.PASS_CLOSURE);
    }

    /**
     * 开通vip
     * @return
     */
    @Bean
    public Queue openVip(){
        return new Queue(QueueCode.OPEN_VIP);
    }

    /**
     * 发送短信
     * @return
     */
    @Bean
    public Queue sendMsg(){
        return new Queue(QueueCode.BB_SEND_SMS);
    }

    /**
     * 消息推送
     * @return
     */
    @Bean
    public Queue sendPush(){
        return new Queue(QueueCode.BB_SEND_JPUSH);
    }

    /**
     * 系统警告短信
     * @return
     */
    @Bean
    public Queue sendSystemMsg(){
        return new Queue(QueueCode.BB_SEND_SMS_SYS);
    }

    /**
     * 提醒短信
     * @return
     */
    @Bean
    public Queue sendRemindMsg(){
        return new Queue(QueueCode.BB_SEND_REMIND_MSG);
    }

    /**
     * 购买优惠券
     * @return
     */
    @Bean
    public Queue buyCoupon(){
        return new Queue(QueueCode.BB_BUY_COUPON);
    }
}
