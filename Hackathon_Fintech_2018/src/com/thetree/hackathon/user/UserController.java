/**
 * Tran Tuan Anh
 * Date: Oct 13, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author gadfl
 *
 */
public class UserController {

	User user = new User();

	private static UserController me;

	private UserController() {
	}

	public static UserController getInstance() {
		if (me == null)
			me = new UserController();
		return me;
	}

	public int checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
		return user.checkLogin(email, password);
	}

	public boolean register(User user) throws ClassNotFoundException, SQLException {
		return user.addUser();
	}

	public User findUserByEmail(String email) throws SQLException, ClassNotFoundException {
		User user = new User();
		if (user.findByEmail(email))
			return user;
		return null;
	}
}
