package com.xiaohan.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xiaohan.base.BaseCode;
import com.xiaohan.base.BaseResult;
import com.xiaohan.base.Pageable;
import com.xiaohan.entity.system.Role;
import com.xiaohan.preference.RolePreference;
import com.xiaohan.utils.DateUtil;
import com.xiaohan.web.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/role")
@Slf4j
public class RoleController {

    private final static String CREATE_ROLE_SUCCESS = "创建角色成功";

    private final static String UPDATE_ROLE_SUCCESS = "更新角色成功";

    private final static String UNKNOWN_EXCEPTION = "未知异常";

    @Resource private RoleService roleService;

    /**
     * Reason: 角色列表查询.分页
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public BaseResult search(Role role, Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗查询角色列表:query:{}", role,pageable);
        }
        PageInfo<Role> rolePageInfo = roleService.getPageInfo(role,pageable);
        return new BaseResult(rolePageInfo);
    }

    /**
     * Reason: 角色首选项.
     */
    @RequestMapping(value = "/getPreferences", method = RequestMethod.GET)
    public BaseResult getPreferences() {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗角色首选项");
        }
        Map map = Maps.newHashMap();
        map.put("roleStatusPreferences", new RolePreference().new RoleStatus().preferences());
        log.info(JSON.toJSONString(map));
        return new BaseResult(map);
    }

    /**
     * Reason: 新增角色信息.
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(Role role) {
        role.setGmtOperate(DateUtil.getCurrentDate());
        role.setOperator(role.getOperator());
        Integer a = roleService.addRole(role);
        return a > 0 ?
                new BaseResult(BaseCode.OK.getCode(),CREATE_ROLE_SUCCESS) :
                new BaseResult(BaseCode.FAIL.getCode(), UNKNOWN_EXCEPTION);
    }

    /**
     * Reason: 更新角色信息.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(Role role) {
        role.setGmtOperate(DateUtil.getCurrentDate());
        role.setOperator(role.getOperator());
        Integer state = roleService.modifyRole(role,"id");
        return state == 1 ?
                new BaseResult(BaseCode.OK.getCode(), UPDATE_ROLE_SUCCESS) :
                new BaseResult(BaseCode.FAIL.getCode(), UNKNOWN_EXCEPTION);
    }
}
