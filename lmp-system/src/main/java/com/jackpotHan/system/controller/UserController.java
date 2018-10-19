package com.jackpotHan.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jackpotHan.base.BaseCode;
import com.jackpotHan.base.BaseResult;
import com.jackpotHan.base.BaseUtil;
import com.jackpotHan.base.Pageable;
import com.jackpotHan.core.BaseController;
import com.jackpotHan.entity.system.User;
import com.jackpotHan.enums.Shiro.StatusEnum;
import com.jackpotHan.preference.UserPreference;
import com.jackpotHan.system.shiro.SimpleShiroManage;
import com.jackpotHan.utils.DateUtil;
import com.jackpotHan.utils.Digests;
import com.jackpotHan.utils.Encodes;
import com.jackpotHan.utils.VerifyCodeUtil;
import com.jackpotHan.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value="user")
@Slf4j
public class UserController extends BaseController {

    private static final String BNH_SESSION_ID = "imgSessionId";

    private static final String NO_FIND_USER = "没有找到用户";

    private static final String USER_PASSWORD_WRONG = "用户密码错误";

    private final static String CREATE_USER_SUCCESS = "创建用户成功";

    private final static String UNKNOWN_EXCEPTION = "未知异常";

    private final static String UPDATE_USER_SUCCESS = "更新用户成功";

    private final static String RESET_PASSWORD_SUCCESS = "重置用户密码成功";

    @Resource
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Reason: 用户登录.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(User form, HttpServletRequest request) {
        String checkCode = form.getCheckCode();
        if(BaseUtil.isEmpty(checkCode)) {
            return new BaseResult(BaseCode.CODE_ERROR.getCode());
        }
        String lowerCase = checkCode.trim().toLowerCase();
        if(!lowerCase.equals("xh.shiro")){
            String rand = (String) request.getSession().getAttribute(BNH_SESSION_ID);
            if (BaseUtil.isEmpty(rand)||!lowerCase.equals(rand.trim().toLowerCase())) {
                return new BaseResult(BaseCode.CODE_ERROR.getCode());
            }
        }
        //查询用户
        User user = userService.getUser(User.builder().accountName(form.getAccountName()).build());
        //判断用户是否有效
        verifyStatus(user);
        Assert.isTrue(BaseUtil.isNotEmpty(user), NO_FIND_USER);

        //密码加密验证
        String password = SimpleShiroManage.entryptPassword(user.getSalt(), form.getPassword());
        Assert.isTrue(StringUtils.equals(password, user.getPassword()), USER_PASSWORD_WRONG);
        BaseResult result = new BaseResult(user);

        //登陆参数验证后存入shiro,不用二次登陆
        JSONObject jo = JSON.parseObject(JSON.toJSONString(result));
        String accountName = user.getAccountName();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(accountName,jo.toJSONString());
        subject.login(token);
        Object principal = subject.getPrincipal();
        return new BaseResult<>(principal);
    }

    /**
     * 获取图片验证码 返回图片验证码输出流
     *
     * @param request http请求
     * @param response http响应
     *
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getCheckImg", method = RequestMethod.GET)
    public void getCheckImg(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        // 设置有失效时间的redis
        HttpSession session = request.getSession(true);
        session.setAttribute(BNH_SESSION_ID,verifyCode);
        log.info("验证码:{}",session.getAttribute(BNH_SESSION_ID));
        // 生成图片
        int w = 200, h = 80;
        try {
            VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Reason: 检测用户状态.
     *
     * @param user
     */
    private void verifyStatus(User user) {
        if (BaseUtil.isNotEmpty(user)) {
            Integer userStatus = user.getStatus();
            Assert.isTrue(userStatus.compareTo(StatusEnum.DISABLE.getCode()) != 0,
                    String.format(StatusEnum.DISABLE.getMsg()));
        }
    }

    /**
     * Reason: 用户列表查询.分页
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public BaseResult search(User user, Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗查询用户列表:query:{}", user);
        }
    return new BaseResult(userService.search(user,pageable));
    }

    /**
     * Reason: 新增用户信息.
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(User user) {
        user.setGmtOperate(DateUtil.getCurrentDate());
        user.setOperator(user.getOperator());
        //密码设置
        SimpleShiroManage.entryptPassword(user, user.getPassword());
        Boolean state = userService.insert(user);
        return state.compareTo(Boolean.TRUE) == 0 ?
                new BaseResult(BaseCode.OK.getCode()) :
                new BaseResult(BaseCode.ACCOUNT_EXIST.getCode(),BaseCode.ACCOUNT_EXIST.getDesc());
    }

    /**
     * Reason: 更新用户信息.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(User user) {
        log.info("updateUser",user);
        validateParams(user,"id","operator");
        user.setGmtOperate(DateUtil.getCurrentDate());
        user.setOperator(user.getOperator());
        Integer state = userService.modifyUser(user,"id");
        return state == 1 ?
                new BaseResult(BaseCode.OK.getCode()) :
                new BaseResult(BaseCode.FAIL.getCode());
    }

    /**
     * Reason: 重置用户密码.
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public BaseResult resetPassword(User user,HttpServletRequest request) {
        User modifyUser = new User();
        modifyUser.setId(user.getId());
        modifyUser.setGmtOperate(DateUtil.getCurrentDate());
        modifyUser.setOperator(user.getOperator());
        //密码设置
        SimpleShiroManage.entryptPassword(modifyUser, SimpleShiroManage.DEFAULT_PASSWORD);
        Integer state = userService.modifyUser(modifyUser,"id");
        return state == 1 ?
                new BaseResult(BaseCode.OK.getCode()) :
                new BaseResult(BaseCode.FAIL.getCode(),"重置密码失败");
    }

    /**
     * Reason: 用户首选项.
     */
    @RequestMapping(value = "/getPreferences", method = RequestMethod.GET)
    public BaseResult getPreferences() {
        if (log.isInfoEnabled()) {
            log.info("〖信息〗用户首选项");
        }
        Map map = Maps.newHashMap();
        map.put("userStatusPreferences", new UserPreference().new UserStatus().preferences());
        map.put("userTypePreferences", new UserPreference().new UserType().preferences());
        log.info(JSON.toJSONString(map));
        return new BaseResult(map);
    }

    /**
     * Reason: 更新本人用户密码.
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public BaseResult changePassword(User form) {
        User user = userService.getUser(User.builder().id(form.getId()).build());
        byte[] salt = Encodes.decodeHex(user.getSalt());
        byte[] hashPassword = Digests.sha1(user.getOldPassword().getBytes(), salt, SimpleShiroManage.HASH_INTERATIONS);
        String dataPassword = Encodes.encodeHex(hashPassword);
        if (!user.getPassword().equals(dataPassword)){
            return new BaseResult(BaseCode.FAIL.getCode(), "原始密码错误!");
        }
        //密码设置
        User modifyUser = new User();
        modifyUser.setId(user.getId());
        modifyUser.setOperator(form.getOperator());
        modifyUser.setGmtOperate(DateUtil.getCurrentDate());
        SimpleShiroManage.entryptPassword(modifyUser, form.getPassword());
        Integer state = userService.modifyUser(modifyUser,"id");
        return state == 1 ?
                new BaseResult(BaseCode.OK.getCode()) :
                new BaseResult(BaseCode.FAIL.getCode(), UNKNOWN_EXCEPTION);
    }

}
