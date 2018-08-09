package com.xiaohan.web.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xiaohan.base.BaseUtil;
import com.xiaohan.base.Pageable;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.enums.Shiro.StatusEnum;
import com.xiaohan.web.dto.PermissionDTO;
import com.xiaohan.web.mapper.PermissionMapper;
import com.xiaohan.web.utils.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PermissionService {

	@Resource private PermissionMapper mapper;

	public Integer addPermission(Permission permission) {
		return mapper.insertSelective(permission);
	}

	public Integer modifyPermission(Permission permission, String... fieldStrs) {
		Example example = MapperUtil.generateExample(permission, fieldStrs);
		return mapper.updateByExampleSelective(permission, example);
	}

	public Permission getPermission(Permission permission) {
		return mapper.selectOne(permission);
	}

	public List<Permission> getPermissions(Permission permission) {
		return mapper.select(permission);
	}

	public List<Permission> getUserPermissions(Integer userId,Integer type){
		List<Permission> permissions;
		if(BaseUtil.isNotEmpty(type)){
			permissions =mapper.selectUserMenuPermission(userId, StatusEnum.ENABLED.getCode(), StatusEnum.ENABLED.getCode(), type);
		}else {
			permissions = mapper.selectUserPermission(userId, StatusEnum.ENABLED.getCode(),StatusEnum.ENABLED.getCode());
		}
		return permissions;
	}

	public List<PermissionDTO> getPermissionVO(Integer userId){
        return mapper.getPermissionDTOs(userId);
	}

	public Map<String, List<Permission>> searchCatagoryPermissions() {
		Map<String, List<Permission>> permissionCatagoryMap = new HashMap<>();
		try {
			Example example = new Example(Permission.class);
			List<Permission> permissions = mapper.selectByExample(example);
			for (Permission permission : permissions) {
				String category = permission.getCategory();
				List<Permission> permissionList = permissionCatagoryMap.get(category);
				if(BaseUtil.isEmpty(permissionList)){
					permissionList = Lists.newArrayList();
					permissionCatagoryMap.put(category, permissionList);
				}
				permissionList.add(permission);
			}
		} catch (RuntimeException e) {
			if (log.isErrorEnabled()) {
				log.error("【事务】查询权限列表异常:{}", e);
			}
		}
		return permissionCatagoryMap;
	}

	public List<Permission> query(Permission permission) {
		return mapper.selectByExample(permission.getPermissionCriteria());
	}

	public PageInfo<Permission> search(Permission permission, Pageable pageable){
		Example example = permission.getPermissionCriteria();
		PageHelper.startPage(pageable.getPageNum(),pageable.getPageSize());
		List<Permission> permissions = mapper.selectByExample(example);
		return new PageInfo<>(permissions);
	}
}

