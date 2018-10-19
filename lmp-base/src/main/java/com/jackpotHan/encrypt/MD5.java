package com.jackpotHan.encrypt;

import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.utils.StrConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:49
 * @Description: MD5加密
 */
public final class MD5 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5.class);

    public static String encode(String content) {
        return encode(content, BaseUtil.UTF8);
    }

    /**
     * Md5加密  返回值全部大写
     * @param messge
     * @param encoding
     * @return md5 result
     */
    public static String encode(String messge, String encoding) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(messge.getBytes(encoding));// 使用指定的字节更新摘要
            byte[] md = mdInst.digest();// 获得密文
            return StrConvertUtil.byteArrToHexStr(md).toUpperCase();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
