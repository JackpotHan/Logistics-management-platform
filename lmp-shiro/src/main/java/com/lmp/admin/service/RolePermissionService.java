package com.lmp.admin.service;

import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseEnum;
import com.jackpot.base.base.BaseException;
import com.lmp.admin.dto.MenuDTO;
import com.lmp.admin.dto.MenuTree;
import com.lmp.admin.dto.RolePermAssignDTO;
import com.lmp.admin.mapper.RolePermissionMapper;
import com.lmp.admin.model.Permission;
import com.lmp.admin.model.RolePermission;
import com.lmp.admin.model.User;
import com.lmp.admin.model.UserRole;
import com.lmp.admin.util.DateUtil;
import com.lmp.admin.util.ParamEmptyUtil;
import com.lmp.admin.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 角色资源服务
 * @date 2019/1/21 16:30
 */
@Service
public class RolePermissionService {

    @Resource
    private RolePermissionMapper mapper;

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserRoleService spmUserRoleService;

    public List<RolePermission> findByRoles(List<String> roles) {
        Example example = new Example(RolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("roleCode", roles);
        return mapper.selectByExample(example);
    }


    @Transactional(rollbackFor = Exception.class)
    public void assignPermissions(RolePermAssignDTO assignDTO) {
        ParamEmptyUtil.validate(assignDTO.getRoleCode());
        RolePermission param = new RolePermission();
        param.setRoleCode(assignDTO.getRoleCode());
        mapper.delete(param);
        if (StringUtil.isEmpty(assignDTO.getPerIds())) return;
        List<Permission> permissions = permissionService.queryByIds(assignDTO.getPerIds());
        if (CollectionUtils.isEmpty(permissions)) {
            throw new BaseException(BaseCode.PER_NO_ERR);
        }
        List<RolePermission> collect = permissions.stream().map(p -> {
            RolePermission permission = new RolePermission();
            permission.setRoleCode(assignDTO.getRoleCode());
            permission.setPerCode(p.getPerCode());
            permission.setCreateTime(DateUtil.now());
            return permission;
        }).collect(Collectors.toList());
        mapper.insertList(collect);
    }

    public List<MenuTree> queryPerTree(String roleCode) {
        ParamEmptyUtil.validate(roleCode);
        Permission permission = new Permission();
        permission.setStatus(BaseEnum.ENABLED.getCode());
        permission.setPerType(BaseEnum.CATALOG.getCode());
        //目录 资源列表
        List<Permission> catalogs = permissionService.queryPermissions(permission);
        List<MenuTree> trees = null;
        if (!CollectionUtils.isEmpty(catalogs)) {
            trees = catalogs.stream().map(c -> {
                MenuTree menuTree = queryChildren(c, roleCode);
                return menuTree;
            }).collect(Collectors.toList());
        }
        return trees;
    }

    public List<MenuDTO> queryMenu(User spmUser) {
        Integer userId = spmUser.getId();
        if (userId == null) {
            throw new BaseException(BaseCode.UNAUTHORIZED_ERR);
        }
        List<MenuDTO> menuList = new ArrayList<>();
        List<UserRole> roles = spmUserRoleService.findByUserId(Long.valueOf(userId));
        if (!CollectionUtils.isEmpty(roles)) {
            List<String> roleCodes = roles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
            List<RolePermission> permissions = findByRoles(roleCodes);
            if (!CollectionUtils.isEmpty(permissions)) {

                List<String> perCodes = permissions.stream().map(RolePermission::getPerCode).distinct().collect(Collectors.toList());

                for (String perCode : perCodes) {
                    Permission param = new Permission();
                    param.setPerCode(perCode);
                    param.setPerType(BaseEnum.CATALOG.getCode());
                    param.setStatus(BaseEnum.ENABLED.getCode());
                    //查询目录
                    List<Permission> catalogs = permissionService.queryPermissions(param);
                    if (!CollectionUtils.isEmpty(catalogs)) {
                        Permission permission = catalogs.get(0);
                        MenuDTO menuDTO = new MenuDTO();
                        menuDTO.setPerName(permission.getPerName());
                        menuDTO.setPerUrl(permission.getPerUrl());
                        //查询目录下的菜单
                        param.setPerType(BaseEnum.MENU.getCode());
                        param.setPerCode(null);
                        param.setParentId(permission.getId());
                        List<Permission> meList = permissionService.queryPermissions(param);
                        if (!CollectionUtils.isEmpty(meList)) {
                            List<MenuDTO> children = new ArrayList<>();
                            for (Permission per : meList) {
                                if (permissions.stream().anyMatch(p -> p.getPerCode().equals(per.getPerCode()))) {
                                    MenuDTO menu = new MenuDTO();
                                    menu.setPerName(per.getPerName());
                                    menu.setPerUrl(per.getPerUrl());
                                    children.add(menu);
                                }
                            }
                            menuDTO.setChildren(children.isEmpty() ? null : children);
                        }
                        menuList.add(menuDTO);
                    }

                }

            }
        }
        return menuList;
    }

    /**
     * 组装
     *
     * @param permission
     * @param roleCode
     * @return
     */
    public MenuTree queryChildren(Permission permission, String roleCode) {
        MenuTree menuTree = new MenuTree();
        menuTree.setId(permission.getId());
        menuTree.setTitle(permission.getPerName());


        RolePermission rpParam = new RolePermission();
        rpParam.setRoleCode(roleCode);
        rpParam.setPerCode(permission.getPerCode());
        //是否已经含有该资源
        RolePermission rolePermission = mapper.selectOne(rpParam);
        menuTree.setChecked(rolePermission != null);
        //查询子节点
        List<MenuTree> childTree = null;
        Permission cParam = new Permission();
        cParam.setParentId(permission.getId());
        List<Permission> cPermissions = permissionService.queryPermissions(cParam);
        if (!CollectionUtils.isEmpty(cPermissions)) {
            childTree = cPermissions.stream().map(p -> {
                MenuTree children = queryChildren(p, roleCode);
                return children;
            }).collect(Collectors.toList());
        }
        menuTree.setChildren(childTree);
        return menuTree;
    }
}
