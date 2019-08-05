package com.lmp.admin.controller;

import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseResult;
import com.lmp.admin.service.RolePermissionService;
import com.lmp.admin.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 菜单控制器
 * @date 2019/1/22 10:48
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 菜单
     *
     * @return
     */
    @PostMapping("/list")
    //@CrossOrigin("*")
    public BaseResult userMenu() {
        return new BaseResult(BaseCode.OK,rolePermissionService.queryMenu(SessionUserUtil.currentUser()));
    }


}
