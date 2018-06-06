package com.codefans.reusablecode.jdbc;

import java.sql.*;

/**
 * @author: mpif
 * @date: 2018-06-04 17:31
 */
public class ConnToMySQLUseJdbc {

    public static void main(String[] args) {
        ConnToMySQLUseJdbc connToMySQLUseJdbc = new ConnToMySQLUseJdbc();
        connToMySQLUseJdbc.conn();
    }

    public void conn() {

        try {

            Class.forName("org.gjt.mm.mysql.Driver");

//            String url = "jdbc:mysql://10.60.58.37:3306/jade?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
//            String username = "jade";
//            String password = "jade";
//            String sql = "select count(*) from lejr_risk_scene_0101";

            String url = "jdbc:mysql://10.60.58.37:3306/risk_operating?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true";
            String username = "order_t_w";
            String password = "NTk3NjIyYmJmNDY";
            String sql = "select count(*) from lejr_scenes";

            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int count = rs.getInt(1);
                System.out.println("count:" + count);
            }








        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }





}
