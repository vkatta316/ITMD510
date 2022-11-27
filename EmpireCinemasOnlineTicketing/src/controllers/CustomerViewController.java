package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Moviemodel;
import models.UserModel;

public class CustomerViewController implements Initializable{
	boolean IsNum(String s)
	{	if(s.length()==0)
		return false;
		for(int i=0;i<s.length();i++)
		{	int val = s.charAt(i);
			if(val<48 || val>57)
				return false;
		}
		return true;
	}
	private Stage stage;
	private Scene scene;
	private Parent parent;
    @FXML
    private Label Balance;

    @FXML
    private Label Fname;

    @FXML
    private TextField InputField;

    @FXML
    private Label Lname;

    @FXML
    private HBox NowDisplay1;

    @FXML
    private ImageView ProfilePic;

    @FXML
    private Button RequestButton;

    @FXML
    private HBox UpcomingDisplay;
    
    @FXML
    private Button SignoutButton;
    @FXML
    private Button RefreshButton;
    @FXML
    void RefreshButtonOnclick(ActionEvent event) {
    	Balance.setText("Balance: $ "+UserModel.Balance);
    }
    @FXML
    void RequestButtonOnclick(ActionEvent event) throws SQLException {
    	String req = InputField.getText();
    	if(IsNum(req))
    	{	Dbconnect.RunQuery(String.format("INSERT INTO RequestTable VALUES ('%s','%s')",UserModel.Email,req));
    		Alert a = new Alert(AlertType.NONE,"Request made",ButtonType.CLOSE);
			a.show();
    	}
    	else
    	{
    		InputField.setText("");
    		Alert a = new Alert(AlertType.NONE,"Enter a proper value",ButtonType.CLOSE);
			a.show();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fname.setText(UserModel.FirstName);
		Lname.setText(UserModel.LastName);
		Balance.setText(Balance.getText()+UserModel.Balance);
			
			addMovieImages("Upcoming1.jpg");
			
			addMovieImages("Upcoming2.jpg");
			
			addMovieImages("Upcoming3.jpg");

		try {
			List<Moviemodel> ls = new ArrayList<>(Dbconnect.GetAllMovies());
			for(int i=0;i<ls.size();i++)
			{
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../views/MoviePannel.fxml"));
			HBox box = fxmlLoader.load();
			MoviePannelController dpc = fxmlLoader.getController();
			dpc.SetData(ls.get(i));
			NowDisplay1.getChildren().add(box);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void SignoutButtonOnclclick(ActionEvent e) throws IOException {
		System.out.println("signout");
		Parent root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
    }
	
	private void addMovieImages(String imagePath) {

		InputStream stream;
		try {
			stream = new FileInputStream(imagePath);

			Image image = new Image(stream);
			ImageView view = new ImageView();
			view.setImage(image);
			view.setFitHeight(100);
			view.setFitWidth(100);
			UpcomingDisplay.getChildren().add(view);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
