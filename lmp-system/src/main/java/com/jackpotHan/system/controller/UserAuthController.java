package com.jackpotHan.system.controller;

import com.jackpotHan.base.BaseResult;
import com.jackpotHan.system.shiro.SimpleShiroManage;
import com.jackpotHan.web.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "authorize",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class UserAuthController extends SimpleShiroManage {


    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/getMetadata/{userId}", method = RequestMethod.GET)
    public BaseResult getMetadata(@PathVariable(name = "userId") Integer userId) {
       return new BaseResult(userAuthService.selectUserAuth(userId));
    }
}
