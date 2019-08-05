package com.lmp.admin.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 加解密工具类
 * MD5加密
 * BASE64加解密
 * DES加解密
 * AES加解密
 * RSA加解密
 * RSA签到验签
 *
 * @version V1.3
 * @date 2016-11-04
 */
public final class CipherUtil {
    private static final Logger logger = LoggerFactory.getLogger(CipherUtil.class);
    private static final String ENCODING = "UTF-8";
    private static final int CODE_LENGTH = 2048 / 8;
    private static final String KEY_ALGORITHM_DES = "DES";
    private static final String KEY_ALGORITHM_AES = "AES";
    private static final String KEY_ALGORITHM_RSA = "RSA";
    private static final String KEY_SIGNATURE_RSA = "SHA1WithRSA";
    private static final String KEY_SIGNATURE_RSA_SHA256 = "SHA256withRSA";
    private static final String CIPHER_ALGORITHM_DES = "DES/ECB/NoPadding";
    private static final String CIPHER_ALGORITHM_AES = "AES/ECB/PKCS5Padding";
    public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    public static final String AES_CBC_PKCS7_PADDING = "AES/CBC/PKCS7Padding";
    public static final String AES_CBC_NO_PADDING = "AES/CBC/NoPadding";
    private static final String CIPHER_ALGORITHM_RSA = "RSA/ECB/PKCS1Padding";

    public static final String RSA_PRIVATE_KEY = "rsa_private_key.pem";
    public static final String RSA_PRIVATE_KEY_PKCS8 = "rsa_private_key_pkcs8.pem";
    public static final String RSA_PUBLIC_KEY = "rsa_public_key.pem";

    /**
     * MD5加密-指定编码方式
     *
     * @param content
     * @param encoding
     * @return
     */
    public static String encodeMd5(String content, String encoding) {
        try {
            return DigestUtils.md5Hex(content.getBytes(encoding)).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * MD5加密
     *
     * @param md5Key key
     * @return
     */
    public static String encodeMd5(Map<String, Object> map, String md5Key, boolean toLower) {
        String content = "";
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
            int size = list.size();
            String[] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            for (int i = 0; i < size; i++) {
                stringBuilder.append(arrayToSort[i]);
            }
            stringBuilder.append(md5Key);
            content = stringBuilder.toString();
            String encodeMd5 = encodeMd5(content);
            if (toLower) {
                return encodeMd5.toLowerCase();
            }
            return encodeMd5;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * MD5加密
     *
     * @param md5Key key
     * @return
     */
    public static String encodeMd5(Map<String, Object> map, String md5Key) {
        return encodeMd5(map, md5Key, true);
    }

    /**
     * MD5加密  默认UTF-8
     *
     * @param content
     * @return
     */
    public static String encodeMd5(String content) {
        return encodeMd5(content, ENCODING);
    }

    /**
     * BASE64加密
     *
     * @param content
     * @return
     */
    public static String encodeBase64(byte[] content) {
        try {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(content), ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * BASE64加密
     *
     * @param content
     * @return
     */
    public static String encodeBase64(String content) {
        try {
            return encodeBase64(content.getBytes(ENCODING));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * BASE64解密
     *
     * @param content
     * @return
     */
    public static byte[] decodeBase64(String content) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(content);
    }

    /**
     * Base64解密
     *
     * @param content
     * @return
     */
    public static String decodeBase64String(String content) {
        try {
            return new String(decodeBase64(content), ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES加密
     *
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
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * DES解密
     *
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
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * AES加密
     *
     * @param content
     * @param encryptKey
     * @return
     */
    public static byte[] encodeAES(String content, String encryptKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(content.getBytes(ENCODING));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64(AES加密)
     *
     * @param content
     * @param encryptKey
     * @return
     */
    public static String encodeAESBase64(String content, String encryptKey) {
        return encodeBase64(encodeAES(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 密文
     * @param decryptKey   密钥
     * @return
     */
    public static String decodeAES(byte[] encryptBytes, String decryptKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes, ENCODING);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * AES解密
     *
     * @param encryptString 密文(Base64)
     * @param decryptKey    密钥
     * @return
     */
    public static String decodeAESString(String encryptString, String decryptKey) {
        return decodeAES(decodeBase64(encryptString), decryptKey);
    }


    /**
     * AES 加密带向量
     *
     * @param content     内容
     * @param encryptKey  秘钥
     * @param iv          向量
     * @param encryptType 加密模式
     * @return
     */
    public static byte[] encodeAES(String content, String encryptKey, String iv, String encryptType) {
        try {
            Cipher cipher = Cipher.getInstance(encryptType);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());  // 初始化向量
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
            return cipher.doFinal(content.getBytes(ENCODING));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * AES 解密带向量
     *
     * @param encryptBytes 密文
     * @param decryptKey   秘钥
     * @param iv           向量
     * @param encryptType  解密方式
     * @return
     */
    public static String decodeAES(byte[] encryptBytes, String decryptKey, String iv, String encryptType) {
        try {
            Cipher cipher = Cipher.getInstance(encryptType);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());  // 初始化向量
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptKey.getBytes(), KEY_ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes, ENCODING);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 读取PEM文件
     *
     * @param path
     * @return
     */
    public static byte[] readPemKey(String path) {
        path = CipherUtil.class.getResource(path).getFile();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            return handlerPemFile(fileInputStream);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <ul>
     * <li>
     * obtain file in jar!
     * </li>
     * </ul>
     *
     * @param inputStream file stream
     * @return bytes
     */
    private static byte[] readPemKey(InputStream inputStream) {
        try {
            return handlerPemFile(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private static byte[] handlerPemFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = inputStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, n);
        }
        String key = new String(outSteam.toByteArray(), "UTF-8");
        outSteam.close();
        inputStream.close();
        if (key.contains("-----BEGIN PRIVATE KEY-----")) {
            return decodeBase64(key.replaceAll("-----\\w+ PRIVATE KEY-----", ""));
        } else if (key.contains("-----BEGIN PUBLIC KEY-----")) {
            return decodeBase64(key.replaceAll("-----\\w+ PUBLIC KEY-----", ""));
        } else if (key.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            final byte[] innerKey = decodeBase64(key.replaceAll("-----\\w+ RSA PRIVATE KEY-----", ""));
            byte[] bytes = new byte[innerKey.length + 26];
            System.arraycopy(decodeBase64("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKY="), 0, bytes, 0, 26);
            System.arraycopy(BigInteger.valueOf(bytes.length - 4).toByteArray(), 0, bytes, 2, 2);
            System.arraycopy(BigInteger.valueOf(innerKey.length).toByteArray(), 0, bytes, 24, 2);
            System.arraycopy(innerKey, 0, bytes, 26, innerKey.length);
            return bytes;
        } else {
            logger.error("ERROR PEM FILES");
            return null;
        }
    }


    /**
     * 通过私钥文件生成RSA私钥
     *
     * @param path 私钥pem文件路径
     * @return
     */
    public static RSAPrivateKey getRSAPriKey(String path) {
        return getRSAPriKey(readPemKey(CipherUtil.class.getResourceAsStream(path)));
    }

    /**
     * 通过私钥串生成RSA私钥
     *
     * @param bytes 通过decodeBase64()
     * @return
     */
    public static RSAPrivateKey getRSAPriKey(byte[] bytes) {
        try {
            KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 通过公钥文件生成RSA公钥
     *
     * @param path
     * @return
     */
    public static RSAPublicKey getRSAPubKey(String path) {
        return getRSAPubKey(readPemKey(CipherUtil.class.getResourceAsStream(path)));
    }

    /**
     * 通过公钥串生成RSA公钥
     *
     * @param bytes 通过decodeBase64()
     * @return
     */
    public static RSAPublicKey getRSAPubKey(byte[] bytes) {
        try {
            KeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 生成RSA公私钥
     *
     * @return Map<String , String> priKey  -  pubKey
     */
    public static Map<String, String> getRSAKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            /** 得到公钥 */
            Key publicKey = keyPair.getPublic();
            /** 得到私钥 */
            Key privateKey = keyPair.getPrivate();
            Map<String, String> map = new HashMap<>();
            map.put("priKey", CipherUtil.encodeBase64(privateKey.getEncoded()));
            map.put("pubKey", CipherUtil.encodeBase64(publicKey.getEncoded()));
            return map;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA私钥签名
     *
     * @param message
     * @param privateKey
     * @return
     */
    public static String signRSABase64(String message, PrivateKey privateKey) {
        return encodeBase64(signRSA(message, ENCODING, privateKey));
    }

    /**
     * RSA私钥签名
     *
     * @param message
     * @param privateKey
     * @return
     */
    public static byte[] signRSA(String message, PrivateKey privateKey) {
        return signRSA(message, ENCODING, privateKey);
    }

    /**
     * RSA私钥签名
     *
     * @param message
     * @param encoding
     * @param privateKey
     * @return
     */
    public static byte[] signRSA(String message, String encoding, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(KEY_SIGNATURE_RSA);
            signature.initSign(privateKey);
            signature.update(message.getBytes(encoding));
            return signature.sign();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * RSA私钥签名
     *
     * @param message
     * @param privateKey
     * @return
     */
    public static byte[] signRSABySHA256(String message, PrivateKey privateKey) {
        return signRSABySHA256(message, ENCODING, privateKey);
    }

    /**
     * RSA私钥签名
     *
     * @param message
     * @param encoding
     * @param privateKey
     * @return
     */
    public static byte[] signRSABySHA256(String message, String encoding, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(KEY_SIGNATURE_RSA_SHA256);
            signature.initSign(privateKey);
            signature.update(message.getBytes(encoding));
            return signature.sign();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * RSA公钥验签
     *
     * @param message
     * @param signature
     * @param publicKey
     * @return
     */
    public static boolean designRSA(String message, byte[] signature, PublicKey publicKey) {
        boolean flag = false;
        try {
            Signature sign = Signature.getInstance(KEY_SIGNATURE_RSA);
            sign.initVerify(publicKey);
            sign.update(message.getBytes(ENCODING));
            flag = sign.verify(signature);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return flag;
    }

    /**
     * RSA公钥验签
     *
     * @param message
     * @param signature
     * @param publicKey
     * @return
     */
    public static boolean designRSABySHA256(String message, byte[] signature, PublicKey publicKey) {
        boolean flag = false;
        try {
            Signature sign = Signature.getInstance(KEY_SIGNATURE_RSA_SHA256);
            sign.initVerify(publicKey);
            sign.update(message.getBytes(ENCODING));
            flag = sign.verify(signature);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return flag;
    }

    /**
     * RSA公钥加密
     *
     * @param bytes
     * @param publicKey
     * @return
     */
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
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * RSA公钥加密
     *
     * @param message
     * @param publicKey
     * @return
     */
    public static byte[] encodeRSA(String message, PublicKey publicKey) {
        try {
            return encodeRSA(message.getBytes(ENCODING), publicKey);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Base64(RSA公钥加密)
     *
     * @param message
     * @param publicKey
     * @return
     */
    public static String encodeRSABase64(String message, PublicKey publicKey) {
        return encodeBase64(encodeRSA(message, publicKey));
    }


    /**
     * RSA私钥解密
     *
     * @param bytes
     * @param privateKey
     * @return
     */
    public static String decodeRSA(byte[] bytes, PrivateKey privateKey) {
        try {
            StringBuilder result = new StringBuilder();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);//私钥解密
            for (int i = 0; i < bytes.length; i += 256) {
                byte[] decrypt = cipher.doFinal(ArrayUtils.subarray(bytes, i, i + 256));
                result.append(new String(decrypt, ENCODING));
            }
            return result.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 从读取 *.pfx证书
     *
     * @param strPfx      证书文件路径
     * @param strPassword 证书密码
     * @return prikey 私钥
     */
    public static PrivateKey GetPvkformPfx(String strPfx, String strPassword) {
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
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
//            java.security.cert.Certificate cert = ks.getCertificate(keyAlias);
//            PublicKey pubkey = cert.getPublicKey();
            return prikey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二磁道加密
     *
     * @param trkKey
     * @param track
     * @return
     */
    public static String encryptStanderTranck(String trkKey, String track) throws Exception {
        logger.debug("trkKey:" + trkKey);
        int trkLen = track.length();
        //track=Int2HexStr(trkLen)+track;
        int mod = trkLen % 16;
        logger.debug("mod:" + mod);
        for (int i = 0; i < 16 - mod; i++) {
            track += "F";
        }

        String mTrack = "";
        try {
            logger.debug("track:" + track);
            mTrack = byte2hex(encode3DES(hexStringToByte(trkKey), hexStringToByte(track)));
            mTrack = Int2HexStr(trkLen) + mTrack;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return mTrack;
    }

    /**
     * 二磁道解密
     *
     * @param trkKey
     * @return
     */
    public static String decryptStanderTranck(String trkKey, String desTtrack) throws Exception {
        String track = "";
        try {
            int len = Integer.parseInt(desTtrack.substring(0, 2), 16);
            desTtrack = desTtrack.substring(2);
            track = decode3DES(desTtrack, trkKey);
            track = track.substring(0, len).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("二磁道密钥错误！");
        }
        return track;
    }


    /**
     * 转成十六进制数字
     *
     * @param n
     * @return
     */
    public static String Int2HexStr(int n) {
        String HexStr = Integer.toHexString(n);
        HexStr = HexStr.length() == 1 ? "0" + HexStr : HexStr;
        return HexStr;
    }


    /**
     * 3DES解密
     *
     * @param dataS 解密数据
     * @return keys 密钥
     */
    public static String decode3DES(String dataS, String keys) throws Exception {
        String result = "";
        try {
            byte[] data = hexStringToByte(dataS);
            byte[] key = hexStringToByte(keys);
            byte[] km = new byte[24];
            System.arraycopy(key, 0, km, 0, 16);
            System.arraycopy(key, 0, km, 16, 8);
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            DESedeKeySpec dks = new DESedeKeySpec(km);
            SecretKey k = SecretKeyFactory.getInstance("DESede")
                    .generateSecret(dks);
            cipher.init(Cipher.DECRYPT_MODE, k);
            result = byte2hex(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return result;
    }

    /**
     * 3DES加密
     *
     * @param key  密钥
     * @param data 待加密数据
     * @return byte[] 加密数据
     */
    public static byte[] encode3DES(byte[] key, byte[] data) throws Exception {
        try {
            byte[] km = new byte[24];
            System.arraycopy(key, 0, km, 0, 16);
            System.arraycopy(key, 0, km, 16, 8);
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            DESedeKeySpec dks = new DESedeKeySpec(km);
            SecretKey k = SecretKeyFactory.getInstance("DESede")
                    .generateSecret(dks);
            cipher.init(Cipher.ENCRYPT_MODE, k);
            byte[] result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 3DES加密
     *
     * @param dataS 待加密数据
     * @return keys 加密数据
     */
    public static String encode3DES(String dataS, String keys) throws Exception {
        byte[] data = hexStringToByte(dataS);
        byte[] key = hexStringToByte(keys);
        String result = byte2hex(encode3DES(key, data));
        return result;
    }


    public static byte[] hexStringToByte(String hex) {
        hex = hex.toUpperCase();
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }


    /**
     * 转换成十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            //if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }

    public static String LAKLSHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static StringBuffer strToPem(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (i % 63 == 0 && i != 0)
                stringBuffer.append("\n");
            stringBuffer.append(str.substring(i, i + 1));
        }
        return stringBuffer;
    }


    /**
     * 使用linux系统openssl工具生成公私钥
     *
     * @return
     */
    public synchronized static Map<String, String> getRSAKeyFile() {
        Map<String, String> map = new HashMap<>();
        Map<String, Process> processMap = new HashMap<>();
        InputStream in = null;
        BufferedReader read = null;
        try {
//            String rsa_private_key = "rsa_private_key.pem";
//            String rsa_private_key_pkcs8 = "rsa_private_key_pkcs8.pem";
//            String rsa_public_key = "rsa_public_key.pem";
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec("./create_rsa.sh");
            exec.waitFor();
            long start_time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - start_time) < 500) {
                try {
                    // 检测是否执行完毕，如果500毫秒还没执行完毕则强制跳出
                    exec.exitValue();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            exec.destroy();
            processMap.put(RSA_PRIVATE_KEY, runtime.exec("cat " + RSA_PRIVATE_KEY));
            processMap.put(RSA_PRIVATE_KEY_PKCS8, runtime.exec("cat " + RSA_PRIVATE_KEY_PKCS8));
            processMap.put(RSA_PUBLIC_KEY, runtime.exec("cat " + RSA_PUBLIC_KEY));
            Set<String> keySet = processMap.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Process pro = processMap.get(key);
                StringBuffer stringBuffer = new StringBuffer();
                pro.waitFor();
                in = pro.getInputStream();
                read = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = read.readLine()) != null) {
                    if (line.indexOf("-----") >= 0)
                        continue;
                    stringBuffer.append(line);
                }
                map.put(key, stringBuffer.toString());
            }
        } catch (IOException e) {
            logger.error("密钥文件生产失败:{}", e.getMessage());
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return map;
    }

    /**
     * 获取文件 MD5值
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取文件夹中 MD5值
     *
     * @param file
     * @param listChild
     * @return
     */
    public static Map<String, String> getDirMD5(File file, boolean listChild) {
        if (!file.isDirectory()) {
            return null;
        }
        //<filepath,md5>
        Map<String, String> map = new HashMap<>();
        String md5;
        File files[] = file.listFiles();
        for (File f : files) {
            if (f.isDirectory() && listChild) {
                map.putAll(getDirMD5(f, listChild));
            } else {
                md5 = getFileMD5(f);
                if (md5 != null) {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }


    /**
     * SHA-1消息摘要算法
     */
    public static String encodeSHA(byte[] data)  {
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-256消息摘要算法
     */
    public static String encodeSHA256ByString(String content)  {
        byte[] data = new byte[0];
        try {
            data = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeSHA256(data);
    }
    /**
     * SHA-256消息摘要算法
     */
    public static String encodeSHA256(byte[] data) {
        MessageDigest md = null;
        // 初始化MessageDigest,SHA即SHA-1的简称
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-384消息摘要算法
     */
    public static String encodeSHA384(byte[] data)  {
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-512消息摘要算法
     */
    public static String encodeSHA512(byte[] data)  {
        // 初始化MessageDigest,SHA即SHA-1的简称
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 执行摘要方法
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

   /* public static void main(String[] args) {
        Arrays.stream(getMacMd5()).forEach(m->System.out.println("\""+m+"\","));
        System.out.println(encodeMd5("90:B0:ED:34:08:9E".toLowerCase().replaceAll(":",""),CipherUtil.ENCODING).toLowerCase());
    }*/


}
