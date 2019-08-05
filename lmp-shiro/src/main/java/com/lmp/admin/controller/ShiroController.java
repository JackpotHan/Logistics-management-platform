package com.lmp.admin.controller;

import com.google.code.kaptcha.Constants;
import com.jackpot.base.base.BaseCode;
import com.jackpot.base.base.BaseResult;
import com.lmp.admin.dto.UserDTO;
import com.lmp.admin.dto.UserLoginDTO;
import com.lmp.admin.service.UserService;
import com.lmp.admin.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录控制器
 * @date 2019/1/15 10:50
 */
@RestController
@Slf4j
public class ShiroController {


    @Resource
    private UserService userService;

    @Value("${shiro.captcha:ZM_SHIRO}")
    private String default_captcha;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/login")
    public BaseResult login(@RequestBody UserLoginDTO user, HttpServletRequest request) {
        log.info("login params:{}", user);
        BaseResult result = new BaseResult();
        if (StringUtils.isBlank(user.getKaptcha().trim())) {
            result.setRespCode(BaseCode.CAPTCHA_EMPTY_ERR.getCode());
            result.setRespDesc(BaseCode.CAPTCHA_EMPTY_ERR.getMsg());
            return result;
        }
        HttpSession session = request.getSession();
        String sessionKaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!default_captcha.equals(user.getKaptcha().trim())) {
            if (!user.getKaptcha().trim().equalsIgnoreCase(sessionKaptcha)) {
                result.setRespCode(BaseCode.CAPTCHA_INPUT_ERR.getCode());
                result.setRespDesc(BaseCode.CAPTCHA_INPUT_ERR.getMsg());
                return result;
            }
        }
        //移出验证码
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        //登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userService.updateLoginTime(userDTO);
            result.setRespCode(BaseCode.LOGIN_SUCCESS.getCode());
            result.setRespDesc(BaseCode.LOGIN_SUCCESS.getMsg());
            result.setRespData(subject.getSession().getId());
        } catch (IncorrectCredentialsException e) {
            result.setRespCode(BaseCode.USERNAME_PASSWORD_ERR.getCode());
            result.setRespDesc(BaseCode.USERNAME_PASSWORD_ERR.getMsg());
        } catch (LockedAccountException e) {
            result.setRespCode(BaseCode.USERNAME_FREEZED_ERR.getCode());
            result.setRespDesc(BaseCode.USERNAME_FREEZED_ERR.getMsg());
        } catch (AuthenticationException e) {
            result.setRespCode(BaseCode.USERNAME_PASSWORD_ERR.getCode());
            result.setRespDesc(BaseCode.USERNAME_PASSWORD_ERR.getMsg());
        } catch (Exception e) {
            result.setRespCode(BaseCode.RESPCODE_FAIL.getCode());
            result.setRespDesc(BaseCode.RESPCODE_FAIL.getMsg());
        }
        return result;
    }

    /**
     * 未登录，shiro应重定向到登录界面
     *
     * @return
     */
    @RequestMapping(value = "/unauth")
    public BaseResult unauth() {
        return new BaseResult(BaseCode.SESSION_LOSE);
    }

    /**
     * 登出返回
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public BaseResult unlogin() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseResult(BaseCode.SESSION_LOSE);
    }


    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/kaptchaImage")
    public void createKaptchaImage(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = VerifyCodeUtils.generateVerifyCode(4);
        //String capText = captchaProducer.createText();
        // store the text in the session
        //request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //将验证码存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        //BufferedImage bi = captchaProducer.createImage(capText);
        try (ServletOutputStream out = response.getOutputStream()) {
            // write the data out
            // 生成图片
            int w = 150, h = 50;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), capText);
            //ImageIO.write(bi, "jpg", out);
            // out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
