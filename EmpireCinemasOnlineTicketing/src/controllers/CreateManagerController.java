package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.UserModel;

public class CreateManagerController {
	private boolean CheckEmail(String Email) throws SQLException
	{
		ResultSet rs = Dbconnect.QueryForResult("select * from empire_cinemas where Email = '"+Email+"'");
		int size = 0;
        while (rs.next()) {
            size++;
        }
        Dbconnect.conn.close();
        if(size >= 1)
        return true;
		return false;
	}
	@FXML
	private TextField EmailField;
	@FXML
	private TextField PassField;
	@FXML
	private TextField LnameField;
	@FXML
	private TextField FnameField;
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent parent;
	public void CreateAccountButtonOnclick(ActionEvent e) throws SQLException
	{
		String Email = EmailField.getText();
		if(CheckEmail(Email))//check mail uniqueness
		{	Alert a = new Alert(AlertType.NONE,"Email already in use",ButtonType.CLOSE);
			a.show();
		}
		else
		{	
			//signup - create account;
			String query=String.format("INSERT INTO empire_cinemas VALUES ('%s', '%s', '2', '%s', '%s','0')",Email,UserModel.myHashFunc(PassField.getText()),FnameField.getText(),LnameField.getText()); 
			Dbconnect.RunQuery(query);
			System.out.println("created");
		}
	}
	public void GoBackButtonOnclick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/AdminView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
