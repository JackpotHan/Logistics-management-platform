package com.lmp.admin.shiro.realm;


import com.lmp.admin.model.RolePermission;
import com.lmp.admin.model.User;
import com.lmp.admin.model.UserRole;
import com.lmp.admin.service.RolePermissionService;
import com.lmp.admin.service.UserRoleService;
import com.lmp.admin.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 验证域
 * @date 2019/1/14 17:50
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService spmUserRoleService;
    @Resource
    private RolePermissionService RolePermissionService;


    /**
     * 权限校验
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo = (User) principals.getPrimaryPrincipal();
        List<UserRole> roles = spmUserRoleService.findByUserId(Long.valueOf(userInfo.getId()));
        if(!CollectionUtils.isEmpty(roles)){
           //角色
            List<String> roleCodes = roles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
            authorizationInfo.addRoles(roleCodes);
            List<RolePermission> pers = RolePermissionService.findByRoles(roleCodes);
           //资源
            if(!CollectionUtils.isEmpty(pers)){
                List<String> perCodes = pers.stream().map(RolePermission::getPerCode).collect(Collectors.toList());
                authorizationInfo.addStringPermissions(perCodes);
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份认证，验证用户输入的账号和密码是否正确
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        String username = (String) token.getPrincipal();
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new AuthenticationException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
        return authenticationInfo;
    }

}
