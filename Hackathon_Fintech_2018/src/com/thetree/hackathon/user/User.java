/**
 * Tran Tuan Anh
 * Date: Oct 13, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thetree.hackathon.constant.Constants;
import com.thetree.hackathon.utils.DBUtils;

/**
 * @author gadfl
 *
 */
public class User extends DBUtils{

	private final String GET_LOGIN = "select * from User where Email=? and Password=?";
    private final String SEARCH_USER_BY_EMAIL = "select * from User where Email = ?";
    private final String ADD_USER = "INSERT INTO User (Name, Email, Password, Gender, Birthday, PhoneNumber) VALUES (?,?,?,?,?,?)";
    
    private String email;
    private String password;
    private String name;
    private String birthDay;
    private String phoneNumber;
    private String gender;
	
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBirthDay() {
        return birthDay;
    }
    
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        if(findByEmail(email)){
            createConnection();
            PreparedStatement ps = conn.prepareStatement(GET_LOGIN);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                    closeConnection();
                    return Constants.LOGIN_SUCCESS; // đăng nhập thành công
            }else{
                closeConnection();
                return Constants.LOGIN_WRONG_PASSWORD; // sai mật khẩu
            }
        }
        return Constants.LOGIN_WRONG_EMAIL; // email không tồn tại
    }
    
    public boolean addUser() throws SQLException, ClassNotFoundException {
        createConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement(SEARCH_USER_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()){
            closeConnection();
            return false;
        }else{
            ps = conn.prepareStatement(ADD_USER);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, birthDay);
            ps.setString(6, phoneNumber);
            ps.executeUpdate();
        }
        closeConnection();
        return true;
    }
    
    public boolean findByEmail(String email) throws SQLException, ClassNotFoundException{
        createConnection();
        PreparedStatement ps = conn.prepareStatement(SEARCH_USER_BY_EMAIL);
        ps.setString(1, email);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        if(rs !=null && rs.next()){
            this.setName(rs.getString("Name"));
            this.setEmail(rs.getString("Email"));
            this.setPhoneNumber(rs.getString("PhoneNumber"));
            this.setBirthDay(rs.getString("Birthday"));
            this.setPassword(rs.getString("Password"));
            this.setGender(rs.getString("Gender"));
            closeConnection();
            return true;
        }
        closeConnection();
        return false;
        
    }
}





