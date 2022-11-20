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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.UserModel;
public class SignupController {
	private boolean CheckEmail(String Email) throws SQLException
	{
		ResultSet rs = Dbconnect.QueryForResult("select * from EMPIRE_CINEMAS where Email = '"+Email+"'");
		int size = 0;
        while (rs.next()) {
            size++;
        }
        Dbconnect.conn.close();
        if(size >= 1)
        return true;
		return false;
	}
	private Stage stage;
	private Scene scene;
	private Parent parent;
	@FXML
	private TextField EmailField;
	@FXML
	private PasswordField PassField;
	@FXML
	private PasswordField RepeatPassField;
	@FXML
	private TextField FirstNameField;
	@FXML
	private TextField LastNameField;
	public void LoginButtonOnclick(ActionEvent e) throws IOException
	{	
		Parent root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public void CreateAccountButtonOnclick(ActionEvent e) throws SQLException {
		String Email = EmailField.getText();
		if(CheckEmail(Email))//check mail uniqueness
		{	Alert a = new Alert(AlertType.NONE,"Email already in use",ButtonType.CLOSE);
			a.show();
		}
		else if(RepeatPassField.getText().compareTo(PassField.getText())!=0) // check if password and repeated password are same
		{	Alert a = new Alert(AlertType.NONE,"Passwords do not match",ButtonType.CLOSE);
			a.show();
		}
		else
		{	
			//signup - create account;
			String query=String.format("INSERT INTO EMPIRE_CINEMAS VALUES ('%s', '%s', '1', '%s', '%s','0')",Email,UserModel.myHashFunc(PassField.getText()),FirstNameField.getText(),LastNameField.getText()); 
			Dbconnect.RunQuery(query);
			System.out.println("created");
		}
	}
}
