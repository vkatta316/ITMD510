package controllers;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Moviemodel;

public class MoviePannelController {
	@FXML
    private HBox Hbox;
	@FXML
    private ImageView movieimg;

    @FXML
    private Label mprice;

    @FXML
    private Label mtitle;
    void SetData(Moviemodel mv) {
		mtitle.setText(mv.gettitle());
		mprice.setText("$ " + mv.getprice());
		InputStream stream;
		try {
			stream = new FileInputStream(mv.getimgsrc());
			Image movieimage = new Image(stream);
			movieimg.setImage(movieimage);
			movieimg.setFitHeight(100);
			movieimg.setFitWidth(75);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    void BookTickets(MouseEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	Moviemodel.titleview = mtitle.getText();
    	Moviemodel.Ticketprice = Integer.parseInt(mprice.getText().substring(2));
		fxmlLoader.setLocation(getClass().getResource("../views/BookTicketsView.fxml"));
		DialogPane Dp = fxmlLoader.load();
		BookTicketsController btc = fxmlLoader.getController();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setDialogPane(Dp);
		dialog.setTitle("Book tickets");
		Optional<ButtonType> clickButton = dialog.showAndWait();
    }
}
