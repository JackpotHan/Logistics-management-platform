package com.xiaohan.system.controller;

import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.entity.system.RolePermission;
import com.xiaohan.system.shiro.SimpleShiroManage;
import com.xiaohan.web.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "rolePermission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RolePermissionController extends SimpleShiroManage {

    @Resource private RolePermissionService rolePermissionService;

    private static Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

    private final static String ASSIGN_ROLE_PERMISSION_SUCCESS = "分配角色权限成功";

    private final static String UNKNOWN_EXCEPTION = "未知异常";

    /**
     * Reason: 查询角色有效权限
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public BaseResult query(RolePermission rolePermissionQuery, BindingResult bindingResult) {
        List<Permission> permissions = rolePermissionService.query(rolePermissionQuery);
        return new BaseResult(permissions);
    }

    /**
     * Reason: 角色授权限.
     */
    @RequestMapping(value = "/assignPermission", method = RequestMethod.GET)
    public BaseResult assignPermission(@RequestParam("roleId") Integer roleId,
                                   @RequestParam("permissionId") Integer permissionId, @RequestParam("checked") boolean checked) {

        Boolean state = rolePermissionService.assignPermission(roleId, permissionId, checked);
        return state.compareTo(Boolean.TRUE) == 0 ?
                new BaseResult(BaseCode.OK.getCode(), ASSIGN_ROLE_PERMISSION_SUCCESS) :
                new BaseResult(BaseCode.FAIL.getCode(), UNKNOWN_EXCEPTION);
    }

}
