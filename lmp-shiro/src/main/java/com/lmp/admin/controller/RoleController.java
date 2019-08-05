package com.lmp.admin.controller;

import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseEnum;
import com.jackpot.base.base.BaseResult;
import com.lmp.admin.dto.MenuTree;
import com.lmp.admin.dto.RoleDTO;
import com.lmp.admin.dto.RolePermAssignDTO;
import com.lmp.admin.service.RolePermissionService;
import com.lmp.admin.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 权限控制器
 * @date 2019/1/21 9:28
 */
@RestController
@RequestMapping("/role")
public class RoleController{

    @Autowired
    private RoleService spmRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 角色列表分页
     *
     * @param roleDTO
     * @return
     */
    @RequiresPermissions("role:list:view")
    @PostMapping("/pageList")
    public BaseResult page(@RequestBody RoleDTO roleDTO) {
        PageInfo<RoleDTO> pageInfo = spmRoleService.queryPage(roleDTO);
        return new BaseResult(BaseCode.OK,pageInfo);
    }

    /**
     * 角色列表
     *
     * @return
     */
    @PostMapping("/list")
    public BaseResult getList() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setStatus(BaseEnum.ENABLED.getCode());
        List<RoleDTO> list = spmRoleService.queryDTOList(roleDTO);
        return new BaseResult(BaseCode.OK,list);
    }

    /**
     * 添加角色
     *
     * @param roleDTO
     * @return
     */
    @RequiresPermissions("role:list:view")
    @PostMapping("/add")
    public BaseResult addRole(@RequestBody RoleDTO roleDTO) {
        spmRoleService.addRole(roleDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 修改角色
     *
     * @param roleDTO
     * @return
     */
    @RequiresPermissions("role:list:edit")
    @PostMapping("/edit")
    public BaseResult editRole(@RequestBody RoleDTO roleDTO) {
        spmRoleService.editRole(roleDTO);
        return new BaseResult(BaseCode.OK);
    }


    /**
     * 资源分配
     *
     * @param assignDTO
     * @return
     */
    @RequiresPermissions("role:per:distribution")
    @PostMapping("/permission/assign")
    public BaseResult assignRoles(@RequestBody RolePermAssignDTO assignDTO) {
        rolePermissionService.assignPermissions(assignDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 查询角色下的资源树
     *
     * @param map
     * @return
     */
    @PostMapping("/permission/tree")
    public BaseResult showMenuTree(@RequestBody Map<String, String> map) {
        List<MenuTree> trees = rolePermissionService.queryPerTree(map.get("roleCode"));
        return new BaseResult(BaseCode.OK,trees);
    }
}