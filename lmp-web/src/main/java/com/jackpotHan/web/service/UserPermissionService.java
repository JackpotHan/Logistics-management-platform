package com.jackpotHan.web.service;


import com.jackpotHan.entity.system.UserPermission;
import com.jackpotHan.web.mapper.UserPermissionMapper;
import com.jackpotHan.web.utils.MapperUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPermissionService {
	@Resource private UserPermissionMapper mapper;

	public Integer addUserPermission(UserPermission userPermission) {
		return mapper.insertSelective(userPermission);
	}

	public Integer modifyUserPermission(UserPermission userPermission, String... fieldStrs) {
		Example example = MapperUtil.generateExample(userPermission, fieldStrs);
		return mapper.updateByExampleSelective(userPermission, example);
	}

	public UserPermission getUserPermission(UserPermission userPermission) {
		return mapper.selectOne(userPermission);
	}

	public List<UserPermission> getUserPermissions(UserPermission userPermission) {
		return mapper.select(userPermission);
	}

}

