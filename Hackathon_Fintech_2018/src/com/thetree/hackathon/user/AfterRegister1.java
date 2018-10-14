/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.user;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

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
public class AfterRegister1 implements Initializable{

	public static String email;
	
	@FXML
	JFXTextField txtIncome;
	@FXML
	JFXDatePicker dpIncomeDate;
	@FXML
	Text lblStatus;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtIncome.setStyle("-fx-text-inner-color: white");
    	//txtEmail.setStyle("-fx-prompt-text-fill: white");
    	dpIncomeDate.setStyle("-fx-text-inner-color: white");
    	//txtPass.setStyle("-fx-prompt-text-fill: white");
	}
	
	@FXML
	public void btnNextPressed(ActionEvent event) {
		getIncome();
	}
	
	private void getIncome() {
		String incomeText = txtIncome.getText();
		if(AccountHelper.VALID_PHONE_NUMBER_REGEX.matcher(incomeText).find()) {
			int income = Integer.parseInt(incomeText);
			LocalDate iDate = dpIncomeDate.getValue();
			if(iDate!=null) {
				String incomeDate = AccountHelper.getDate(iDate.toString());
				AfterRegister2.email = email;
				AfterRegister2.income = income;
				AfterRegister2.incomeDate = incomeDate;
				if(income != 0) {
					afterRegister2();
				} else {
					afterRegister3();
				}
			}
		}else {
			lblStatus.setText("In valid income");
		}
	}
	
	private void afterRegister2() {
		try {
        	Parent root = FXMLLoader.load(getClass().getResource("next2.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = (Stage)txtIncome.getScene().getWindow();
        	stage.setScene(scene);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	private void afterRegister3() {
		try {
        	Parent root = FXMLLoader.load(getClass().getResource("next3.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = (Stage)txtIncome.getScene().getWindow();
        	stage.setScene(scene);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

}
