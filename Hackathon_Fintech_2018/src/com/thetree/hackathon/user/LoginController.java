/**
 * Tran Tuan Anh
 * Date: Oct 13, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.thetree.hackathon.constant.Constants;
import com.thetree.hackathon.main.MainController;

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
public class LoginController implements Initializable{

	@FXML
	JFXTextField txtEmail;
	@FXML
	JFXPasswordField txtPass;
	@FXML
	Text txtStatus;
	
	@FXML
	public void btnLoginPressed(ActionEvent event) {
		login();
	}
	
	@FXML
	public void btnSignUpPressed(ActionEvent event) {
		signUp();
	}
	
	private void login(){
	        String u = txtEmail.getText().toLowerCase();
	        String p = new String(txtPass.getText());
	        String pass = AccountHelper.MD5encrypt(p);
	        boolean check = true;
	        if(check&u.isEmpty()){
	            txtStatus.setText("Error: email must have");
	       }else
	        
	        
	        if(check&p.isEmpty()){
	            txtStatus.setText("Error: password must have");
	        }else
	        
	        if(check){
	            try {
	                switch (UserController.getInstance().checkLogin(u, pass)){
	                    case Constants.LOGIN_SUCCESS:
	                        try {
	                        	MainController.email = u;
	                        	Parent root = FXMLLoader.load(getClass().getResource("/com/thetree/hackathon/main/Main.fxml"));
	                        	Scene scene = new Scene(root);
	                        	Stage stage = new Stage();
	                        	stage.setScene(scene);
	                        	stage.show();
	                        	((Stage)txtEmail.getScene().getWindow()).close();
	                        }catch(Exception e){
	                        	e.printStackTrace();
	                        }
	                    case Constants.LOGIN_WRONG_PASSWORD:
	                        txtStatus.setText("Wrong Password");
	                    default:
	                        txtStatus.setText("Email is not exist");
	                }
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            } catch (ClassNotFoundException ex) {
	               ex.printStackTrace();
	            }
	        }
	    }

	private void signUp() {
		try {
        	Parent root = FXMLLoader.load(getClass().getResource("RegisterForm.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = (Stage)txtEmail.getScene().getWindow();
        	stage.setScene(scene);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 Platform.runLater(new Runnable() {
	            @Override public void run() {
	            	txtEmail.setStyle("-fx-text-inner-color: white");
	            	//txtEmail.setStyle("-fx-prompt-text-fill: white");
	            	txtPass.setStyle("-fx-text-inner-color: white");
	            	//txtPass.setStyle("-fx-prompt-text-fill: white");
	            }
	        });
	}
}
