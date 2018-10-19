package com.jackpotHan.web.service;

import com.google.common.collect.Lists;
import com.jackpotHan.entity.system.Role;
import com.jackpotHan.entity.system.UserRole;
import com.jackpotHan.web.mapper.UserRoleMapper;
import com.jackpotHan.web.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleService {
	@Resource private UserRoleMapper mapper;

	@Resource private RoleService roleService;

	public Integer addUserRole(UserRole userRole) {
		return mapper.insertSelective(userRole);
	}

	public Integer modifyUserRole(UserRole userRole, String... fieldStrs) {
		Example example = MapperUtil.generateExample(userRole, fieldStrs);
		return mapper.updateByExampleSelective(userRole, example);
	}

	public UserRole getUserRole(UserRole userRole) {
		return mapper.selectOne(userRole);
	}

	public List<UserRole> getUserRoles(UserRole userRole) {
		return mapper.select(userRole);
	}

	public List<Role> query(UserRole userRole) {
		List<UserRole> userRoleKeys = getUserRoles(userRole);
		if (userRoleKeys.isEmpty()) {
			return Lists.newArrayList();
		}
		Role role = new Role();
		role.setStatus(userRole.getRoleStatus());
		for (UserRole userRoleKey : userRoleKeys) {
			role.getRoleIds().add(userRoleKey.getRoleId());
		}
		return roleService.getRoleList(role);
	}

	@Transactional(readOnly = false)
	public void assignRole(Integer userId, List<Integer> roleIds) {
		Example example = new Example(UserRole.class);
		example.createCriteria().andEqualTo("userId",userId);
		mapper.deleteByExample(example);
		UserRole userRoleKey;
		for (Integer roleId : roleIds) {
			userRoleKey = new UserRole();
			userRoleKey.setUserId(userId);
			userRoleKey.setRoleId(roleId);
			mapper.insertSelective(userRoleKey);
		}
	}
}

