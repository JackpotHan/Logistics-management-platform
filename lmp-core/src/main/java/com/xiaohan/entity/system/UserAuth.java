package com.xiaohan.entity.system;

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