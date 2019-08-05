package com.lmp.admin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列号生成器
 *
 * @author liuzhangsheng
 * @create 2019/3/28
 */
public class SerialNumberGenerator {
    private static int number = 0;

    private SerialNumberGenerator() {
    }

    public static synchronized String getNextNumber() {
        ++number;
        if (number > 999999) {
            number = 1;
        }
        String numStr = String.valueOf(number);
        StringBuilder sb = new StringBuilder(numStr);
        for (int i = 0; i < 6 - numStr.length(); i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static synchronized String getNextNumberWithDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(dateTimeFormatter) + getNextNumber();
    }

}
