package com.xiaohan.web.service;

import com.xiaohan.entity.system.UserAuth;
import com.xiaohan.web.mapper.UserAuthMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAuthService {

    @Resource
    private UserAuthMapper mapper;

    public List<UserAuth> selectUserAuth(Integer userId){
        return mapper.selectUserAuth(userId);
    }
}
