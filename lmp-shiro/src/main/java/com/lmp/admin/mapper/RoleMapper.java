package com.lmp.admin.mapper;

import com.lmp.admin.dto.RoleDTO;
import com.lmp.admin.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends MyMapper<Role> {
    List<RoleDTO> selectUserRole(Integer userId);
}