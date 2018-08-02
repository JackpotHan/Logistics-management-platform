package com.xiaohan.core.entity.system;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description:角色
 */
@Table(name = "T_ROLE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @Column(name = "C_ID")
    private int id;
    @Column(name = "C_NAME")
    private String name; // 角色名称
    @Column(name = "C_KEYWORD")
    private String keyword; // 角色关键字，用于权限控制
    @Column(name = "C_DESCRIPTION")
    private String description; // 描述

}
