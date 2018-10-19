package com.jackpotHan.web.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.base.Pageable;
import com.jackpotHan.entity.system.User;
import com.jackpotHan.entity.system.UserRole;
import com.jackpotHan.web.mapper.UserMapper;
import com.jackpotHan.web.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper mapper;
    @Resource
    private UserRoleService userRoleService;

    public Integer addUser(User user) {
        return mapper.insertSelective(user);
    }

    public Integer modifyUser(User user, String... fieldStrs) {
        Example example = MapperUtil.generateExample(user, fieldStrs);
        return mapper.updateByExampleSelective(user, example);
    }

    public User getUser(User user) {
        return mapper.selectOne(user);
    }

    public List<User> getUsers(User user) {
        return mapper.select(user);
    }

    public PageInfo<User> search(User user, Pageable pageable) {
        Example userExample = user.getUserExample();
        PageHelper.startPage(pageable.getPageNum(), pageable.getPageSize());
        List<User> users = mapper.selectByExample(userExample);
        return new PageInfo<>(users);
    }

    @Transactional(readOnly = false)
    public Boolean insert(User user) {
        List<User> users = mapper.selectByExample(user.getUserExample());
        Assert.isTrue(users.isEmpty(), "登录名已存在无法注册!");
        int c = mapper.insertSelective(user);
        return c == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    public PageInfo<User> getUserByRole(Integer roleId, String searchText,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(BaseUtil.isEmpty(searchText)){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            List<UserRole> userRoles = userRoleService.getUserRoles(userRole);
            criteria.andIn("id",userRoles.stream()
                    .map(UserRole::getUserId).collect(Collectors.toList()));
        }else{
            if(searchText.startsWith("1") && searchText.length()==11){
                criteria.andEqualTo("mobile",searchText);
            }else if (BaseUtil.isContainChinese(searchText)){
                criteria.andEqualTo("nickName",searchText);
            }else if(searchText.startsWith("1") && searchText.length() ==5){
                criteria.andEqualTo("id",searchText);
            }else {
                criteria.andEqualTo("accountName",searchText);
            }
        }
        List<User> users =   mapper.selectByExample(example);
        return new PageInfo<>(users);
    }


    public PageInfo<User> getAllUserByRoleId(Integer roleId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        List<UserRole> userRoles = userRoleService.getUserRoles(userRole);
        criteria.andIn("id",userRoles.stream()
                .map(UserRole::getUserId).collect(Collectors.toList()));
        List<User> users =   mapper.selectByExample(example);
        return new PageInfo<>(users);
    }
}

