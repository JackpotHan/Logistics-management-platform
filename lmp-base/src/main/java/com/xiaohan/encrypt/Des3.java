package com.xiaohan.encrypt;

import com.xiaohan.utils.BcdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:46
 * @Description: DES3加解密
 */
public class Des3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Des3.class);

    private static final String KEY_ALGORITHM_3DES = "DESede";
    private static final String CIPHER_ALGORITHM_3DES = "DESede/ECB/NoPadding";

    /**
     * DES3加密
     * @param message
     * @param key
     * @return
     */
    public static byte[] encode(byte[] message, byte[] key) {
        try {
            byte[] km = new byte[24];
            System.arraycopy(key, 0, km, 0, 16);
            System.arraycopy(key, 0, km, 16, 8);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_3DES);
            DESedeKeySpec dks = new DESedeKeySpec(km);
            SecretKey k = SecretKeyFactory.getInstance(KEY_ALGORITHM_3DES).generateSecret(dks);
            cipher.init(Cipher.ENCRYPT_MODE, k);
            return cipher.doFinal(message);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES3字符串加密
     * @param message
     * @param key
     * @return
     */
    public static String encode(String message, String key) {
       return BcdUtil.bytesToHexString(encode(BcdUtil.hexStringToByte(message), BcdUtil.hexStringToByte(key)));
    }

    /**
     * DES3字节解密
     * @param message
     * @param key
     * @return
     */
    public static byte[] decode(byte[] message, byte[] key) {
        try {
            byte[] km = new byte[24];
            System.arraycopy(key, 0, km, 0, 16);
            System.arraycopy(key, 0, km, 16, 8);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_3DES);
            DESedeKeySpec dks = new DESedeKeySpec(km);
            SecretKey k = SecretKeyFactory.getInstance(KEY_ALGORITHM_3DES).generateSecret(dks);
            cipher.init(Cipher.DECRYPT_MODE, k);
            return cipher.doFinal(message);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES3字符串解密
     * @param message
     * @param key
     * @return
     */
    public static String decode(String message, String key) {
        return BcdUtil.bytesToHexString(decode(BcdUtil.hexStringToByte(message), BcdUtil.hexStringToByte(key)));
    }
}
