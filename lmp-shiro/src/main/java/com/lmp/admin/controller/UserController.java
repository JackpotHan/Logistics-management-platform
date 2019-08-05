package com.lmp.admin.controller;

import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseResult;
import com.lmp.admin.dto.UserDTO;
import com.lmp.admin.dto.UserRoleAssignDTO;
import com.lmp.admin.service.UserRoleService;
import com.lmp.admin.service.UserService;
import com.lmp.admin.util.SessionUserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: 用户控制器
 * @date 2019/1/17 15:01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 用户注册
     *
     * @param userDTO
     * @return
     */
    @RequiresPermissions("user:list:add")
    @PostMapping("/register")
    public BaseResult registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 用户重置密码
     *
     * @param userDTO
     * @return
     */
    @RequiresPermissions("user:pwd:reset")
    @PostMapping("/reset")
    public BaseResult resetPwd(@RequestBody UserDTO userDTO) {
        userService.resetPassword(userDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 用户修改
     *
     * @param userDTO
     * @return
     */
    @RequiresPermissions("user:list:edit")
    @PostMapping("/edit")
    public BaseResult editUser(@RequestBody UserDTO userDTO) {
        userDTO.setOperateUser(SessionUserUtil.currentUserName());
        userService.editUser(userDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 用户状态修改
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/status/modify")
    public BaseResult modifyStatus(@RequestBody UserDTO userDTO) {
        userService.modifyStatus(userDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 用户列表(分页)
     *
     * @param userDTO
     * @return
     */
  //  @RequiresPermissions("user:list:view")
    @PostMapping("/pageList")
    public BaseResult pageList(@RequestBody UserDTO userDTO) {
        PageInfo<UserDTO> pageInfo = userService.queryPage(userDTO);
        return new BaseResult(BaseCode.OK,pageInfo);
    }

    /**
     * 角色分配
     *
     * @param assignDTO
     * @return
     */
    @PostMapping("/role/assign")
    public BaseResult assignRoles(@RequestBody UserRoleAssignDTO assignDTO) {
        userRoleService.assignRoles(assignDTO);
        return new BaseResult(BaseCode.OK);
    }

    /**
     * 角色查看
     *
     * @param param
     * @return
     */
    @PostMapping("/role/list")
    public BaseResult roleList(@RequestBody Map<String,Long> param) {
        return new BaseResult(BaseCode.OK,userRoleService.findRolesByUserId(param.get("userId")));
    }

}