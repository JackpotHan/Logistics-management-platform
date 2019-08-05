/**
 * Date:2016年11月25日下午4:43:20
 * <p>
 * Copyright (c) 2016, sunxiaolu@unionpay95516.com All Rights Reserved.
 *
 * @author sunxiaolu
 */

package com.lmp.admin.util;

import com.jackpot.base.base.BaseException;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 对象转换
 *
 * @author sunxiaolu
 * @date: 2016年11月25日 下午4:43:20
 * @see
 * @since JDK 1.8
 */
public class ObjectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 判断对象及参数是否为空
     *
     * @param obj
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static boolean isBlankObjectParam(Object obj) {
        if (isBlankObject(obj))
            return true;
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (!"serialVersionUID".equals(f.getName()) && f.get(obj) != null) {
                    return false;
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return true;
            }
        }
        return true;
    }

    /**
     * 判断对象及参数不为为空
     *
     * @param obj
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static boolean isNotBlankObjectParam(Object obj) {
        if (isBlankObject(obj))
            return false;
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (!"serialVersionUID".equals(f.getName()) && f.get(obj) != null) {
                    return true;
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static boolean isBlankObject(Object obj) {
        if (obj == null)
            return true;
        return false;
    }

    private static void setFieldValue(Object obj, Field f, Object value) throws IllegalAccessException {
        String type = f.getType().getName();
        String valueStr = value.toString();
        if (type.equals("java.util.Date")) {
            f.set(obj, DateUtil.toDateTime(valueStr));
        } else if (type.equals("java.lang.Boolean")) {
            f.set(obj, Boolean.parseBoolean(valueStr));
        } else if (type.equals("java.lang.Byte")) {
            f.set(obj, Byte.parseByte(valueStr));
        } else if (type.equals("java.lang.Short")) {
            f.set(obj, Short.parseShort(valueStr));
        } else if (type.equals("java.lang.Integer")) {
            f.set(obj, Integer.parseInt(valueStr));
        } else if (type.equals("java.lang.Long")) {
            f.set(obj, Long.parseLong(valueStr));
        } else if (type.equals("java.lang.Float")) {
            f.set(obj, Float.parseFloat(valueStr));
        } else if (type.equals("java.lang.Double")) {
            f.set(obj, Double.parseDouble(valueStr));
        } else if (type.equals("java.math.BigDecimal")) {
            f.set(obj, new BigDecimal(valueStr));
        } else {
            f.set(obj, value);
        }
    }

    /**
     * 复制map中的值到实体中,map中的值必须是String类型
     *
     * @param map
     * @param obj
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyMapToObject(Map<String, Object> map, Object obj) throws Exception {
        if (isBlankObject(obj))
            return;
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
//            if (f.isAnnotationPresent(Id.class)) {
//                System.out.println("test");
//            }
            // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
            String name = f.getName();
            Object value = map.get(name);
            if ("serialVersionUID".equals(name) || value == null) {
                continue;
            }
            setFieldValue(obj, f, value);
        }
    }

    /**
     * 复制map中的值到实体中,map中的值必须是String类型 可忽略某些值
     *
     * @param map
     * @param obj
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyMapToObject(Map<String, Object> map, Object obj, String... ignores) throws Exception {
        if (isBlankObject(obj))
            return;
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
//            if (f.isAnnotationPresent(Id.class)) {
//                System.out.println("test");
//            }
            // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
            String name = f.getName();
            Object value = map.get(name);
            if ("serialVersionUID".equals(name) || value == null) {
                continue;
            }
            boolean isIgnore = false;
            for (String ignore : ignores) {
                if (name.equalsIgnoreCase(ignore)) {
                    isIgnore = true;
                    break;
                }
            }
            if (!isIgnore) {
                setFieldValue(obj, f, value);
            }
        }
    }

    /**
     * 复制对象中的值到另一个对象中
     *
     * @param src
     * @param tar
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyObjectField(Object src, Object tar) throws BaseException {
        if (isBlankObject(src) || isBlankObject(tar)) {
            throw new BaseException("Object src or Object tar is null, please check them.");
        }
        try {
            for (Field srcField : src.getClass().getDeclaredFields()) {
                srcField.setAccessible(true);
                String name = srcField.getName();
                Object value = srcField.get(src);
                if ("serialVersionUID".equals(name) || value == null) {
                    continue;
                }
                for (Field tarField : tar.getClass().getDeclaredFields()) {
                    tarField.setAccessible(true);
                    if (name.equalsIgnoreCase(tarField.getName())) {
                        tarField.set(tar, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("copyObjectField error : " + e.getCause().getMessage());
        }
    }

    /**
     * 复制对象中的值到另一个对象中
     *
     * @param src
     * @param tar
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyObjectFieldContainsFather(Object src, Object tar) throws BaseException {
        if (isBlankObject(src) || isBlankObject(tar)) {
            throw new BaseException("Object src or Object tar is null, please check them.");
        }
        try {
            Class<?> clazz = src.getClass();
            List<Field> fields = new ArrayList<>();
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            if (!"java.lang.Object".equals(clazz.getSuperclass().getTypeName())) {
                fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            }
            Class<?> tarClazz = tar.getClass();
            List<Field> tarFields = new ArrayList<>();
            tarFields.addAll(Arrays.asList(tarClazz.getDeclaredFields()));
            if (!"java.lang.Object".equals(tarClazz.getSuperclass().getTypeName())) {
                tarFields.addAll(Arrays.asList(tarClazz.getSuperclass().getDeclaredFields()));
            }
            for (Field srcField : fields) {
                srcField.setAccessible(true);
                String name = srcField.getName();
                Object value = srcField.get(src);
                if ("serialVersionUID".equals(name) || value == null) {
                    continue;
                }
                for (Field tarField : tarFields) {
                    tarField.setAccessible(true);
                    if (name.equalsIgnoreCase(tarField.getName())) {
                        tarField.set(tar, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("copyObjectField error : " + e.getCause().getMessage());
        }
    }

    /**
     * 复制对象中的值到另一个对象中,可以忽视指定字段
     *
     * @param src
     * @param tar
     * @param ignores
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyObjectField(Object src, Object tar, String... ignores) throws BaseException {
        if (isBlankObject(src) || isBlankObject(tar) || ArrayUtils.isEmpty(ignores)) {
            throw new BaseException("Object src or Object tar or ignores is null, please check them.");
        }
        try {
            for (Field srcField : src.getClass().getDeclaredFields()) {
                srcField.setAccessible(true);
                String name = srcField.getName();
                Object value = srcField.get(src);
                if ("serialVersionUID".equals(name) || value == null) {
                    continue;
                }
                boolean isIgnore = false;
                for (String ignore : ignores) {
                    if (name.equalsIgnoreCase(ignore)) {
                        isIgnore = true;
                        break;
                    }
                }
                if (isIgnore) {
                    continue;
                }
                for (Field tarField : tar.getClass().getDeclaredFields()) {
                    tarField.setAccessible(true);
                    if (name.equalsIgnoreCase(tarField.getName())) {
                        tarField.set(tar, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("copyObjectField error : " + e.getCause().getMessage());
        }
    }

    /**
     * 复制对象中的值到另一个对象中,选择指定字段
     *
     * @param src
     * @param tar
     * @param options src fields
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void copyObjectOptionalField(Object src, Object tar, String... options) throws BaseException {
        if (isBlankObject(src) || isBlankObject(tar) || ArrayUtils.isEmpty(options)) {
            throw new BaseException("Object src or Object tar or Options is null, please check them.");
        }
        try {
            for (Field srcField : src.getClass().getDeclaredFields()) {
                srcField.setAccessible(true);
                String name = srcField.getName();
                Object value = srcField.get(src);
                if ("serialVersionUID".equals(name) || value == null) {
                    continue;
                }
                List<String> optionsList = new ArrayList<>();
                for (String o : options) {
                    optionsList.add(o.toLowerCase());
                }
                for (Field tarField : tar.getClass().getDeclaredFields()) {
                    tarField.setAccessible(true);
                    if (name.equalsIgnoreCase(tarField.getName()) && optionsList.contains(name.toLowerCase())) {
                        tarField.set(tar, value);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("copyObjectField error : " + e.getCause().getMessage());
        }
    }


    /**
     * 获取对象中所有字段名称
     *
     * @param obj
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static String[] getObjectAllFields(Object obj) throws BaseException {
        if (isBlankObject(obj)) {
            throw new BaseException("Object obj is null, please check them.");
        }
        String[] declaredFieldNameArr = null;
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            int length = declaredFields.length;
            declaredFieldNameArr = new String[length];
            for (int index = 0; index < length; index++) {
                Field srcField = declaredFields[index];
                srcField.setAccessible(true);
                declaredFieldNameArr[index] = srcField.getName();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("getObjectAllFields error : " + e.getCause().getMessage());
        }
        return declaredFieldNameArr;
    }

    /**
     * 将Javabean转换为Map
     *
     * @param javaBean javaBean
     * @return Map对象
     * @throws BaseException
     */
    public static Map<String, String> toMap(Object javaBean) throws BaseException {
        Map<String, String> result = new HashMap<>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw new BaseException("toMap error : " + e.getCause().getMessage());
            }
        }
        return result;
    }

    /**
     * 复制对象中的值到另一个全新对象中
     *
     * @param src
     * @param tarClazz
     * @throws Exception
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static <T> T copyObjectFieldToNewObj(Object src, Class<T> tarClazz) throws BaseException {
        T t = null;
        if (isBlankObject(src)) {
            throw new BaseException("Object src is null, please check them.");
        }
        try {
            t = tarClazz.newInstance();
            copyObjectFieldContainsFather(src, t);
            return t;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BaseException("copyObjectFieldToNewObj error : " + e.getCause().getMessage());
        }
    }

    public static boolean equals(Object object1, Object object2) {
        return object1 == object2 ? true : (object1 != null && object2 != null ? object1.equals(object2) : false);
    }
}
