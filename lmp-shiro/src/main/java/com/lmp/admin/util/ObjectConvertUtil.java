package com.lmp.admin.util;

import com.jackpot.base.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 实体类转换
 * @date 2018/6/5 16:06
 */
public class ObjectConvertUtil {

    private static Logger logger = LoggerFactory.getLogger(ObjectConvertUtil.class);

    /**
     * src 转换成tar 并返回tar 对象
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     * @
     */
    public static <T> T convert(Object src, Class<T> tar) {
        try {
            if (src != null) {
                T t = tar.newInstance();
                ObjectUtils.copyObjectField(src, t);
                return t;
            }
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        }
        return null;
    }


    /**
     * src 转换成tar 并返回tar 对象 忽略某些字段
     *
     * @param src
     * @param tar
     * @param ignores 忽略的字段
     * @param <T>
     * @return
     * @
     */
    public static <T> T convert(Object src, Class<T> tar, String... ignores)  {
        try {
            if (src != null) {
                T t = tar.newInstance();
                ObjectUtils.copyObjectField(src, t, ignores);
                return t;
            }
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        }
        return null;
    }

    /**
     * src 转换成tar 并返回tar 对象  指定字段转换
     *
     * @param src
     * @param tar
     * @param options 指定的字段
     * @param <T>
     * @return
     * @
     */
    public static <T> T convertOptional(Object src, Class<T> tar, String... options)  {
        try {
            if (src != null) {
                T t = tar.newInstance();
                ObjectUtils.copyObjectOptionalField(src, t, options);
                return t;
            }
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new BaseException("Convert error : " + e.getCause().getMessage());
        }
        return null;
    }


    /**
     * src 列表 转化成 tar列表
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     * @
     */
    public static <T> List<T> convertList(List<?> src, Class<T> tar)  {

        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(dto -> {
                try {
                    T t = tar.newInstance();
                    ObjectUtils.copyObjectField(dto, t);
                    vList.add(t);
                } catch (InstantiationException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                }
            });
            return vList;
        }

        return vList;
    }


    /**
     * src 列表 转化成 tar列表 忽略某些字段
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     * @
     */
    public static <T> List<T> convertList(List<?> src, Class<T> tar, String... ignores)  {

        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(dto -> {
                try {
                    T t = tar.newInstance();
                    ObjectUtils.copyObjectField(dto, t, ignores);
                    vList.add(t);
                } catch (InstantiationException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                }
            });
            return vList;
        }

        return null;
    }


    /**
     * src 列表 转化成 tar列表 指定某些字段
     *
     * @param src
     * @param tar
     * @param <T>
     * @return
     * @
     */
    public static <T> List<T> convertListOptional(List<?> src, Class<T> tar, String... options)  {

        List<T> vList = new ArrayList<>();

        if (src != null && src.size() > 0) {
            src.forEach(dto -> {
                try {
                    T t = tar.newInstance();
                    ObjectUtils.copyObjectOptionalField(dto, t, options);
                    vList.add(t);
                } catch (InstantiationException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                    throw new BaseException("Convert error : " + e.getCause().getMessage());
                }
            });
            return vList;
        }

        return null;
    }

}
