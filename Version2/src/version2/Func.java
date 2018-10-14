/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static version2.Connecting.conn;
import static version2.Home.*;
import static version2.Login.*;

/**
 *
 * @author KirrNguyen
 */
public class Func {
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ptmt = null;
    public boolean checkLogin(){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from SaveMoney");
            while (rs.next()) {
                    String user = rs.getString(1);  
                    if(!user.equals("")){
                        return true;
                    }
                } 
            } catch (SQLException ex) {}
        return false;
    }
    public void UpdateMoneyIn(String name,int money){
        //cn.Connected();
        String Update = "update user set MoneyIn = ? where email = ?";
        try {    
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                    ptmt = conn.prepareStatement(Update);
                    ptmt.setInt(1,money);
                    ptmt.setString(2, name);
                    int kt = ptmt.executeUpdate();
                    if(kt != 0){
                        //JOptionPane.showMessageDialog(null, "update thành công");
                    }
                    ptmt.close();
            }
        
        } catch (SQLException ex) {
            System.out.println("UpdateMoneyIn");
        }
//        try {
//            cn.Close();
//        } catch (SQLException ex) {}
    }
    public void UpdateMoney1(String name,int money){
        //cn.Connected();
        String Update = "update user set money = ? where email = ?";
        try {    
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                    ptmt = conn.prepareStatement(Update);
                    ptmt.setInt(1,Money1 + money);
                    ptmt.setString(2, name);
                    int kt = ptmt.executeUpdate();
                    if(kt != 0){
                        //JOptionPane.showMessageDialog(null, "update thành công");
                    }
                    ptmt.close();
            }
        
        } catch (SQLException ex) {
            System.out.println("UpdateMoneyIn");
        }
//        try {
//            cn.Close();
//        } catch (SQLException ex) {}
    }
    public void UpdateWithDraw(String name,String money){
        //cn.Connected();
        String Update = "update user set withdraw = ? where email = ?";
        try {    
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                    ptmt = conn.prepareStatement(Update);
                    ptmt.setString(1,money);
                    ptmt.setString(2, name);
                    int kt = ptmt.executeUpdate();
                    if(kt != 0){
                        //JOptionPane.showMessageDialog(null, "update thành công");
                    }
                    ptmt.close();
            }
        
        } catch (SQLException ex) {
            System.out.println("UpdateMoneyIn");
        }
//        try {
//            cn.Close();
//        } catch (SQLException ex) {}
    }
    public boolean check(String username){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                    String name1 = rs.getString(1);
                    if(username.equals(name1)) {
                        JOptionPane.showMessageDialog(null, "Email đã tồn tại");
                        return false;
                    }
            }
        } catch (SQLException ex) {
            System.out.println("check");
        }
        return true;
        
    }
    public void Register(String email,String name,int money,
                        int savemoney1,int savemoney2,int savemoney3,String password){
        //cn.Connected();
        String insert = "insert into User values(?,?,?,?,?,?,?,?)";
        try {
                ptmt = conn.prepareStatement(insert);
                ptmt.setString(1, email);
                ptmt.setString(2, name);
                ptmt.setInt(3, money);
                ptmt.setInt(4, savemoney1);
                ptmt.setInt(5, savemoney2);
                ptmt.setInt(6, savemoney3);
                ptmt.setString(7, password);
                ptmt.setInt(8, 0);
                int kt = ptmt.executeUpdate();
                if(kt != 0){
                    //JOptionPane.showMessageDialog(null, "đã gửi đăng kí");
                }else System.out.println("them khong thanh cong");
                ptmt.close();
            } catch (SQLException ex) {
                System.out.println("Register");
            }
//        try {
//            cn.Close();
//        } catch (SQLException ex) {}
    }
    public void CreateTable(String email){
        try{
            String sql = "CREATE TABLE "+email+
                     "(name VARCHAR(255), " +
                     " money INT(25), " + 
                     " note VARCHAR(255), " + 
                     " time VARCHAR(255))"; 

            stmt.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println("CreateTable");
        }
    }
    public void insertday(String user,String name,int money,String note){
        
        
        String insert = "insert into "+user+" values(?,?,?,?)";
        try {
                ptmt = conn.prepareStatement(insert);
                ptmt.setString(1, name);
                ptmt.setInt(2, money);
                ptmt.setString(3, note);
                ptmt.setString(4, time);
                int kt = ptmt.executeUpdate();
                if(kt != 0){
                    //JOptionPane.showMessageDialog(null, "đã gửi đăng kí");
                }else System.out.println("them khong thanh cong");
                ptmt.close();
            } catch (SQLException ex) {
                System.out.println("insertday");
            }
    }
    // lấy giá trị của bảng user
    public void getTable(String user){
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                if(user.equals(rs.getString(1))){
                    Money1 = rs.getInt(3);
                    Money2 = rs.getInt(4);
                    Money3 = rs.getInt(5);
                    PayMoney = rs.getInt(6);   
                    MoneyIn = rs.getInt(8);
                }
            }
            
        }catch(Exception ex){
            System.out.println("getTable");
        }
    }
    // lấy tài khoản đã sd của user
    public void getMoney(String user){
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from "+user);
            while (rs.next()) {
                MoneyOut += rs.getInt(2);
            }
            
        }catch(Exception ex){
            System.out.println("getTable");
        }
    }
    
    public boolean LoginUser(String username,String password){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                    String name1 = rs.getString(1);  
                    String name2 = rs.getString(7);
                    if(username.equals(name1) && password.equals(name2)) {
                        NAMELOGIN = name1;
                        return true;
                    }
                } 
            } catch (SQLException ex) {}
        return false;
    }
    
    public int eatmoney(String name){
        int output = 0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from "+name);
            while (rs.next()) {
                if(rs.getString(1).equals("food")){
                    output += rs.getInt(2);
                }
            }
            
        }catch(Exception ex){
            System.out.println("eatmoney");
        }
        return output;
    }
    public int workmoney(String name){
        int output = 0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from "+name);
            while (rs.next()) {
                if(rs.getString(1).equals("work")){
                    output += rs.getInt(2);
                }
            }
            
        }catch(Exception ex){
            System.out.println("eatmoney");
        }
        return output;
    }
    public int moremoney(String name){
        int output = 0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from "+name);
            while (rs.next()) {
                if(rs.getString(1).equals("more")){
                    output += rs.getInt(2);
                }
            }
            
        }catch(Exception ex){
            System.out.println("eatmoney");
        }
        return output;
    }
}
