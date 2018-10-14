/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author gadfl
 *
 */
public class RegisterController implements Initializable {

	@FXML
	JFXTextField txtName;
	@FXML
	JFXPasswordField txtPass;
	@FXML
	JFXTextField txtEmail;
	@FXML
	JFXTextField txtGender;
	@FXML
	JFXTextField txtPhoneNumber;
	@FXML
	JFXDatePicker dpBirthday;
	@FXML
	Text lblPass;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txtEmail.setStyle("-fx-text-inner-color: white");
				//txtEmail.setStyle("-fx-prompt-text-fill: white");
				txtPass.setStyle("-fx-text-inner-color: white");
				//txtPass.setStyle("-fx-prompt-text-fill: white");
				txtName.setStyle("-fx-text-inner-color: white");
				//txtName.setStyle("-fx-prompt-text-fill: white");
				txtGender.setStyle("-fx-text-inner-color: white");
				//txtGender.setStyle("-fx-prompt-text-fill: white");
				txtPhoneNumber.setStyle("-fx-text-inner-color: white");
				//txtPhoneNumber.setStyle("-fx-prompt-text-fill: white");
				dpBirthday.setStyle("-fx-text-inner-color: white");
			}
		});
	}

	@FXML
	public void btnSignUpPressed(ActionEvent event) throws ClassNotFoundException, SQLException {
		signUp();
	}

	@FXML
	public void btnCancelPressed(ActionEvent event) {
		openLogin();
	}

	private void signUp() throws ClassNotFoundException, SQLException {
		if (checkRegister()) {
			addUser();
		}
	}

	public boolean checkRegister() throws SQLException, ClassNotFoundException {
		String password = new String(txtPass.getText());
		String phoneNumber = txtPhoneNumber.getText();
		boolean hasError = false;
		if (!AccountHelper.validateEmail(txtEmail.getText().toLowerCase())) {
			txtEmail.setText("Email invalid form");
			hasError = true;
		}
		if (UserController.getInstance().findUserByEmail(txtEmail.getText()) != null) {
			hasError = true;
			txtEmail.setText("Email is already exist");
		}
		if (password.length() < 8) {
			lblPass.setText("Password must at leat 8 character");
		}
		if (!AccountHelper.validatePhoneNumber(phoneNumber)) {
			txtPhoneNumber.setText("Phone number only have . - and space character");
			hasError = true;
		}
		return !hasError;
	}

	private void addUser() {
		try {
			User user = new User();
			String date = dpBirthday.getValue().toString();
			String password = new String(txtPass.getText());
			String gender = txtGender.getText();
			user.setEmail(txtEmail.getText().toLowerCase());
			user.setPassword(AccountHelper.MD5encrypt(password));
			user.setName(txtName.getText());
			user.setPhoneNumber(txtPhoneNumber.getText());
			user.setBirthDay(AccountHelper.getDate(date));
			user.setGender(gender);
			if (UserController.getInstance().register(user)) {
				AfterRegister1.email = txtEmail.getText();
				openNextWindow1();
			} else {
				System.out.println("?");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private void openLogin() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) txtEmail.getScene().getWindow();
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void openNextWindow1() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Next1.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) txtEmail.getScene().getWindow();
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
