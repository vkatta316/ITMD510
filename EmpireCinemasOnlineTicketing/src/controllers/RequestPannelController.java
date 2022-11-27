package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Moviemodel;
import models.RequestModel;

public class RequestPannelController {
	public static int StringToInt(String str)
	{
		return Integer.parseInt(str);
		
	}
	public static String IntToString(int num)
	{
		return String.valueOf(num);
	}
	@FXML
    private HBox Box;
    @FXML
    private Text Amount;

    @FXML
    private Button ApproveButton;

    @FXML
    private Button DeclineButton;

    @FXML
    private Text Email;

    @FXML
    void ApproveButtonOnclick(ActionEvent event) throws SQLException {
    	//convert Amount to number;
    	int add = StringToInt(Amount.getText().substring(2));
    	//Query the DB convert that to number
    	ResultSet rs = Dbconnect.QueryForResult("Select * from empire_cinemas where email = '"+Email.getText()+"'");
    	while(rs.next())
    	{	
    		String balance = rs.getString("Balance");
    		int newval = add+StringToInt(balance);
    		String NewBalance = IntToString(newval);
    		System.out.println("123");
    		Dbconnect.RunQuery("UPDATE empire_cinemas SET Balance = '"+NewBalance+"' WHERE Email = '"+Email.getText()+"'");
    		System.out.println("123");
    		Dbconnect.RunQuery(String.format("DELETE FROM RequestTable WHERE Email='%s' AND Amount='%s'",Email.getText(),Amount.getText().substring(2)));
    		System.out.println("123");
    	}
    	// add both
    	//convert to string
    	// remove,update;
    	VBox vb = (VBox) Box.getParent();
        vb.getChildren().remove(Box);
    }

    @FXML
    void DeclineButtonOnlclick(ActionEvent event) {
    	VBox vb = (VBox) Box.getParent();
        vb.getChildren().remove(Box);
    }
    void SetData(RequestModel rv)
    {
    	Email.setText(rv.GetEmail());
    	Amount.setText("$ "+rv.GetAmount());
    	
    }
}