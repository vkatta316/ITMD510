package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import models.Moviemodel;
import models.UserModel;

public class BookTicketsController implements Initializable{
	
	@FXML
    private DialogPane OuterBox;
	@FXML
    private Button BookTicketsButton;

    @FXML
    private Button CancelButton;

    @FXML
    private Text Cost;

    @FXML
    private Button MinusButton;

    @FXML
    private Text NumTickets;

    @FXML
    private Button PlusButton;

    @FXML
    void BookTicketsButtonOnclick(ActionEvent event) throws SQLException {
    	int cost = Integer.parseInt(Cost.getText().substring(2));
    	if(cost>Integer.parseInt(UserModel.Balance))
    	{
    		Alert a = new Alert(AlertType.NONE,"Not enough funds",ButtonType.CLOSE);
			a.show();
    	}
    	else
    	{	int newb = Integer.parseInt(UserModel.Balance)-cost;
    		UserModel.Balance = String.valueOf(newb);
    		Dbconnect.RunQuery("UPDATE empire_cinemas SET Balance = '"+newb+"' WHERE Email = '"+UserModel.Email+"'");
    		Alert a = new Alert(AlertType.NONE,"Booking done!",ButtonType.CLOSE);
			a.show();
    	}
    }

    @FXML
    void CancelButtonOnclick(ActionEvent event) {

    }

    @FXML
    void MinusButtonOnclick(ActionEvent event) {
    	System.out.println("clicked");
    	int x = Integer.parseInt(NumTickets.getText());
    	if(x>0)
    	{
    		x--;
    		NumTickets.setText(String.valueOf(x));
    		Cost.setText("$ "+String.valueOf(Moviemodel.Ticketprice*x));
    	}
    }

    @FXML
    void PlusButtonOnclick(ActionEvent event) {
    	System.out.println("clicked");
    	int x = Integer.parseInt(NumTickets.getText());
    	x++;
    	NumTickets.setText(String.valueOf(x));
		Cost.setText("$ "+String.valueOf(Moviemodel.Ticketprice*x));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MinusButton.getStyleClass().add("icon-minus");
		MinusButton.setPickOnBounds(true);
		PlusButton.getStyleClass().add("icon-button");
		PlusButton.setPickOnBounds(true);
		
	}

}
