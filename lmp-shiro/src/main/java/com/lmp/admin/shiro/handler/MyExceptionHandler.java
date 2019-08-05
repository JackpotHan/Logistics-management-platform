package com.lmp.admin.shiro.handler;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.jackpot.base.base.BaseCode;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: admin 全局异常处理
 * @date 2019/1/14 17:44
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
//        if (ex instanceof UnauthenticatedException) {
//            attributes.put("respCode", BaseCode.USERNAME_PASSWORD_ERR.getCode());
//            attributes.put("respDesc", BaseCode.USERNAME_PASSWORD_ERR.getMsg());
//        } else if (ex instanceof UnauthorizedException) {
//            attributes.put("respCode", BaseCode.UNAUTHORIZED_ERR.getCode());
//            attributes.put("respDesc", BaseCode.UNAUTHORIZED_ERR.getMsg());
//        } else {
//            attributes.put("respCode", BaseCode.RESPCODE_FAIL.getCode());
//            attributes.put("respDesc", BaseCode.RESPCODE_FAIL.getMsg());
//        }
        attributes.put("respCode", BaseCode.RESPCODE_FAIL.getCode());
        attributes.put("respDesc", ex.getMessage());
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }

}
