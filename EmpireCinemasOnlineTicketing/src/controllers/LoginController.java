package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserModel;
public class LoginController {
	@FXML
	private TextField EmailField;
	@FXML
	private PasswordField PassField;
	private Stage stage;
	private Scene scene;
	private Parent parent;
	public String CheckLogin(String username,String password) throws SQLException
	{	
		ResultSet rs = Dbconnect.QueryForResult("select * from EMPIRE_CINEMAS where Email = '"+username+"' AND password ='"+password+"'");		int size = 0;
		String privilige = "0";
        while (rs.next()) {
        	UserModel.FirstName = rs.getString("Fname");
    		UserModel.LastName = rs.getString("Lname");
    		UserModel.Balance = rs.getString("Balance");
    		privilige = rs.getString("Privilege");
            size++;
        }
        System.out.println(privilige);
        Dbconnect.conn.close();
        return privilige;
	}
	public void LoginButtonOnClick(ActionEvent e) throws SQLException, IOException
	{
		UserModel newuser = new UserModel(EmailField.getText(),PassField.getText());
		String priv = CheckLogin(newuser.Email,newuser.Password);
		if(priv.charAt(0) == '1')
		{
			Parent root = FXMLLoader.load(getClass().getResource("../views/CustomerView.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		else if(priv.charAt(0) == '2')
		{
			Parent root = FXMLLoader.load(getClass().getResource("../views/ManagerView.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		else if(priv.charAt(0) == '4')
		{
			Parent root = FXMLLoader.load(getClass().getResource("../views/AdminView.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		else 
		{	Alert a = new Alert(AlertType.NONE,"Invalid credentials",ButtonType.CLOSE);
			a.show();
		}
	}
	public void SignupButtonOnclick(ActionEvent e) throws IOException
	{	System.out.println("Clicked login");
		Parent root = FXMLLoader.load(getClass().getResource("../views/SignupPage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
