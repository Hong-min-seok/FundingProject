package com.kosa.funding.view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kosa.funding.model.AddWishListDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;


public class StoreProductOverviewController implements Initializable{

	@FXML
	private Button back_store;
	
	@FXML
	private AnchorPane prod_detail;
	
	@FXML
	private Button add_wishlist;
	

	@FXML
	private TextField productCode;
	
	@FXML
	private Label PopupMessage;

	@FXML
	private Button PopupSumit;

	private Popup popup;

	private static int product_code = 0;
	
	private String member_id;
	
	public void setMemberid(String data) {
		this.member_id = data;
	}
	

	public void setProductCode(int code) {
		System.out.println(code);
		this.product_code = code;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML public void handleBtnBackAction(ActionEvent event) {
		StackPane root = (StackPane) prod_detail.getParent();
		root.getChildren().remove(prod_detail);
	}
	
	@FXML
	public void handleBtnAddAction(ActionEvent event) {
		Popup pu = new Popup();

		try {

			AddWishListDAO adao = new AddWishListDAO();
			int result = adao.add_wishlist(member_id.toString(), product_code);

			Parent parent = FXMLLoader.load(getClass().getResource("StorepopUpLayout.fxml"));
			Label lblMessage = (Label) parent.lookup("#PopupMessage");

			if (result == 1) {
				lblMessage.setText("위시리스트에 추가되었습니다.");
			}
			else
			{
				lblMessage.setText("이미 위시리스트에 존재합니다.");
			}
			Button submit = (Button) parent.lookup("#PopupSumit");
			submit.setOnAction(vnt -> pu.hide());

			pu.getContent().add(parent);
			pu.setAutoHide(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pu.show(add_wishlist.getScene().getWindow());

	}

}
