package com.jackpotHan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description: 发短信工具类
 */
public class SmsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

    public static void SendHyMessage(String mobile, String content) {
        try {
            String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit&account=C32265326&password=e0bc8f0f95459aad73ee661d73281458&mobile=%s&content=%s";
            String ret = HttpUtil.sendGet(String.format(url, mobile, URLEncoder.encode(content)));
            if (!ret.contains("提交成功")) {
                LOGGER.error("短信发送失败：{}-{}-{}", mobile, content, ret);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static boolean sendCode(String mobile, String code) {
        String content = "【帮帮还】您的短信验证码是%s，请勿将验证码泄露给任何人。您还可通过如下方式进行操作 t.cn/Rd4Dyvm ，感谢您的支持！";
        return sendClMessage(mobile, String.format(content, code));
    }

    public static boolean sendClMessage(String mobile, String content) {
        try {
            String url = "http://smssh1.253.com/msg/send/json";
            Map<String, String> params = new HashMap<>();
            params.put("account", "N0411747");
            params.put("password", "t9oU2Nej5A44b9");
            params.put("phone", mobile);
            params.put("msg", content);
            String ret = HttpUtil.sendJsonPost(url, JSON.toJSONString(params));
            JSONObject retObject = JSON.parseObject(ret);
            if (!"0".equals(retObject.getString("code"))) {
                LOGGER.error("短信发送失败：{}-{}-{}", mobile, content, ret);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        sendCode("15757100761", "329877");
    }
}
