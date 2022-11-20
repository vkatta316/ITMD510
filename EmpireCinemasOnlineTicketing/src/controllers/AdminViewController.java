package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminViewController {
	private Stage stage;
	private Scene scene;
	private Parent parent;
	public void CreateMovieButtonOnclick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/CreateMoviesView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public void CreateManagerAccButtonOnclick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/CreateManagerAccView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public void DeleteMoviesButtonOnclick(ActionEvent e) throws IOException
	{	
		Parent root = FXMLLoader.load(getClass().getResource("../views/DeleteMoviesView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public void SignoutButtonOnclick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
