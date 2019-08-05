package com.lmp.admin.service;

import com.lmp.admin.dto.UserRoleAssignDTO;
import com.lmp.admin.dto.UserRoleDTO;
import com.lmp.admin.mapper.UserRoleMapper;
import com.lmp.admin.model.Role;
import com.lmp.admin.model.UserRole;
import com.lmp.admin.util.DateUtil;
import com.lmp.admin.util.ParamEmptyUtil;
import com.lmp.admin.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description: 用户角色服务
 * @date 2019/1/21 16:23
 */
@Service
public class UserRoleService {
    @Resource
    private RoleService operationRoleService;

    @Resource
    private UserRoleMapper mapper;

    public List<UserRole> findByUserId(Long userId) {
        UserRole param = new UserRole();
        param.setUserId(userId);
        return mapper.select(param);
    }

    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(UserRoleAssignDTO assignDTO) {
        ParamEmptyUtil.validate(assignDTO.getUserId());
        UserRole dParam = new UserRole();
        dParam.setUserId(assignDTO.getUserId());
        mapper.delete(dParam);
        if(StringUtil.isEmpty(assignDTO.getRoles())) return;
        List<UserRole> SpmUserRoles = assignDTO.getRoles().stream().map(r -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(assignDTO.getUserId());
            userRole.setRoleCode(r);
            userRole.setCreateTime(DateUtil.now());
            return userRole;
        }).collect(Collectors.toList());
        mapper.insertList(SpmUserRoles);
    }

    public List<UserRoleDTO> findRolesByUserId(Long userId) {
        ParamEmptyUtil.validate(userId);
        List<UserRole> byUserId = findByUserId(userId);
        if (!CollectionUtils.isEmpty(byUserId)) {
            List<UserRoleDTO> userRoleDTOList = byUserId.stream().map(u -> {
                Role param = new Role();
                param.setRoleCode(u.getRoleCode());
                Role query = operationRoleService.query(param);
                UserRoleDTO roleDTO = new UserRoleDTO();
                roleDTO.setRoleCode(u.getRoleCode());
                roleDTO.setRoleDesc(query.getRoleDesc());
                roleDTO.setRoleName(query.getRoleName());
                roleDTO.setRoleId(query.getId());
                return roleDTO;
            }).collect(Collectors.toList());
            return userRoleDTOList;
        }

        return null;
    }

    public boolean judegeHasSuperAdmin(Long userId){
        UserRole userRole = new UserRole();
        userRole.setRoleCode("superAdmin");
        userRole.setUserId(userId);
        return StringUtil.isEmpty(mapper.selectOne(userRole)) ? false : true;
    }

    public void deleteByUserId(Integer userId) {
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("userId", userId);
        mapper.deleteByExample(example);
    }
}
