package com.lmp.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseException;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 参数空校验工具
 * @date 2019/1/17 15:17
 */
public class ParamEmptyUtil {

    /**
     * 参数空校验 返回 boolean
     *
     * @param objs
     * @param <T>
     * @return
     */
    public static <T> boolean check(T... objs) {
        if (Objects.isNull(objs) || objs.length == 0) {
            return true;
        }
        for (Object obj : objs) {
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).isEmpty();
            } else if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            } else if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            } else if (obj == null || obj.toString().trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 参数空校验，直接抛出参数缺少异常
     *
     * @param objs
     * @param <T>
     */
    public static <T> void validate(T... objs) {
        if (check(objs)) {
            throw new BaseException(BaseCode.PARAM_MISS);
        }
    }

}
