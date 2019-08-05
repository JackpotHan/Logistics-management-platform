package com.jackpot.base.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hjf
 * @date ：Created in 2019/4/9 14:11
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
@Slf4j
public class DelayRabbitConfig {

    public static final String BB_DELAY_EXCHANGE = "BB.DELAY.EXCHANGE";

    /**
     * 创建一个自定义交换机  类型为x-delayed-message
     * 此类型需要rabbitmq安装插件rabbitmq_delayed_message_exchange
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(BB_DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /** ====渠道通知==== */
    public static final String  BB_CHANNEL_NOTIFY_QUEUE     = "BB.CHANNEL.NOTIFY.QUEUE";

    @Bean
    public Queue channelNotifyQueue() {
        return new Queue(BB_CHANNEL_NOTIFY_QUEUE, true);
    }

    @Bean
    public Binding bindingChannelNotify() {
        return BindingBuilder.bind(channelNotifyQueue()).to(delayExchange()).with(BB_CHANNEL_NOTIFY_QUEUE).noargs();
    }
    /** ====渠道通知==== */


    /** ====订单关闭开始==== */
    public static final String  BB_ORDER_CLOSE_QUEUE    = "BB.ORDER.CLOSE.QUEUE";

    @Bean
    public Queue orderCloseQueue() {
        return new Queue(BB_ORDER_CLOSE_QUEUE, true);
    }

    @Bean
    public Binding bindingOrderClose() {
        return BindingBuilder.bind(orderCloseQueue()).to(delayExchange()).with(BB_ORDER_CLOSE_QUEUE).noargs();
    }
    /** ====订单关闭结束==== */


}
