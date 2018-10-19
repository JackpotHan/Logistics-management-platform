package com.jackpotHan.system.shiro;

import com.jackpotHan.entity.system.User;
import com.jackpotHan.utils.Digests;
import com.jackpotHan.utils.Encodes;


public class SimpleShiroManage {

    public static final String DEFAULT_PASSWORD = "123456";

    public static final int HASH_INTERATIONS = 1024;

    public static final int SALT_SIZE = 8;

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     *
     * @param user po层人员对象
     * @param plainPassword 明文密码5
     */
    public static void entryptPassword(User user, String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    public static String entryptPassword(String salt, String plainPassword) {
        byte[] saltHex = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), saltHex, HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     *
     * @param salt
     * @param plainPassword 明文密码
     */
    public static String entryptSimplePassword(String salt, String plainPassword) {
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

}
