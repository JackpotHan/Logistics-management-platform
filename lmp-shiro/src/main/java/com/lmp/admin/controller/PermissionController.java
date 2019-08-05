package com.lmp.admin.controller;

import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseResult;
import com.lmp.admin.dto.PermissionDTO;
import com.lmp.admin.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 资源控制器
 * @date 2019/1/21 13:41
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加资源
     *
     * @param permissionDTO
     * @return
     */
    @RequiresPermissions("per:list:add")
    @PostMapping("/add")
    public BaseResult addPer(@RequestBody PermissionDTO permissionDTO) {
        permissionService.addPermission(permissionDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 修改资源
     *
     * @param permissionDTO
     * @return
     */
    @RequiresPermissions("per:list:edit")
    @PostMapping("/edit")
    public BaseResult editPer(@RequestBody PermissionDTO permissionDTO) {
        permissionService.editPermission(permissionDTO);
        return new BaseResult(BaseCode.OK);
    }


    /**
     * 资源列表分页
     *
     * @param permissionDTO
     * @return
     */
    @RequiresPermissions("per:list:view")
    @PostMapping("/pageList")
    public BaseResult page(@RequestBody PermissionDTO permissionDTO) {
        PageInfo<PermissionDTO> pageInfo = permissionService.queryPage(permissionDTO);
        return new BaseResult(BaseCode.OK,pageInfo);
    }

    /**
     * 查询目录列表
     *
     * @param
     * @return
     */
    @PostMapping("/catalog")
    public BaseResult getCatalog() {
        List<PermissionDTO> catalogs = permissionService.queryCatalog();
        return new BaseResult(BaseCode.OK,catalogs);
    }

    /**
     * 查询子资源
     *
     * @param permissionDTO
     * @return
     */
    @PostMapping("/child")
    public BaseResult getSub(@RequestBody PermissionDTO permissionDTO) {
        List<PermissionDTO> childList = permissionService.queryChildList(permissionDTO);
        return new BaseResult(BaseCode.OK,childList);
    }
}
