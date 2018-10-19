package com.jackpotHan.system.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.core.BaseController;
import com.jackpotHan.entity.system.Permission;
import com.jackpotHan.entity.system.UserPermission;
import com.jackpotHan.enums.Shiro.CategoryEnum;
import com.jackpotHan.enums.Shiro.TypeEnum;
import com.jackpotHan.web.dto.PermissionDTO;
import com.jackpotHan.web.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "userPermission")
@Slf4j
public class UserPermissionController extends BaseController {

    @Resource
    private PermissionService permissionService;

    /**
     * Reason: 查询用户菜单权限
     */
    @RequestMapping(value = "/queryMenu", method = RequestMethod.GET)
    public BaseResult queryMenu(String userId) {
        //角色关联的菜单权限
        List<Permission> rolePermissions = permissionService.getUserPermissions(new Integer(userId), TypeEnum.MENU_CONTROL.getCode());
        if(CollectionUtils.isEmpty(rolePermissions)) rolePermissions = Lists.newArrayList();
        Map<String, Permission> decrease = Maps.newHashMap();
        for (Permission permission : rolePermissions) {
            if (!decrease.containsKey(permission.getName())) {
                decrease.put(permission.getName(), permission);
            }
        }

        //树结构组装
        Map<String, List> root = Maps.newHashMap();
        List<Permission> permissionList;
        for (Map.Entry<String, Permission> entry : decrease.entrySet()) {
            Permission permission = entry.getValue();
            if (root.containsKey(permission.getCategory())) {
                root.get(permission.getCategory()).add(permission);
            } else {
                permissionList = Lists.newArrayList();
                permissionList.add(permission);
                root.put(permission.getCategory(), permissionList);
            }
        }

        List result = Lists.newArrayList();
        Map<String, Object> catg;
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            catg = Maps.newHashMap();
            catg.put("category", categoryEnum.getValue());
            catg.put("icon", categoryEnum.getIcon());
            List<Permission> subs = root.get(categoryEnum.getValue());
            // object is null continue.
            if (Objects.isNull(subs)) {
                continue;
            }
            Collections.sort(subs, new Comparator<Permission>() {

                public int compare(Permission arg0, Permission arg1) {
                    return arg0.getSort().compareTo(arg1.getSort());
                }
            });
            catg.put("sub", subs);
            result.add(catg);
        }

        return new BaseResult(result);
    }

    /**
     * Reason: 查询本人有效权限
     */
//    @BindingDetection(name = "查询用户权限", javaType = UserPermissionForm.Query.class)
    @RequestMapping(value = "/queryPrinciple", method = RequestMethod.GET)
    public BaseResult queryPrinciple(UserPermission userPermission) {
        //角色关联的菜单权限
        validateParams(userPermission,"userId","operatorId");
        log.info("查询用户权限:userId:{},operatorId:{}",userPermission.getUserId(),userPermission.getOperatorId());
        Integer userId = (BaseUtil.isNotEmpty(userPermission.getOperatorId()) && BaseUtil.isNotEmpty(userPermission.getUserId()))?
                new Integer(userPermission.getOperatorId()) : userPermission.getUserId();
        List<Permission> rolePermissions = permissionService.getUserPermissions(userId,null);
        if(CollectionUtils.isEmpty(rolePermissions)) rolePermissions = Lists.newArrayList();
        // 将权限名称以键值对的形式存入map
        Map<Integer, Permission> decrease = Maps.newHashMap();
        for (Permission permission : rolePermissions) {
            if (!decrease.containsKey(permission.getId())) {
                decrease.put(permission.getId(), permission);
            }
        }
        //将map存入list中
        List<Permission> perms = Lists.newArrayList();
        for (Map.Entry<Integer, Permission> entry : decrease.entrySet()) {
            Permission permission = entry.getValue();
            perms.add(permission);
        }
        return new BaseResult(perms);
    }

    /**
     * 用户登录后查询所有权限
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryUserPrinciple", method = RequestMethod.GET)
    public BaseResult queryUserPrinciple(String userId) {
        //角色关联的菜单权限
        List<Permission> rolePermissions = permissionService.getUserPermissions(new Integer(userId),null);
        if(CollectionUtils.isEmpty(rolePermissions)) rolePermissions = Lists.newArrayList();
        // 将权限名称以键值对的形式存入map
        Map<Integer, Permission> decrease = Maps.newHashMap();
        for (Permission permission : rolePermissions) {
            if (!decrease.containsKey(permission.getId())) {
                decrease.put(permission.getId(), permission);
            }
        }
        //将map存入list中
        List<Permission> perms = Lists.newArrayList();
        for (Map.Entry<Integer, Permission> entry : decrease.entrySet()) {
            Permission permission = entry.getValue();
            perms.add(permission);
        }
        return new BaseResult(perms);
    }


    /**
     * 用户权限列表,有选择类型标志位。
     */
    @RequestMapping(value = "/relateQuery", method = RequestMethod.GET)
    public BaseResult relateQuery(String userId) {
        List<PermissionDTO> permissions = permissionService.getPermissionVO(new Integer(userId));
        return new BaseResult(permissions);
    }
}
