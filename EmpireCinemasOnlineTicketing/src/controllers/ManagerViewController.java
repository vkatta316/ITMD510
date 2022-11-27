package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Moviemodel;
import models.RequestModel;

public class ManagerViewController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent parent;
	 @FXML
	 private VBox Display;
    @FXML
    private Button SignoutButton;

    @FXML
    void SignoutButtonOnclick(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			List<RequestModel> ls = new ArrayList<>(Dbconnect.GetAllRequest());
			for(int i=0;i<ls.size();i++)
			{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../views/RequestPannel.fxml"));
			HBox box = fxmlLoader.load();
			RequestPannelController dpc = fxmlLoader.getController();
			dpc.SetData(ls.get(i));
			//box.addEventHandler(null, null);
			Display.getChildren().add(box);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}