package com.lmp.admin.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseException;
import com.lmp.admin.dto.UserDTO;
import com.lmp.admin.mapper.UserMapper;
import com.lmp.admin.model.User;
import com.lmp.admin.util.CipherUtil;
import com.lmp.admin.util.DateUtil;
import com.lmp.admin.util.ParamEmptyUtil;
import com.lmp.admin.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 用户服务
 * @date 2019/1/16 15:00
 */
@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public UserService(UserMapper mapper){
        this.mapper = mapper;
    }

    @Resource
    private UserRoleService userRoleService;


    public User findByUserName(String userName) {
        User param = new User();
        param.setUsername(userName);
        return mapper.selectOne(param);
    }

    /**
     * 注册添加用户
     *
     * @param userDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserDTO userDTO) {
        ParamEmptyUtil.validate(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getOrgId(), userDTO.getStatus());
        String userName = userDTO.getUsername().trim();
        if (checkUserExist(userName)) {
            throw new BaseException(BaseCode.USER_ACCOUNT_EXIST_ERR);
        }
        if (checkUserPhoneExist(userDTO.getPhone())) {
            throw new BaseException(BaseCode.USER_ACCOUNT_EXIST_ERR);
        }
        Subject subject = SecurityUtils.getSubject();
        User user1 = (User) subject.getPrincipal();
        String password = userDTO.getPassword().trim();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String encodePassword = encodePassword(password, salt);
        User user = new User();
        user.setPhone(userDTO.getPhone());
        user.setUsername(userName);
        user.setName(userDTO.getName());
        user.setPassword(encodePassword);
        user.setSalt(salt);
        user.setOrgId(userDTO.getOrgId());
        user.setStatus(userDTO.getStatus());
        user.setCreateTime(DateUtil.now());
        user.setLastLoginTime(DateUtil.getCurrentDate());
        user.setCreatorId(user1.getId());
        user.setOperateUser(user1.getUsername());
        mapper.insertSelective(user);
    }

    public boolean addUser(User user) {
        //判断是否注册
        if (checkUserExist(user.getUsername())) {
            throw new BaseException(BaseCode.USER_ACCOUNT_EXIST_ERR);
        }
        if (checkUserPhoneExist(user.getPhone())) {
            throw new BaseException(BaseCode.USER_ACCOUNT_PHONE_ERR);
        }
        return mapper.insertSelective(user) > 0 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyStatus(UserDTO userDTO) {
        ParamEmptyUtil.validate(userDTO.getUsername(), userDTO.getStatus());
        User param = new User();
        param.setUsername(userDTO.getUsername());
        param.setStatus(userDTO.getStatus());
        param.setUpdateTime(DateUtil.now());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", userDTO.getUsername());
        mapper.updateByExampleSelective(param, example);
    }

    public void modifyUser(User user) {
        ParamEmptyUtil.validate(user.getId());
        mapper.updateByPrimaryKey(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateLoginTime(UserDTO userDTO) {
        ParamEmptyUtil.validate(userDTO.getUsername());
        User param = new User();
        param.setLastLoginTime(DateUtil.now());
        param.setUpdateTime(DateUtil.now());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", userDTO.getUsername());
        mapper.updateByExampleSelective(param, example);
//        cleanOtherSession(userDTO);

    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(UserDTO userDTO) {
        ParamEmptyUtil.validate(userDTO.getUsername(), userDTO.getPassword());
        User param = new User();
        String password = userDTO.getPassword().trim();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String encodePassword = encodePassword(password, salt);
        param.setPassword(encodePassword);
        param.setSalt(salt);
        param.setUpdateTime(DateUtil.now());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", userDTO.getUsername());
        int i = mapper.updateByExampleSelective(param, example);
        if (i != 1) {
            throw new BaseException(BaseCode.RESPCODE_FAIL);
        }

    }

    public void resetPassword(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", user.getId());
        mapper.updateByExampleSelective(user, example);
    }

    public PageInfo<UserDTO> queryPage(UserDTO userDTO) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        boolean superAdmin = userRoleService.judegeHasSuperAdmin(user.getId().longValue());
        if (!superAdmin) {
            userDTO.setCreatorId(user.getId());
        }
        return PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize()).doSelectPageInfo(() -> mapper.list(userDTO));
    }


    /**
     * 验证用户名是否已经存在,存在返回true
     *
     * @param userName
     * @return
     */
    public boolean checkUserExist(String userName) {
        User param = new User();
        param.setUsername(userName);
        User operationUser = mapper.selectOne(param);
        if (operationUser != null) {
            return true;
        }
        return false;
    }

    public boolean checkUserPhoneExist(String phone) {
        if (StringUtil.isEmpty(phone)) return false;
        User param = new User();
        param.setPhone(phone);
        User operationUser = mapper.selectOne(param);
        if (operationUser != null) {
            return true;
        }
        return false;
    }


    /**
     * 清除同用户 其他登录会话
     *
     * @param userDTO
     */
    private void cleanOtherSession(UserDTO userDTO) {
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        SessionDAO sessionDAO = sessionManager.getSessionDAO();
        Collection<Session> sessions = sessionDAO.getActiveSessions();//获取当前已登录的用户session列表
        //同一时刻 同一账号只能有一个
        if (sessions != null && sessions.size() > 0) {
            for (Session session : sessions) {
                if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {

                    Subject subject = new Subject.Builder().session(session).buildSubject();
                    User userSession = (User) subject.getPrincipal();
                    //
                    if (userDTO.getUsername().equals(userSession.getUsername())) {
                        Serializable id = SecurityUtils.getSubject().getSession().getId();
                        if (id.equals(session.getId())) {
                            continue;
                        }
                        //两者一致的时候，删除这个session
                        sessionDAO.delete(session);
                    }
                }

            }
        }
    }

    /**
     * 密码明文加密
     *
     * @param password 明文密码
     * @param salt     盐值
     * @return
     */
    public static String encodePassword(String password, String salt) {
        String hashAlgorithmName = "SHA-256";
        String s = CipherUtil.encodeSHA256ByString(password);
        //  String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        ByteSource saltSource = ByteSource.Util.bytes(salt);
        int hashIterations = 1;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, s, saltSource, hashIterations);
        return simpleHash.toString();
    }

    public void editUser(UserDTO userDTO) {
        ParamEmptyUtil.validate(userDTO.getUsername(), userDTO.getName());
        User param = new User();
        param.setName(userDTO.getName());
        param.setStatus(userDTO.getStatus());
        param.setUpdateTime(DateUtil.now());
        param.setOperateUser(userDTO.getOperateUser());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", userDTO.getUsername());
        mapper.updateByExampleSelective(param, example);
    }

    public User getUser(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public User getUser(String userName) {
        User user = new User();
        user.setUsername(userName);
        return mapper.selectOne(user);
    }

    public List<User> getUserNotEqualId(String phone, Integer id) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone).andNotEqualTo("id", id);
        return mapper.selectByExample(example);
    }

    public void update(User user) {
        mapper.updateByPrimaryKeySelective(user);
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

}
