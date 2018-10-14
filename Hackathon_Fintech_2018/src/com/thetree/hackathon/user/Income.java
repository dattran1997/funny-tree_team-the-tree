/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thetree.hackathon.utils.DBUtils;

/**
 * @author gadfl
 *
 */
public class Income extends DBUtils{

	private final String ADD_INCOME = "INSERT INTO AfterRegister (Email, Income, IncomeDate, EssentialCost) VALUES (?,?,?,?)";
	private final String UPDATE_INCOME = "UPDATE AfterRegister SET Income = ?, IncomeDate = ?, EssentialCost = ? WHERE Email = ?";
	private final String SEARCH_INCOME_BY_EMAIL = "select * from AfterRegister where Email = ?";
	
	private String email;
	private int income;
	private String incomeDate;
	private int essentialCost;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the income
	 */
	public int getIncome() {
		return income;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(int income) {
		this.income = income;
	}
	/**
	 * @return the incomeDate
	 */
	public String getIncomeDate() {
		return incomeDate;
	}
	/**
	 * @param incomeDate the incomeDate to set
	 */
	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}
	/**
	 * @return the essentialCost
	 */
	public int getEssentialCost() {
		return essentialCost;
	}
	/**
	 * @param essentialCost the essentialCost to set
	 */
	public void setEssentialCost(int essentialCost) {
		this.essentialCost = essentialCost;
	}
	
	public boolean addIncome() throws SQLException, ClassNotFoundException {
        createConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement(SEARCH_INCOME_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()){
            closeConnection();
            return false;
        }else{
            ps = conn.prepareStatement(ADD_INCOME);
            ps.setInt(1, income);
            ps.setString(2, incomeDate);
            ps.setInt(3, essentialCost);
           
            ps.executeUpdate();
        }
        closeConnection();
        return true;
    }
	
}
