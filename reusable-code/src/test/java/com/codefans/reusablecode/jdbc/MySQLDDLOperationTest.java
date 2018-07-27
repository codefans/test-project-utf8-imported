package com.codefans.reusablecode.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * @author: codefans
 * @date: 2018-07-05 14:30
 * java操作DDL
 * DDL（data definition language）数据定义语言
 *      CREATE、ALTER、DROP
 *
 */
public class MySQLDDLOperationTest {

    String driverClassName = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://10.60.58.247:3306/credit_platform_base?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull";

    String username = "order_t_w";
    String password = "NTk3NjIyYmJmNDY";

    @Test
    public void createTest() {

        this.createDBTest();
//        this.createTableTest();


    }

    public void createDBTest() {

        url = "jdbc:mysql://10.60.58.247:3306/mysql?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull";
        String sql = "CREATE DATABASE `DDLTestDB` CHARACTER SET utf8;";

        this.executeUpdate(sql);

    }

    public void createTableTest() {

        String sql = "CREATE TABLE `business_customer_base222` (" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID'," +
                "  `create_time` datetime NOT NULL COMMENT '创建时间'," +
                "  `update_time` datetime NOT NULL COMMENT '更新时间', " +
                "  `creator` datetime DEFAULT NULL COMMENT '创建人'," +
                "  `updater` datetime DEFAULT NULL COMMENT '更新人', " +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对公客户基础信息表';";

        this.executeUpdate(sql);

    }

    @Test
    public void alterTest() {

        String sql = "alter table `business_customer_base222` add column addr varchar(20) NOT NULL COMMENT '姓名';";
        this.executeUpdate(sql);


    }

    public void executeUpdate(String sql) {
        try {

            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int count = pstmt.executeUpdate();

            System.out.println("count:" + count);

            System.out.println("update count:" + pstmt.getUpdateCount());

//            this.print(pstmt.getMetaData());

//            DatabaseMetaData metaData = conn.getMetaData();
//            metaData.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(ResultSetMetaData metaData) {

        try {
            int columnCount = metaData.getColumnCount();
            String columnName = "";
            for(int i = 0; i < columnCount; i ++) {
                columnName = metaData.getColumnName(i);
                System.out.println(columnName);


            }







        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
