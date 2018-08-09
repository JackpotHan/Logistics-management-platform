package com.xiaohan.web.service;


import com.google.common.collect.Lists;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.entity.system.RolePermission;
import com.xiaohan.web.mapper.RolePermissionMapper;
import com.xiaohan.web.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolePermissionService {
	@Resource private RolePermissionMapper mapper;
	@Resource private PermissionService permissionService;

	//给角色分配权限
	public Integer addRolePermission(RolePermission rolePermission) {
		return mapper.insertSelective(rolePermission);
	}

	//更新用户权限
	public Integer modifyRolePermission(RolePermission rolePermission, String... fieldStrs) {
		Example example = MapperUtil.generateExample(rolePermission, fieldStrs);
		return mapper.updateByExampleSelective(rolePermission, example);
	}

	//获取用户权限
	public RolePermission getRolePermission(RolePermission rolePermission) {
		return mapper.selectOne(rolePermission);
	}
	public List<RolePermission> getRolePermissions(RolePermission rolePermission) {
		return mapper.select(rolePermission);
	}

	//查询并展示所有的权限
	public List<Permission> query(RolePermission rolePermission) {
		List<RolePermission> rolePermissionKeys = mapper.selectByExample(rolePermission.getRolePermissionCriteria());
		if (rolePermissionKeys.isEmpty()) {
			return Lists.newArrayList();
		}
		Permission permission = new Permission();
		permission.setStatus(rolePermission.getPermissionStatus());
		permission.setType(rolePermission.getPermissionType());
		for (RolePermission rolePermissionKey : rolePermissionKeys) {
			permission.getPermissionIds().add(rolePermissionKey.getPermissionId());
		}
		return permissionService.query(permission);
	}

	//分配权限，及删除
	@Transactional(readOnly = false)
	public Boolean assignPermission(Integer roleId, Integer permissionId, boolean checked) {
		if (checked) {
			Example rolePermissionCriteria = new Example(RolePermission.class);
			rolePermissionCriteria.createCriteria().andEqualTo("roleId",roleId).andEqualTo("permissionId",permissionId);
			List<RolePermission> rolePermissionKeys =
					mapper.selectByExample(rolePermissionCriteria);
			if (rolePermissionKeys.isEmpty()) {
				RolePermission rolePermissionKey = new RolePermission();
				rolePermissionKey.setPermissionId(permissionId);
				rolePermissionKey.setRoleId(roleId);
				Integer c = addRolePermission(rolePermissionKey);
				return c == 1 ? Boolean.TRUE : Boolean.FALSE;
			}
		} else {
			Example example = new Example(RolePermission.class);
			example.createCriteria().andEqualTo("roleId",roleId).andEqualTo("permissionId",permissionId);
			mapper.deleteByExample(example);
		}
		return Boolean.TRUE;
	}
}

