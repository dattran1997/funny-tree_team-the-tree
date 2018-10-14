/**
 * Tran Tuan Anh
 * Date: Oct 14, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author gadfl
 *
 */
public class Test extends Application {

	public static void main(String [] args) {
		Application.launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage defaultStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/thetree/hackathon/user/LoginForm.fxml"));
		Scene scene = new Scene(root);
		defaultStage.setScene(scene);
		defaultStage.show();
	}
	
}
