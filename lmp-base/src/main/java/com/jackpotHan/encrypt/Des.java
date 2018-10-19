package com.jackpotHan.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:46
 * @Description: DES加解密
 */
public final class Des {
    private static final Logger LOGGER = LoggerFactory.getLogger(Des.class);

    private static final String KEY_ALGORITHM_DES = "DES";
    private static final String CIPHER_ALGORITHM_DES = "DES/ECB/NoPadding";

    /**
     * DES加密
     * @param message
     * @param key
     * @return
     */
    public static byte[] encodeDes(byte[] message, byte[] key) {
        try {
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM_DES);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_DES);
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            return cipher.doFinal(message);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES解密
     * @param message
     * @param key
     * @return
     */
    public static byte[] decodeDes(byte[] message, byte[] key) {
        try {
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM_DES);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            return cipher.doFinal(message);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
