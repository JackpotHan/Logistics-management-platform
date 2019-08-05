package com.lmp.admin.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtil {

    public static String getResponse(HttpServletRequest request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static void response(HttpServletResponse response, String message) {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(message);
            out.flush();
            out.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
