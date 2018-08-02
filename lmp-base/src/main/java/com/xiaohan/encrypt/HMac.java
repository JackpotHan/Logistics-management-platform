package com.xiaohan.encrypt;

import com.xiaohan.base.BaseUtil;
import com.xiaohan.utils.StrConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:47
 * @Description: HmacMD5加密
 */
public final class HMac {
    private static final Logger LOGGER = LoggerFactory.getLogger(HMac.class);

    /**
     * HmacMD5加密
     * @param data
     * @param secret
     * @return
     */
    public static byte[] encode(byte[] data, byte[] secret) {
        try {
            SecretKey secretKey = new SecretKeySpec(secret, "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String encode(String data, String secret){

        try {
            byte[] bytes = encode(data.getBytes(BaseUtil.UTF8),secret.getBytes(BaseUtil.UTF8));
            return StrConvertUtil.byteArrToHexStr(bytes);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
