package com.lmp.admin.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 处理流的工具类
 */
public class StreamUtil {

    private static final Logger LOGGER       = LoggerFactory.getLogger(StreamUtil.class.getName());

    private static final int    DEFAULT_SIZE = 1024 * 20;

    /**
     * close inputstream
     *
     * @param is
     */
    public static void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                LOGGER.warn("Error occur when closing InputStream!" + e.getMessage(), e);
            }
        }
    }

    /**
     * close outputstream
     *
     * @param os
     */
    public static void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                LOGGER.warn("Error occur when closing OutputStream!" + e.getMessage(), e);
            }
        }
    }

    public static void closeReader(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.warn("Error occur when closing Reader!" + e.getMessage(), e);
            }
        }
    }

    public static void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                LOGGER.warn("Error occur when closing Writer!" + e.getMessage(), e);
            }
        }
    }

    /**
     * 将InputStream写到fileName文件里
     */
    public static void writeInputStreamToFile(InputStream is, File file) throws Exception {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buff = new byte[DEFAULT_SIZE]; // buff用于存放循环读取的临时数据
            int readed = 0;
            while ((readed = is.read(buff)) > 0) {
                os.write(buff, 0, readed);
            }
            os.flush();
        } finally {
            closeStream(os);
        }
    }

    public static byte[] getByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        byte[] result = null;
        try {
            int readed = 0;
            while ((readed = is.read(buff)) > 0) {
                swapStream.write(buff, 0, readed);
            }
            swapStream.flush();
            result = swapStream.toByteArray();
        } finally {
            StreamUtil.closeStream(swapStream);
        }
        return result;
    }

    /**
     * str空判断
     * 
     * @param str
     * @return
     * 
     */
    public static boolean isNull(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
            return true;
        } else
            return false;
    }

    /**
     * 获取当前时间str，格式yyyyMMddHHmmss
     * 
     * @return
     * 
     */
    public static String getCurrentDateTimeStr() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * 
     * 功能描述：获取真实的IP地址
     * 
     * @param request
     * @return
     * 
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getParameter("userreq_ip");
        if (ip == null || "".equals(ip.trim())) {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (!isNull(ip) && ip.contains(",")) {
                String[] ips = ip.split(",");
                ip = ips[ips.length - 1];
            }
            // 转换IP 格式
            if (!isNull(ip)) {
                ip = ip.replace(".", "_");
            }
        }
        return ip;
    }

    /**
     * 生成待签名串
     *
     * @return
     * 
     */
    public static String genSignData(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if ("sign".equals(key)) {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (isNull(value)) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * 读取request流
     *
     * @return
     * 
     */
    public static String readReqStr(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {

            }
        }
        return sb.toString();
    }

    public static String generateRiskItem(HttpServletRequest req) {
        JSONObject riskItemObj = new JSONObject();
        riskItemObj.put("user_info_full_name", "你好");
        riskItemObj.put("frms_ware_category", "1999");
        return riskItemObj.toString();
    }

    public static String getVersion(HttpServletRequest req) {
        String version = req.getParameter("version");
        if (version == null || "".equals(version.trim()))
            version = "1.0";
        return version;
    }

    public static String getFlagModify(HttpServletRequest req) {
        String flag_modify = req.getParameter("flag_modify");
        if (flag_modify == null || "".equals(flag_modify.trim()))
            flag_modify = "1";
        return flag_modify;
    }

    public static String getContextUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() // 服务器地址
                + ":" + request.getServerPort() // 端口号
                + request.getContextPath() // 项目名称
                + request.getServletPath(); // 参数
    }

    public static void setRequestCharacter(HttpServletRequest request, String character) {
        try {
            request.setCharacterEncoding(character);
        } catch (Exception e) {
            // ignore exception
        }
    }

    public static void setResponseCharacter(HttpServletResponse response, String character) {
        try {
            response.setCharacterEncoding(character);
        } catch (Exception e) {
            // ignore exception
        }
    }

}