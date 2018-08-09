package com.xiaohan.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import com.xiaohan.base.Pageable;
import com.xiaohan.entity.system.Permission;
import com.xiaohan.preference.CategoryPreference;
import com.xiaohan.preference.PermissionPreference;
import com.xiaohan.utils.DateUtil;
import com.xiaohan.web.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class PermissionController {

    private final static String CREATE_PERMISSION_SUCCESS = "创建权限成功";

    private final static String UPDATE_PERMISSION_SUCCESS = "更新权限成功";

    private final static String UNKNOWN_EXCEPTION = "未知异常";

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public BaseResult query(Permission permission) {
        List<Permission> permissions = permissionService.getPermissions(permission);
        return new BaseResult(permissions);
    }

    /**
     * Reason: 查询所有类别权限
     */
    @RequestMapping(value = "/searchCatagoryPermissions", method = RequestMethod.GET)
    public BaseResult searchCatagoryPermissions() {
        Map<String, List<Permission>> permissionCatagoryMap = permissionService.searchCatagoryPermissions();
        return new BaseResult(permissionCatagoryMap);
    }

    /**
     * Reason: 权限首选项.
     */
    @RequestMapping(value = "/getPreferences", method = RequestMethod.GET)
    public BaseResult getPreferences() {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗权限首选项");
        }
        Map map = Maps.newHashMap();
        map.put("permissionStatusPreferences", new PermissionPreference().new PermissionStatus().preferences());
        map.put("permissionTypePreferences", new PermissionPreference().new PermissionType().preferences());
        map.put("permissionLevelPreferences", new PermissionPreference().new PermissionLevel().preferences());
        map.put("categoryPreferences", new CategoryPreference().new Category().preferences());
        log.info(JSON.toJSONString(map));
        return new BaseResult(map);
    }

    /**
     * Reason: 权限列表查询.分页
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public BaseResult search(Permission permission, Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗查询权限列表:query:{}", permission);
        }
        PageInfo<Permission> page = permissionService.search(permission,pageable);
        return new BaseResult(page);
    }


    /**
     * Reason: 新增权限信息.
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(Permission permission,String operator) {
        permission.setGmtOperate(DateUtil.getCurrentDate());
        permission.setOperator(operator);
        Integer state = permissionService.addPermission(permission);
        return state == 1 ?
                new BaseResult(BaseCode.OK.getCode(),CREATE_PERMISSION_SUCCESS) :
                new BaseResult(BaseCode.FAIL.getCode(),UNKNOWN_EXCEPTION);
    }

    /**
     * Reason: 更新权限信息.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(Permission permission,String operator) {
        permission.setGmtOperate(DateUtil.getCurrentDate());
        permission.setOperator(operator);
        Integer state = permissionService.modifyPermission(permission,"id");
        return state > 0 ?
                new BaseResult(BaseCode.OK.getCode(),UPDATE_PERMISSION_SUCCESS) :
                new BaseResult(BaseCode.FAIL.getCode(),UNKNOWN_EXCEPTION);
    }
}
