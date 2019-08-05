package com.jackpot.base.feign.logger;


import feign.Request;
import feign.Response;
import feign.Util;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * @author: emmet
 * @date: 2018/12/18
 */
public class LmpFeignLogger extends feign.Logger {

    private final Logger logger;

    public LmpFeignLogger(Logger logger) {
        this.logger = logger;
    }
    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        this.log(configKey, "---> HTTP/1.1 %s %s", request.method(), request.url());
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        int status = response.status();
        String reason = response.reason() != null && logLevel.compareTo(feign.Logger.Level.NONE) > 0 ? " " + response.reason() : "";
        if (response.body() != null && status != 204 && status != 205) {
            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
            if (bodyData.length > 0) {
                String responseData = Util.decodeOrDefault(bodyData, Util.UTF_8, "Binary data");
                this.log(configKey, "<--- HTTP/1.1 %s%s (%sms) {%s}", status, reason, elapsedTime, responseData);
            }
            return response.toBuilder().body(bodyData).build();
        } else {
            this.log(configKey, "<--- HTTP/1.1 %s%s (%sms)", status, reason);
        }
        return response;
    }
}
