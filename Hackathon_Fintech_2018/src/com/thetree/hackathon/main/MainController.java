/**
 * Tran Tuan Anh
 * Date: Oct 13, 2018
 * Project: Hackathon_Fintech_2018
 */
package com.thetree.hackathon.main;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import com.thetree.hackathon.user.AccountHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.text.Text;

/**
 * @author gadfl
 *
 */
public class MainController implements Initializable{
	
	public static String email;
	
	private String tag = "#ExtraFee";
	private int amount =0;
	
	@FXML
	JFXTextField txtAmount;
	@FXML
	Text lblTag;
	@FXML
	TreeTableColumn<TimeLine, String> timeColumn;
	@FXML
	TreeTableColumn<TimeLine, String> tagColumn;
	@FXML
	TreeTableColumn<TimeLine, String> amountColumn;
	@FXML
	TreeTableView<TimeLine> treeTable;
	
	@FXML
	public void btnExtraFeeTag(ActionEvent event) {
		tag="#ExtraFee";
		lblTag.setText(tag);
	}
	@FXML
	public void btnLivingCostTag(ActionEvent event) {
		tag="#LivingCost";
		lblTag.setText(tag);
	}
	@FXML
	public void btnFoodsTag(ActionEvent event) {
		tag="#Foods";
		lblTag.setText(tag);
	}
	@FXML
	public void btnAddOne(ActionEvent event) {
		if(amount > 0) {
			amount-=1000;
			txtAmount.setText(String.valueOf(amount));
		}
	}
	@FXML
	public void btnSubOne(ActionEvent event) {
		amount+=1000;
		txtAmount.setText(String.valueOf(amount));
	}
	@FXML
	public void btnAddFive(ActionEvent event) {
		amount+=5000;
		txtAmount.setText(String.valueOf(amount));
	}
	@FXML
	public void btnAddTen(ActionEvent event) {
		amount+=10000;
		txtAmount.setText(String.valueOf(amount));
	}
	@FXML
	public void btnAddTwenty(ActionEvent event) {
		amount+=20000;
		txtAmount.setText(String.valueOf(amount));
	}
	@FXML
	public void btnSave(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(AccountHelper.VALID_PHONE_NUMBER_REGEX.matcher(txtAmount.getText()).find()) {
			amount = Integer.parseInt(txtAmount.getText());
		}
		LocalDateTime curDate = LocalDateTime.now();
		String date = AccountHelper.getDate(curDate.toLocalDate().toString());
		String time = curDate.getHour()+":"+curDate.getMinute()+":"+curDate.getMinute();
		TimeLine timeLine = new TimeLine();
		timeLine.setEmail(email);
		timeLine.setDate(date);
		timeLine.setTime(time);
		timeLine.setCost(amount);
		timeLine.setTag(tag);
		timeLine.addTimeLine();
	}
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtAmount.setStyle("-fx-text-inner-color: white");
		lblTag.setText(tag);
		timeColumn = new TreeTableColumn<>("Time");
		timeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Time"));
		tagColumn = new TreeTableColumn<>("Tag");
		tagColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Tag"));
		amountColumn = new TreeTableColumn<>("Amount");
		amountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Amount"));
		treeTable = new TreeTableView<>();
		treeTable.getColumns().add(timeColumn);
		treeTable.getColumns().add(tagColumn);
		treeTable.getColumns().add(amountColumn);
		try {
			loadData();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadData() throws ClassNotFoundException, SQLException {
		TimeLine timeLine = new TimeLine();
		timeLine.setEmail(email);
		LocalDateTime curDate = LocalDateTime.now();
		String date = AccountHelper.getDate(curDate.toLocalDate().toString());
		timeLine.setDate(date);
		ArrayList<TimeLine> timeLineList = timeLine.getTimeLine();
		TreeItem<TimeLine> root = new TreeItem<>(new TimeLine("Time", 0, "Tag"));
		for (TimeLine time : timeLineList) {
		    TreeItem<TimeLine> item = new TreeItem<>(time);
		    root.getChildren().addAll(item);
		}
		treeTable.setRoot(root);
	}
	
	private void reload() {
		
	}

}
