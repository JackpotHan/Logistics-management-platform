package com.xiaohan.system.controller;

import com.xiaohan.base.BaseResult;
import com.xiaohan.web.service.UserAuthService;
import com.xiaohan.system.shiro.SimpleShiroManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "authorize",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAuthController extends SimpleShiroManage {

    private static Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/getMetadata/{userId}", method = RequestMethod.GET)
    public BaseResult getMetadata(@PathVariable(name = "userId") Integer userId) {
       return new BaseResult(userAuthService.selectUserAuth(userId));
    }
}
