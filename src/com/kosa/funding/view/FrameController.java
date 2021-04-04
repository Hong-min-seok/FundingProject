package com.kosa.funding.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FrameController implements Initializable {

	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;

	private String member_id;

	public void setMemberid(String data) {
		this.member_id = data;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void init() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FriendListFrame.fxml"));
			Parent root = (Parent) loader.load();
			
			FriendController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.initList(this.member_id);

			bp.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void wishlist() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("WishList.fxml"));
			Parent root = (Parent) loader.load();
			
			WishListController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.setTargetid(this.member_id);
			fc.init();

			bp.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chart() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Chart_Layout.fxml"));
			Parent root = (Parent) loader.load();
			
			chartController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.init();

			bp.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void FriendListClick(MouseEvent event) {
		init();
	}

	@FXML
	private void WishListClick(MouseEvent evnet) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("WishList.fxml"));
			Parent root = (Parent) loader.load();
			
			WishListController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.setTargetid(this.member_id);
			fc.init();

			bp.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void StoreClick(MouseEvent evnet) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("funding_store.fxml"));
			Parent root = (Parent) loader.load();
			
			StoreListController fc = loader.getController();
			fc.setMemberid(this.member_id);
			fc.init();

			bp.setCenter(root);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void GraphClick(MouseEvent evnet) {
		chart();
	}

	private void loadPage(String page) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(page + ".fxml"));
			Parent root = (Parent) loader.load();
			
			/*
			 * FriendController fc = loader.getController(); System.out.println("¿©±â : " +
			 * this.member_id); fc.setMemberid(this.member_id); fc.initList(this.member_id);
			 */

			bp.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
