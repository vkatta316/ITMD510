package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Moviemodel;

public class MoviePannelController {
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
