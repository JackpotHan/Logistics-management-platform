package com.xiaohan.encrypt;

import com.xiaohan.base.BaseUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:49
 * @Description: RSA加密
 */
public final class Rsa {
    private static final Logger LOGGER = LoggerFactory.getLogger(Rsa.class);

    private static final int CODE_LENGTH = 2048 / 8;
    private static final String KEY_ALGORITHM_RSA = "RSA";
    private static final String KEY_SIGNATURE_RSA = "SHA1WithRSA";
    private static final String CIPHER_ALGORITHM_RSA = "RSA/ECB/PKCS1Padding";

    public static byte[] signRSA(byte[] message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(KEY_SIGNATURE_RSA);
            signature.initSign(privateKey);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static byte[] signRSA(String message, PrivateKey privateKey) {
        try {
            return signRSA(message.getBytes(BaseUtil.UTF8), privateKey);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String signRSAToBase64(byte[] message, PrivateKey privateKey) {
        return Base64.encode(signRSA(message, privateKey));
    }

    public static String signRSAToBase64(String message, PrivateKey privateKey) {
        return Base64.encode(signRSA(message, privateKey));
    }

    public static byte[] signRSAWithPfx(byte[] message, PrivateKey privateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA); //秘钥算法
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);
            return signRSA(message, priKey);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static boolean designRSA(String message, String signature, PublicKey publicKey) {
        try {
            return designRSA(message, Base64.decode(signature), publicKey);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    public static boolean designRSA(String message, byte[] signature, PublicKey publicKey) {
        boolean flag = false;
        try {
            Signature sign = Signature.getInstance(KEY_SIGNATURE_RSA);
            sign.initVerify(publicKey);
            sign.update(message.getBytes(BaseUtil.UTF8));
            flag = sign.verify(signature);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return flag;
    }

    public static String encodeRSAToBase64(String message, PublicKey publicKey) {
        return Base64.encode(encodeRSA(message, publicKey));
    }

    public static byte[] encodeRSA(String message, PublicKey publicKey) {
        try {
            return encodeRSA(message.getBytes(BaseUtil.UTF8), publicKey);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String encodeRSAToBase64(byte[] bytes, PublicKey publicKey) {
        return Base64.encode(encodeRSA(bytes, publicKey));
    }

    public static byte[] encodeRSA(byte[] bytes, PublicKey publicKey) {
        try {
            byte[] encrypt = null;
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey); //公钥加密
            for (int i = 0; i < bytes.length; i += CODE_LENGTH - 11) {
                byte[] temp = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + CODE_LENGTH - 11));
                encrypt = ArrayUtils.addAll(encrypt, temp);
            }
            return encrypt;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String decodeRSA(byte[] bytes, PrivateKey privateKey) {
        try {
            StringBuilder result = new StringBuilder();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);//私钥解密
            for (int i = 0; i < bytes.length; i += 256) {
                byte[] decrypt = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + 256));
                result.append(new String(decrypt, BaseUtil.UTF8));
            }
            return result.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static byte[] decodeRSA(String message, PrivateKey privateKey) {
        byte[] bytes = Base64.decode(message);
        byte[] decrypts = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);//私钥解密
            for (int i = 0; i < bytes.length; i += 256) {
                byte[] decrypt = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + 256));
                decrypts = ArrayUtils.addAll(decrypts, decrypt);
            }
            return decrypts;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
