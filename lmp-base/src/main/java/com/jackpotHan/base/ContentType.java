package com.jackpotHan.base;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:44
 * @Description:
 */
public enum ContentType {
    JSON("application/json"),
    XML("text/xml");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}