/**
 * Tran Tuan Anh
 * Date: Oct 13, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gadfl
 *
 */
public class AccountHelper {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PASSWORD_REGEX = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
	public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^([ .-]*[0-9]+[ .-]*)+$");

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static int validateName(String str) {
		int len = str.length();
		if (len == 0)
			return 1;
		if (len > 25)
			return 2;
		return 0;
	}

	public static boolean validatePhoneNumber(String phoneNumber) {
		if (phoneNumber.length() > 0) {
			Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
			return matcher.find();
		}
		return true;
	}

	public static boolean validatePassword(String passStr) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(passStr);
		return matcher.find();
	}

	public static String MD5encrypt(String password) {
		StringBuffer sb = new StringBuffer();
		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			for (int i = 0; i < thedigest.length; i++) {
				sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public static String getDate(String date) {
		String[] splitResult = date.split("-"); // split(date, " ");
		//String month = convertStringToMonth(splitResult[0]);
		return splitResult[1] + "/" + splitResult[2] + "/" + splitResult[0];
	}

	private static String convertStringToMonth(String month) {
		switch (month) {
		case "Jan":
			return "01";
		case "Feb":
			return "02";
		case "Mar":
			return "03";
		case "Apr":
			return "04";
		case "May":
			return "05";
		case "June":
			return "06";
		case "Jul":
			return "07";
		case "Aug":
			return "08";
		case "Sept":
			return "09";
		case "Oct":
			return "10";
		case "Nov":
			return "11";
		case "Dec":
			return "12";
		default:
			return "null";
		}
	}
}
