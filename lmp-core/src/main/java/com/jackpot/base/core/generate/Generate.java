package com.jackpot.base.core.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Hanjt
 * @Date: 2018/8/9 11:33
 * @Description: 逆向工程
 */
public class Generate {
    private static List<String> colnames = new ArrayList<>();
    private static List<String> colTypes = new ArrayList<>();
    private static List<String> colComment = new ArrayList<>();
    private static Boolean importDate = false;
    private static Boolean importBigDecimal = false;

    /**
     *
     * @param host 数据库IP
     * @param port 数据库端口号
     * @param database 数据库名
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param tableName 表名
     * @param entityName 实体类名
     * @param basePackage 实体类包
     * @param basePath 实体类路径
     * @throws Exception
     */
    public static void generate(String host, String port, String database, String username, String password, String tableName, String entityName, String basePackage, String basePath) throws Exception {
        String entityPackage = basePackage + ".entity";
        String entityPath = basePath + "lmp-core/src/main/java/com/jackpotHan/entity/";
        String mapperPackage = basePackage + ".mapper";
        String mapperPath = basePath + "lmp-base/src/main/java/com/jackpotHan/base/mapper/";
        String servicePackage = basePackage + ".service";
        String servicePath = basePath + "lmp-base/src/main/java/com/jackpotHan/base/service/";
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, entityPackage, entityPath, database, tableName, colnames, colTypes, colComment, importDate, importBigDecimal);
       ///生成Mapper
        GenerateMapper.generate(entityName, entityPackage, mapperPackage, mapperPath);
        //生成Service
        GenerateService.generate(entityName, entityPackage, mapperPackage, servicePackage, servicePath);

    }

    public static void generateEntity(String host, String port, String database, String username, String password, String tableName, String entityName, String basePackage, String basePath) throws Exception {
        String entityPackage = basePackage + ".entity";
        String entityPath = basePath + "/entity/";
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, entityPackage, entityPath, database, tableName, colnames, colTypes, colComment, importDate, importBigDecimal);

    }

    private static void initTable(String host, String port, String database, String username, String password, String tableName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
        Connection conn = DriverManager.getConnection(url, username, password);
        String strsql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + tableName + "'"; //读一行记录;
        PreparedStatement pstmt = conn.prepareStatement(strsql);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            colnames.add(result.getString(1));
            colTypes.add(result.getString(2));
            colComment.add(result.getString(4));
            if ("date".equals(result.getString(2)) || "datetime".equals(result.getString(2)) || "timestamp".equals(result.getString(2))) importDate = true;
            if ("decimal".equals(result.getString(2))) importBigDecimal = true;
        }
        if(conn!=null) conn.close();
    }

    public static void main(String[] args)throws Exception {
        String basePath = "D:\\IdeaProject\\Logistics-management-platform\\";
        generate("localhost", "3306", "lmp-delivery", "root", "123456",
                "t_order","Order", "com.jackpotHan", basePath);
    }

}
