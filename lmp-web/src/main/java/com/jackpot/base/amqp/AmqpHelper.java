package com.jackpot.base.amqp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: hanjt
 * @Date: 2019/1/23 11:42
 * @Description:
 */
@Component
@Slf4j
public class AmqpHelper {

    @Resource
    public AmqpTemplate amqpTemplate;

    /**
     * 优惠券购买成功
     * @param orderNo
     * @param userNo
     */
    public void buyCoupon(String orderNo,String userNo, boolean isSuccess){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userNo",userNo);
        jsonObject.put("orderNo",orderNo);
        jsonObject.put("isSuccess",isSuccess);
        amqpTemplate.convertAndSend(QueueCode.BB_BUY_COUPON,jsonObject);
    }

    /**
     * 延时发送
     * @param msg
     * @param queue
     * @param delay
     */
    public void delaySend(Object msg, String queue, long delay){
        log.info("[延时队列发送] - [{}] - [{}]", queue, JSON.toJSONString(msg));
        amqpTemplate.convertAndSend(DelayRabbitConfig.BB_DELAY_EXCHANGE, queue, msg, message -> {
            //设置过期时间  也可以setDelay 但是setDelay只能接受Integer类型
            message.getMessageProperties().setHeader("x-delay", delay);
            return message;
        });
    }

}
