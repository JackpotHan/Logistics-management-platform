package com.jackpotHan.system.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.entity.system.User;
import com.jackpotHan.entity.system.UserAuth;
import com.jackpotHan.enums.StatusEnum;
import com.jackpotHan.web.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/***
 * 继承关系：Realm->CachingRealm->AuthenticatingRealm->AuthorizingRealm
 * AuthenticatingRealm  认证
 * AuthorizingRealm  授权
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String password = token.getPassword().toString();
        BaseResult<?> result = JSONObject.parseObject(password, BaseResult.class);
        Assert.isTrue(StringUtils.equals(result.getRespCode().toString(), StatusEnum.SUCCESS.getCode().toString()), result.getRespDesc());
        User user = new User();
        try {
            BeanUtils.populate(user, (Map) result.getRespData());
        } catch (IllegalAccessException e) {
            if (log.isErrorEnabled()) {
                log.error("『异常』授权数据解析异常:", e);
            }
            Assert.isTrue(false, "授权数据解析错误");
        } catch (InvocationTargetException e) {
            if (log.isErrorEnabled()) {
                log.error("『异常』授权数据解析异常:", e);
            }
            Assert.isTrue(false, "授权数据解析错误");
        }
        return new SimpleAuthenticationInfo(user, null, "default");
    }

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        BaseResult<List<UserAuth>> result = new BaseResult(userAuthService.selectUserAuth(user.getId()));
        Assert.isTrue(StringUtils.equals(result.getRespCode().toString(), StatusEnum.SUCCESS.getCode().toString()), result.getRespDesc());
        List<UserAuth> userAuths = Lists.newArrayList();
        List<UserAuth> list = JSON.parseObject(JSON.toJSONString(result.getRespData()),List.class);
        try {
            for (UserAuth userAuth1 : list) {
                userAuths.add(userAuth1);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("『异常』权限数据解析异常:", e);
            }
            Assert.isTrue(false, "权限数据解析错误");
        }
        for (UserAuth userAuth : userAuths) {
            info.addRole(userAuth.getRoleCode());
            info.addStringPermission(userAuth.getPermissionValue());
        }
        return info;
    }

}
