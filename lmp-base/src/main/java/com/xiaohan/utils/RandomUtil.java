package com.xiaohan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description: 随机工具类
 */
public final class RandomUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtil.class);

    /**
     * 生成随机长度字符串
     * @param length 字符串长度
     * @return
     */
    public static String randomStr(int length) {
        return String.format("%0"+length+"d", (int)(Math.random() * Math.pow(10, length)));
    }
}
