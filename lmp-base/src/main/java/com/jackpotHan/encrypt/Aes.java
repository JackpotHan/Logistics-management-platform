package com.jackpotHan.encrypt;

import com.jackpotHan.base.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:40
 * @Description: AES加解密
 */
public final class Aes {
    private static final Logger LOGGER = LoggerFactory.getLogger(Aes.class);

    private static final String KEY_ALGORITHM_AES = "AES";
    private static final String CIPHER_ALGORITHM_AES = "AES/ECB/PKCS5Padding";

    /**
     * AES加密
     * @param content
     * @param encryptKey
     * @return
     */
    public static byte[] encode(String content, String encryptKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(content.getBytes(BaseUtil.UTF8));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * AES加密转Base64
     * @param content
     * @param encryptKey
     * @return
     */
    public static String encodeToBase64(String content, String encryptKey) {
        return Base64.encode(encode(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes
     * @param decryptKey
     * @return
     */
    public static String decode(byte[] encryptBytes, String decryptKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes, BaseUtil.UTF8);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
