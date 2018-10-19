package com.jackpotHan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.URL;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description: 图片工具类
 */
public final class ImgUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgUtil.class);

    /**
     * 读取url获取图片的byte数组
     * @param imgurl
     * @return
     */
    public static byte[] getUrlImageByte(String imgurl) {
        try {
            URL url = new URL(imgurl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0){
                output.write(buffer,0,length);
            }
            byte[] context = output.toByteArray();
            dataInputStream.close();
            return context;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 读取本地图片获取图片的byte数组
     * @param imgPath
     * @return
     */
    public static byte[] getLocalImageByte(String imgPath) {
        byte[] content = null;
        try {
            BufferedInputStream bis = null;
            ByteArrayOutputStream out = null;
            try {
                FileInputStream input=new FileInputStream(imgPath);
                bis = new BufferedInputStream(input, 1024);
                byte[] bytes = new byte[1024];
                int len;
                out = new ByteArrayOutputStream();
                while ((len = bis.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                }
                bis.close();
                content = out.toByteArray();
            } finally {
                if (bis != null) bis.close();
                if (out != null) out.close();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return content;
    }

    private static void bytesToImg(byte[] byteRtn, String filePath) {
        try {
            FileImageOutputStream output=new FileImageOutputStream(new File(filePath));
            output.write(byteRtn);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
