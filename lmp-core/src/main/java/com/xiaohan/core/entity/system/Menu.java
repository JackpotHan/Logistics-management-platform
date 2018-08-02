package com.xiaohan.core.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description:菜单
 */
@Entity
@Table(name = "T_MENU")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseObject {
    @Id
    @Column(name = "C_ID")
    private int id;
    @Column(name = "C_NAME")
    private String name; // 菜单名称
    @Column(name = "C_PAGE")
    private String page; // 访问路径
    @Column(name = "C_PRIORITY")
    private Integer priority; // 优先级
    @Column(name = "C_DESCRIPTION")
    private String description; // 描述

}
