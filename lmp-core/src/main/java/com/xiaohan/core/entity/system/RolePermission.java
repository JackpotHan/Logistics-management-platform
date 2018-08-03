package com.xiaohan.core.entity.system;

import com.xiaohan.base.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema = "`system`",name = "role_permission")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission extends BaseObject {
	@Id
	@Column(name = "role_id")
	private Integer roleId;//角色ID

	@Column(name = "permission_id")
	private Integer permissionId;//权限ID

}

