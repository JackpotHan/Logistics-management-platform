package com.lmp.admin.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class MenuTree implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标签
     */
    private String title;

    /**
     * 是否被选中
     */
    private boolean checked = false;

    /**
     * 子树
     */
    private List<MenuTree> children;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
