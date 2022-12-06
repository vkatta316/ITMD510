package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.Moviemodel;

public class DeletePannelController {
	@FXML
    private HBox Box;

    @FXML
    private Button DeleteButton;

    @FXML
    private ImageView image;

    @FXML
    private Label Price;

    @FXML
    private Label Title;
    
    @FXML
    private Moviemodel mv;

    public void SetData(Moviemodel mv)
    {
         DeleteButton.setShape(null);
         Circle cir2 = new Circle(250,250,120);
         //cir2.setFill(new ImagePattern(new Image("C:\\Users\\sivam\\OneDrive\\Desktop\\close.png",false)));
         DeleteButton.setShape(cir2);
         InputStream stream;
 		try {
 			stream = new FileInputStream("close.png");

 			Image image = new Image(stream);
 			ImageView view = new ImageView(image);
 			
 			view.setFitHeight(35);
 			view.setFitWidth(35);
 			view.setPreserveRatio(true);
 			DeleteButton.setGraphic(view);

 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
         Title.setText(mv.gettitle());
         Price.setText("$ "+mv.getprice());
         
         InputStream strm;
 		try {
 			strm = new FileInputStream(mv.getimgsrc());
 			Image movieimage = new Image(strm);
 			image.setImage(movieimage);
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
        // Image movieimage = new Image(mv.getimgsrc());
         //image.setImage(movieimage);
         //DeleteButton.setStyle("-fx-background-image: url('C:\\Users\\sivam\\OneDrive\\Desktop\\close.png')");
    }
    public void DeleteButtonOnlcick(ActionEvent e)
    {
    	
    	try {
			Dbconnect.RunQuery(String.format("DELETE FROM movies_list WHERE Movie='%s'",Title.getText()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	VBox vb = (VBox) Box.getParent();
        vb.getChildren().remove(Box);
		
    }
}
