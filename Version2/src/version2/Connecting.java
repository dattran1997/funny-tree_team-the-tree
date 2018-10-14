/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KirrNguyen
 */
public class Connecting {
    public static Connection conn = null;
    //String url = "jdbc:sqlite::resource:"+ getClass().getResource("/Database/database.db").toString();
    //String url = "jdbc:sqlite:database.db";
    String url = "jdbc:sqlite:src/Database/database.db";
    public Connection connecting(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            if(conn != null){
                System.out.println("ket noi thanh cong");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("not data");
        }
        return conn;
    }
    public void Connected(){
        conn = connecting();
    }
    public void Close() throws SQLException{
        if(conn != null) conn.close();
        System.out.println("close database");
    }
}
