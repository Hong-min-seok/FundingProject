package com.kosa.funding.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OrderFinishedController implements Initializable {
	@FXML
	private ImageView congrtImage;

	@FXML
	private Button gotowishlist;

	@FXML
	private Button gotoorderlist;

	private String congtimagepath = "./images/dance.png";

	private String member_id;

	public void setMemberid(String data) {
		this.member_id = data;
	}

	@FXML
	private void gotoWishlistPage(ActionEvent event) {
		try {
			// 위시리스트페이지로 화면전환

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Frame.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);

			FrameController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.wishlist();

			Stage stage = (Stage) gotowishlist.getScene().getWindow();
			stage.setScene(scene);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void gotoOrderlistPage(ActionEvent event) {
		try {
			// 주문현황페이지로 화면전환
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Frame.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			
			FrameController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.chart();

			Stage stage = (Stage)gotowishlist.getScene().getWindow();
			stage.setScene(scene);		

			
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(congtimagepath.toString());
			BufferedInputStream bis = new BufferedInputStream(fis);
			congrtImage.setImage(new Image(bis, 200, 200, false, false));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
