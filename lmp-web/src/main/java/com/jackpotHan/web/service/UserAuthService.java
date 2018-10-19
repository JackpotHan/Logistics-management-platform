package com.jackpotHan.web.service;

import com.jackpotHan.entity.system.UserAuth;
import com.jackpotHan.web.mapper.UserAuthMapper;
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
