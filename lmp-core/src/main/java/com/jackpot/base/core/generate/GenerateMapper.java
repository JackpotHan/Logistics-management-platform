package com.jackpot.base.core.generate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Hanjt
 * @Date: 2018/8/9 11:34
 * @Description: 逆向工程生成Mapper
 */
public class GenerateMapper {
    //生成Mapper类
    public static void generate(String entityName, String entityPackage, String mapperPackage, String mapperPath) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(mapperPackage).append(";\r\n").append("\r\n");

        sb.append("import com.jackpotHan.base.mapper.BaseMapper;\r\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\r\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;\r\n\r\n");

        sb.append("@Mapper\r\n");
        sb.append("public interface ").append(entityName).append("Mapper extends BaseMapper<" + entityName + "> {\r\n");
        sb.append("}\r\n");
        String content = sb.toString();
        System.out.println(content);
        FileWriter fw = new FileWriter(mapperPath + entityName + "Mapper.java");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
        fw.close();
    }
}
