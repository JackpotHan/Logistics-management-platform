package com.lmp.admin.util;

import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @description copy from “org.springframework.util.Assert”
 */
@Slf4j
public class AssertUtil {

    /**
     * 两个对象相等，否则抛出ZmException
     */
    public static void isEquals(Object obj1, Object obj2, String message) {
        if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
            throw new BaseException(message);
        }
    }

    /**
     * expression为true，否则抛出ZmException
     */
    public static void isTrue(boolean expression, String message) {
        isTrue(expression, BaseCode.RESPCODE_FAIL.getCode(), message);
    }

    /**
     * expression为true，否则抛出ZmException
     */
    public static void isTrue(boolean expression, BaseCode bizStatusEnum) {
        if (!expression) {
            throw new BaseException(bizStatusEnum);
        }
    }

    /**
     * expression为true，否则抛出ZmException
     */
    public static void isTrue(boolean expression, String code, String message) {
        if (!expression) {
            throw new BaseException(code, message);
        }
    }

    /**
     * object为null，否则抛出ZmException
     */
    public static void isNull(Object object, String message) {
        isNull(object, BaseCode.RESPCODE_FAIL.getCode(), message);
    }

    /**
     * object为null，否则抛出ZmException
     */
    public static void isNull(Object object, String code, String message) {
        if (StringUtil.isNotEmpty(object)) {
            throw new BaseException(code, message);
        }
    }

    /**
     * object不为null，否则抛出ZmException
     */
    public static void notNull(Object object, String message) {
        notNull(object, BaseCode.RESPCODE_FAIL.getCode(), message);
    }

    /**
     * object不为null，否则抛出ZmException
     */
    public static void notNull(Object object, BaseCode bizStatusEnum) {
        notNull(object, bizStatusEnum.getCode(), bizStatusEnum.getMsg());
    }

    /**
     * object不为null，否则抛出ZmException
     */
    public static void notNull(Object object, String code, String message) {
        if (StringUtil.isEmpty(object)) {
            throw new BaseException(code, message);
        }
    }

    public static void validateParams(Object obj, String... args) {
        if (obj == null) return;
        try {
            Class clazz = obj.getClass();
            for (String arg : args) {
                Field field = clazz.getDeclaredField(arg);
                field.setAccessible(true);
                if (StringUtil.isEmpty(field.get(obj))) throw new BaseException(BaseCode.RESPCODE_FAIL.getCode(), String.format("缺少参数[%s]", field.getName()));
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error(e.getMessage(), e);
        }
    }
}
