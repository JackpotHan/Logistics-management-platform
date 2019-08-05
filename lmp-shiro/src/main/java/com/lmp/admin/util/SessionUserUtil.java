package com.lmp.admin.util;

import com.lmp.admin.model.User;
import org.apache.shiro.SecurityUtils;

/**
 * @Description: 会话用户工具
 * @date 2019/1/17 15:56
 */
public class SessionUserUtil {

    /**
     * 获取当前用户对象
     *
     * @return
     */
    public static User currentUser() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        return principal;
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    public static Integer currentUserId() {
        User operationUser = currentUser();
        if (operationUser != null) {
            return operationUser.getId();
        }
        return null;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String currentUserName() {
        User operationUser = currentUser();
        if (operationUser != null) {
            return operationUser.getUsername();
        }
        return null;
    }
}
