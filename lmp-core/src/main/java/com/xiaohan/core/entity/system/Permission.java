package com.xiaohan.core.entity.system;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description:权限名称
 */
@Table(name = "T_PERMISSION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission{

    @Id
    @Column(name = "C_ID")
    private int id;
    @Column(name = "C_NAME")
    private String name; // 权限名称
    @Column(name = "C_KEYWORD")
    private String keyword; // 权限关键字，用于权限控制
    @Column(name = "C_DESCRIPTION")
    private String description; // 描述

}
