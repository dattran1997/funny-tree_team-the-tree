/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author gadfl
 *
 */
public class AfterRegister2 implements Initializable{
	
	public static String email;
	public static int income;
	public static String incomeDate;
	
	@FXML
	JFXTextField txtFood;
	@FXML
	JFXTextField txtLivingCost;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtFood.setStyle("-fx-text-inner-color: white");
    	//txtEmail.setStyle("-fx-prompt-text-fill: white");
    	txtLivingCost.setStyle("-fx-text-inner-color: white");
    	//txtPass.setStyle("-fx-prompt-text-fill: white");
	}
	
	@FXML
	public void btnNextPressed(ActionEvent event) throws ClassNotFoundException, SQLException {
		getSomeThing();
	}
	//I can not thing anything!!! :(
	public void getSomeThing() throws ClassNotFoundException, SQLException {
		String foodText = txtFood.getText();
		String livingCostText = txtLivingCost.getText();
		if(AccountHelper.VALID_PHONE_NUMBER_REGEX.matcher(foodText).find() && AccountHelper.VALID_PHONE_NUMBER_REGEX.matcher(livingCostText).find()) {
			int food = Integer.parseInt(foodText);
			int livingCost = Integer.parseInt(livingCostText);
			
			int essentialCost = food + livingCost/30;
			
			Income i = new Income();
			i.setEmail(email);
			i.setIncome(income);
			i.setIncomeDate(incomeDate);
			i.setEssentialCost(essentialCost);
			i.addIncome();
			
			openLogin();
		}
	}
	
	private void openLogin() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) txtFood.getScene().getWindow();
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
