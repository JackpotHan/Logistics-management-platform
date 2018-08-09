package com.xiaohan.system.controller;

import com.google.common.collect.Lists;
import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import com.xiaohan.base.BaseUtil;
import com.xiaohan.entity.system.Role;
import com.xiaohan.entity.system.UserRole;
import com.xiaohan.web.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "userRole",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class UserRoleController {

    private final static String ASSIGN_USER_ROLE_SUCCESS = "分配用户角色成功";

    @Resource
    private UserRoleService userRoleService;

    /**
     * Reason: 查询用户有效角色
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public BaseResult query(UserRole userRoleQuery) {
        List<Role> roles = userRoleService.query(userRoleQuery);
        return new BaseResult(roles);
    }

    /**
     * Reason: 分配用户角色.
     */
    @RequestMapping(value = "/assignRole", method = RequestMethod.GET)
    public BaseResult assignRole(@RequestParam("userId") Integer userId,
                             @RequestParam(value = "roles", required = false) List<Integer> roles) {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗查询角色列表:userId:{},roles{}", userId,roles);
        }
        if (BaseUtil.isEmpty(roles)) {
            roles = Lists.newArrayList();
        }
        userRoleService.assignRole(userId, roles);
        return new BaseResult(BaseCode.OK.getCode());
    }
}
