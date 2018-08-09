package com.xiaohan.system.cache;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author: Hanjt
 * @Date: 2018/8/7 14:29
 * @Description:
 */
public class SimpleCacheManage {

    /**
     * 方法缓存路径
     */
    public static Map<String, String> methodMap = Maps.newConcurrentMap();

}
