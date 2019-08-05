package com.lmp.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseEnum;
import com.jackpot.base.base.BaseException;
import com.lmp.admin.dto.RoleDTO;
import com.lmp.admin.mapper.RoleMapper;
import com.lmp.admin.model.Role;
import com.lmp.admin.model.User;
import com.lmp.admin.util.DateUtil;
import com.lmp.admin.util.ObjectConvertUtil;
import com.lmp.admin.util.ParamEmptyUtil;
import com.lmp.admin.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色服务
 * @date 2019/1/16 15:00
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper mapper;
    @Resource
    private UserRoleService userRoleService;

    public PageInfo<RoleDTO> queryPage(RoleDTO roleDTO) {
        Role param = ObjectConvertUtil.convert(roleDTO, Role.class);
        Page<RoleDTO> page = PageHelper.startPage(roleDTO.getPageNum(), roleDTO.getPageSize());
        Example example = new Example(param.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(param.getRoleName())) {
            criteria.andLike("roleName", "%" + param.getRoleName() + "%");
        }
        if (!StringUtil.isEmpty(param.getRoleCode())) {
            criteria.andLike("roleCode", "%" + param.getRoleCode() + "%");
        }
        List<Role> roles = mapper.selectByExample(example);
        PageInfo<RoleDTO> pageInfo = page.toPageInfo();
        pageInfo.setList(ObjectConvertUtil.convertList(roles, RoleDTO.class));
        return pageInfo;
    }

    public List<RoleDTO> queryDTOList(RoleDTO roleDTO) {
        Role param = ObjectConvertUtil.convert(roleDTO, Role.class);
        List<Role> select = mapper.select(param);
        return ObjectConvertUtil.convertList(select, RoleDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDTO roleDTO) {
        ParamEmptyUtil.validate(roleDTO.getRoleCode(), roleDTO.getRoleName());
        if (checkRoleExist(roleDTO.getRoleCode())) {
            throw new BaseException(BaseCode.ROLE_CODE_EXIST_ERR);
        }
        Role convert = ObjectConvertUtil.convert(roleDTO, Role.class);
        convert.setCreateTime(DateUtil.now());
        convert.setStatus(BaseEnum.ENABLED.getCode());
        mapper.insertSelective(convert);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editRole(RoleDTO roleDTO) {
        ParamEmptyUtil.validate(roleDTO.getId());
        Role param = ObjectConvertUtil.convert(roleDTO, Role.class, "roleCode");
        param.setUpdateTime(DateUtil.now());
        mapper.updateByPrimaryKeySelective(param);
    }

    /**
     * 校验roleCode 是否存在，存在返回true
     *
     * @param roleCode
     * @return
     */
    public boolean checkRoleExist(String roleCode) {
        Role param = new Role();
        param.setRoleCode(roleCode);
        Role operationRole = mapper.selectOne(param);
        if (operationRole != null) {
            return true;
        }
        return false;
    }

    public Role query(Role role) {
        return mapper.selectOne(role);
    }

    public List<RoleDTO> selectUserRole() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (userRoleService.judegeHasSuperAdmin(user.getId().longValue())) {
            List<Role> dtos = mapper.selectAll();
            List<RoleDTO> ros = new ArrayList<>();
            RoleDTO roleDTO;
            for (Role role : dtos) {
                roleDTO = new RoleDTO();
                BeanUtils.copyProperties(role, roleDTO);
                ros.add(roleDTO);
            }
            return ros;
        }
        return mapper.selectUserRole(user.getId());
    }

    public List<RoleDTO> getUserRole(Integer id){
        return mapper.selectUserRole(id);
    }
}
