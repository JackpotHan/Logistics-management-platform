package com.jackpotHan.system.controller;

import com.google.common.collect.Lists;
import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.entity.system.Role;
import com.jackpotHan.entity.system.UserRole;
import com.jackpotHan.web.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
