/*
 * Copyright (c) 2017, chaney  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.xiaohan.core.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAuth extends BaseObject {

    private Integer id;

    private String accountName;

    private String email;

    private String mobile;

    private String roleName;

    private String roleCode;

    private String permissionName;

    private String permissionValue;

    private Integer permissionType;

    private Integer permissionLevel;

    private String permissionCategory;

}