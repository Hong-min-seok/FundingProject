package com.kosa.funding.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemController {
	
	@FXML
	private Label name;
	
	@FXML
	private ImageView profileimg;
	
	
	public void setProfile(String name, String imgpath) {
		this.name.setText(name);
	}
}
