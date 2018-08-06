package com.xiaohan.core.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission extends BaseObject {
    @Id
    @Column(name = "user_id")
    private Integer userId;//用户ID

    @Column(name = "permission_id")
    private Integer permissionId;//权限ID

}

