package com.jackpotHan.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description:
 */
public class BaseObject implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
