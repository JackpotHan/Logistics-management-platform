package com.jackpotHan.web.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackpotHan.base.Pageable;
import com.jackpotHan.entity.system.Role;
import com.jackpotHan.web.mapper.RoleMapper;
import com.jackpotHan.web.utils.MapperUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
	@Resource private RoleMapper mapper;

	public Integer addRole(Role role) {
		return mapper.insertSelective(role);
	}

	public Integer modifyRole(Role role, String... fieldStrs) {
		Example example = MapperUtil.generateExample(role, fieldStrs);
		return mapper.updateByExampleSelective(role, example);
	}

	public Role getRole(Role role) {
		return mapper.selectOne(role);
	}

	public List<Role> getRoles(Role role) {
		return mapper.select(role);
	}

	public List<Role> getRoleList(Role role){return mapper.selectByExample(role.getRoleCriteria());}

	public PageInfo<Role> getPageInfo(Role role, Pageable pageable){
		PageHelper.startPage(pageable.getPageNum(),pageable.getPageSize());
		return new PageInfo<Role>(mapper.selectByExample(role.getRoleCriteria()));
	}
}

