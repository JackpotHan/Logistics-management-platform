package com.jackpotHan.encrypt;


import com.jackpotHan.base.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:46
 * @Description: Base64加解密
 */
public final class Base64 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64.class);

    /**
     * Base64加密
     * @param content
     * @return
     */
    public static String encode(byte[] content) {
        try {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(content), BaseUtil.UTF8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64加密
     * @param content
     * @return
     */
    public static String encode(String content) {
        try {
            return encode(content.getBytes(BaseUtil.UTF8));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64解密
     * @param content
     * @return
     */
    public static byte[] decode(String content) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(content);
    }

    /**
     * Base64解密转字符串
     * @param content
     * @return
     */
    public static String decodeToString(String content) {
        try {
            return new String(decode(content), BaseUtil.UTF8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
