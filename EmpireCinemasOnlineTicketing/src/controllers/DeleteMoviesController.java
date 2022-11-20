package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Moviemodel;

public class DeleteMoviesController implements Initializable {
	@FXML
	private ScrollPane MovieDisplay;
	@FXML
	private VBox display;
	private Stage stage;
	private Scene scene;
	private Parent parent;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			List<Moviemodel> ls = new ArrayList<>(Dbconnect.GetAllMovies());
			for(int i=0;i<ls.size();i++)
			{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../views/MoviePannelDelete.fxml"));
			HBox box = fxmlLoader.load();
			DeletePannelController dpc = fxmlLoader.getController();
			dpc.SetData(ls.get(i));
			display.getChildren().add(box);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void GobackOnclick(ActionEvent e) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("../views/AdminView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
