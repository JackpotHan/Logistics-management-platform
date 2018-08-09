package com.xiaohan.utils;

import com.alibaba.fastjson.JSON;

import com.xiaohan.base.BaseUtil;
import com.xiaohan.encrypt.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AgentHttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentHttpUtil.class);

    @Value("${usr.sign.key}") private String ADMIN_SIGN_KEY;
    @Value("${admin.app.id}") private String ADMIN_APP_ID;
    @Value("${bnh.service.gateway}") private String BNH_SERVICE_GATEWAY;

    public String bnhServiceSendPost(Map<String,String> params){
        LOGGER.info("bnhServiceSendPost reciver:"+JSON.toJSONString(params));
        params.put("token","d877dec4e93b6e025194a134f54bd9442ec009ca");
        params.put("version",BaseUtil.isEmpty(params.get("version"))?"1.0":params.get("version"));
        params.put("appid",ADMIN_APP_ID);
        List<Map.Entry<String, String>> entries = params.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        entries.forEach((entry) -> {
            if(BaseUtil.isNotEmpty(entry.getValue()))
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        });
        builder.append("signKey=").append(ADMIN_SIGN_KEY);
        LOGGER.info("SIGN:{}", builder.toString());
        params.put("sign", MD5.encode(builder.toString()));
        LOGGER.info("SEND:{}", params);
        String responseEntity = HttpUtil.sendPost(BNH_SERVICE_GATEWAY,params);
        return responseEntity;
    }
}
