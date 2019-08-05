package com.jackpot.base.encrypt;

import com.jackpot.base.base.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:49
 * @Description:
 */
public final class RsaKey {
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaKey.class);

    private static final String KEY_ALGORITHM_RSA = "RSA";

    private static byte[] readKeyFromPath(String path) {
        try (InputStream inputStream = RsaKey.class.getResourceAsStream(path);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            byte[] b = new byte[1024];
            int length = 0;
            while (-1 != (length = inputStream.read(b))) {
                baos.write(b, 0, length);
            }
            byte[] fileBytes = baos.toByteArray();
            String key = new String(fileBytes, BaseUtil.UTF8);
            if (key.contains("-----BEGIN PRIVATE KEY-----")) {
                return Base64.decode(key.replaceAll("-----\\w+ PRIVATE KEY-----", ""));
            } else if (key.contains("-----BEGIN PUBLIC KEY-----")) {
                return Base64.decode(key.replaceAll("-----\\w+ PUBLIC KEY-----", ""));
            } else if (key.contains("-----BEGIN RSA PRIVATE KEY-----")) {
                final byte[] innerKey = Base64.decode(key.replaceAll("-----\\w+ RSA PRIVATE KEY-----", ""));
                byte[] bytes = new byte[innerKey.length + 26];
                System.arraycopy(Base64.decode("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKY="), 0, bytes, 0, 26);
                System.arraycopy(BigInteger.valueOf(bytes.length - 4).toByteArray(), 0, bytes, 2, 2);
                System.arraycopy(BigInteger.valueOf(innerKey.length).toByteArray(), 0, bytes, 24, 2);
                System.arraycopy(innerKey, 0, bytes, 26, innerKey.length);
                return bytes;
            } else {
                LOGGER.error("ERROR PEM FILES");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static PrivateKey getPriKeyFromPfx(String strPfx, String strPassword) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(strPfx);
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration<String> enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements()) {
                keyAlias = enumas.nextElement();
            }
            return (PrivateKey) ks.getKey(keyAlias, nPassword);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static PrivateKey getPriKeyFromPath(String path) {
        return getPriKeyFromByte(readKeyFromPath(path));
    }

    public static PrivateKey getPriKeyFromContent(String content) {
        return getPriKeyFromByte(Base64.decode(content));
    }

    public static PrivateKey getPriKeyFromByte(byte[] bytes) {
        try {
            KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static PublicKey getPubKeyFromPath(String path) {
        return getPubKeyFromByte(readKeyFromPath(path));
    }

    public static PublicKey getPubKeyFromContent(String content) {
        return getPubKeyFromByte(Base64.decode(content));
    }

    public static PublicKey getPubKeyFromByte(byte[] bytes) {
        try {
            KeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static PublicKey getPubKeyFromCerPath(String path) {
        try (
                InputStream inputStream = RsaKey.class.getResourceAsStream(path);
        ) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate)cf.generateCertificate(inputStream);
            return certificate.getPublicKey();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
