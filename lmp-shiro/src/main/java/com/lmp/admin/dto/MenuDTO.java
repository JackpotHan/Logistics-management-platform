package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuDTO implements Serializable {
    /**
     * 名称
     */
    private String perName;
    /**
     * url
     */
    private String perUrl;

    /**
     * icon
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<MenuDTO> children;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
