package com.xiaohan.entity.system;

import com.google.common.collect.Lists;
import com.xiaohan.base.BaseObject;
import com.xiaohan.base.BaseUtil;
import lombok.*;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

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

	@Transient
	private List<Integer> roleIds = Lists.newArrayList();

	@Transient
	private Integer permissionStatus;

	@Transient
	private Integer permissionType;

	/**
	 * Reason: 根据数据组装成Criteria对象.
	 *
	 * @return
	 */
	public Example getRolePermissionCriteria() {
		Example example = new Example(RolePermission.class);
		Example.Criteria criteria = example.createCriteria();
		if (BaseUtil.isNotEmpty(roleId)) {
			criteria.andEqualTo("roleId",roleId);
		}
		if (BaseUtil.isNotEmpty(roleIds)) {
			criteria.andIn("roleId",roleIds);
		}
		return example;
	}


}

