package com.jackpotHan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);
    public static String getResponse(HttpServletRequest request) {
        try {
            // 读取请求内容
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static void response(HttpServletResponse response, String message) {
        try{
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
            out.println(message);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
