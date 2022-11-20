package controllers;

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
    public void SetData(Moviemodel mv)
    {
         DeleteButton.setShape(null);
         Circle cir2 = new Circle(250,250,120);
         //cir2.setFill(new ImagePattern(new Image("C:\\Users\\sivam\\OneDrive\\Desktop\\close.png",false)));
         DeleteButton.setShape(cir2);
         Image img = new Image("C:\\Users\\sivam\\OneDrive\\Desktop\\close.png");
         ImageView view = new ImageView(img);
         view.setFitHeight(35);
         view.setPreserveRatio(true);
         DeleteButton.setGraphic(view);
         Title.setText(mv.gettitle());
         Price.setText("$ "+mv.getprice());
         Image movieimage = new Image(mv.getimgsrc());
         image.setImage(movieimage);
         //DeleteButton.setStyle("-fx-background-image: url('C:\\Users\\sivam\\OneDrive\\Desktop\\close.png')");
    }
    public void DeleteButtonOnlcick(ActionEvent e)
    {
    	VBox vb = (VBox) Box.getParent();
        vb.getChildren().remove(Box);
    }
}
