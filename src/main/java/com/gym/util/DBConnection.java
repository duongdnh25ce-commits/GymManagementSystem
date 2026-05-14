package com.gym.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Lấy thông số từ Railway cung cấp
        String host = System.getenv("MYSQLHOST");
        String port = System.getenv("MYSQLPORT");
        String dbName = System.getenv("MYSQLDATABASE");
        String user = System.getenv("MYSQLUSER");
        String pass = System.getenv("MYSQLPASSWORD");

        String url;
        String finalUser;
        String finalPass;

        if (host != null) {
            // ĐANG CHẠY TRÊN RAILWAY
            url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true";
            finalUser = user;
            finalPass = pass;
            System.out.println("--- Kết nối CSDL Railway thành công ---");
        } else {
            // ĐANG CHẠY TRÊN MÁY TÍNH CỦA BẠN (Sửa lại pass máy bạn nếu cần)
            url = "jdbc:mysql://localhost:3306/GymManagement?useUnicode=true&characterEncoding=UTF-8";
            finalUser = "root"; 
            finalPass = "admin123"; 
            System.out.println("--- Kết nối CSDL Localhost thành công ---");
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, finalUser, finalPass);
    }
}