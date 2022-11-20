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

public class CreateMovieController {
	@FXML
	private TextField MovieNameField;
	@FXML
	private TextArea DescriptionArea;
	@FXML
	private TextField TicketPriceField;
	private Stage stage;
	private Scene scene;
	private Parent parent;
	private boolean CheckEmail(String Movie) throws SQLException
	{
		ResultSet rs = Dbconnect.QueryForResult("select * from movies_list where movie = '"+Movie+"'");
		int size = 0;
        while (rs.next()) {
            size++;
        }
        Dbconnect.conn.close();
        if(size >= 1)
        return true;
		return false;
	}
	public void CreateButtonOnclick(ActionEvent e) throws SQLException
	{	System.out.print("created movie");
		String movie = MovieNameField.getText();
		if(CheckEmail(movie))//check mail uniqueness
		{	Alert a = new Alert(AlertType.NONE,"Movie Already exists",ButtonType.CLOSE);
			a.show();
		}
		else
		{	
			//signup - create account;
			String query=String.format("INSERT INTO movies_list VALUES ('%s', '%s','%s')",movie,DescriptionArea.getText(),TicketPriceField.getText()); 
			Dbconnect.RunQuery(query);
			System.out.println("created");
		}
	}
	public void GoBackButtonOnlcick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/AdminView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
