package com.jackpotHan.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.jackpotHan.core.BaseController;
import com.jackpotHan.entity.system.User;
import com.jackpotHan.utils.AgentHttpUtil;
import com.jackpotHan.web.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "adminAgent")
public class BnhGatewayController extends BaseController {

    @Resource private AgentHttpUtil agentHttpUtil;
    @Resource private UserService userService;

    @RequestMapping("commonsAgent")
    public String commonsAgent(HttpServletRequest request){
        Enumeration paramNames = request.getParameterNames();
        Map<String,String> map = new HashMap<>();
        while(paramNames.hasMoreElements()){
            String paraName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paraName);
            if(paramValues.length == 1){
                String paramValue = paramValues[0];
                map.put(paraName,paramValue);
            }
        }
       return agentHttpUtil.bnhServiceSendPost(map);
    }

    @RequestMapping(value = "noAuthority")
    public String noAuthority(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paraName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paraName);
            if(paramValues.length == 1){
                String paramValue = paramValues[0];
                map.put(paraName,paramValue);
            }
        }
        return agentHttpUtil.bnhServiceSendPost(map);
    }

    /**
     * 获取到当前登入的用户数据 返回json
     * @param request
     * @return
     */
    @RequestMapping("getUserAgent")
    public String getUserAgent(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paraName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paraName);
            if(paramValues.length == 1){
                String paramValue = paramValues[0];
                map.put(paraName,paramValue);
            }
        }
        String id = map.get("id");
        User user = new User();
        user.setId(new Integer(id));
        User data =userService.getUser(user);
        if(data!=null){
          map.put("data",JSONObject.toJSONString(data));
        }
        return agentHttpUtil.bnhServiceSendPost(map);
    }

}
