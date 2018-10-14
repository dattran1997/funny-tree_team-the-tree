/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thetree.hackathon.utils.DBUtils;

/**
 * @author gadfl
 *
 */
public class TimeLine extends DBUtils {

	private final String GET_TIMELINE = "select * from TimeLine where Email=? and Date=?";
	private final String ADD_TIMELINE = "INSERT INTO User (Email, Date, Time, Cost, Tag) VALUES (?,?,?,?,?)";

	private String email;
	private String date;
	private String time;
	private int cost;

	private String tag;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean addTimeLine() throws SQLException, ClassNotFoundException {
		createConnection();
		PreparedStatement ps;
		ps = conn.prepareStatement(ADD_TIMELINE);
		ps.setString(1, email);
		ps.setString(2, date);
		ps.setString(3, time);
		ps.setInt(4, cost);
		ps.setString(5, tag);
		ps.executeUpdate();
		closeConnection();
		return true;
	}

	/**
	 * @param time
	 * @param cost
	 * @param tag
	 */
	public TimeLine(String time, int cost, String tag) {
		super();
		this.time = time;
		this.cost = cost;
		this.tag = tag;
	}

	public TimeLine() {
		
	}
	
	public ArrayList<TimeLine> getTimeLine() throws ClassNotFoundException, SQLException{
		ArrayList<TimeLine> timeLineList = new ArrayList<>();
		createConnection();
		PreparedStatement stmt = conn.prepareStatement(GET_TIMELINE);
		stmt.setString(1, email);
		stmt.setString(2, date);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TimeLine timeLine = new TimeLine();
			timeLine.setEmail(rs.getString("Email"));
			timeLine.setDate(rs.getString("Date"));
			timeLine.setTime(rs.getString("Time"));
			timeLine.setCost(rs.getInt("Cost"));
			timeLine.setTag(rs.getString("Tag"));
			timeLineList.add(timeLine);
		}
		return timeLineList;
	}
	
}
