package com.kosa.funding.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.kosa.funding.model.MemberDAO;
import com.kosa.funding.model.MemberVO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FriendController implements Initializable {

	@FXML
	private ListView<HBox> myprofile;

	@FXML
	private ListView<HBox> bfriendlist;

	@FXML
	private ListView<HBox> friendlist;

	private MemberDAO memberdao = new MemberDAO();
	
	private String member_id;
	private String target_id;
	
	public void setMemberid(String data) {
		this.member_id = data;
	}
	
	public void setTargetid(String data) {
		this.target_id = data;
	}

	public void initList(String data) {
		try {
			System.out.println("여긴 initList입니다 들어온 아이디는 " + data);
			MemberVO myInfo = memberdao.getMyProfile(data);
			HBox me = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
			Label myname = (Label) me.lookup("#name");
			ImageView myimg = (ImageView) me.lookup("#image");
			myname.setText(myInfo.getName());

			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream("./images/" + myInfo.getImage_path().toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			bis = new BufferedInputStream(fis);
			myimg.setImage(new Image(bis, 150, 150, false, false));

			myprofile.setItems(FXCollections.observableArrayList(me));

			List<MemberVO> bfriendsInfo = memberdao.getBirthFriendList(data);

			for (MemberVO bean : bfriendsInfo) {
				HBox bfriend = (HBox) FXMLLoader.load(getClass().getResource("bitem.fxml"));
				Label name = (Label) bfriend.lookup("#name");
				Label tid = (Label) bfriend.lookup("#tid");
				Button btn = (Button) bfriend.lookup("#funding_button");
				btn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						System.out.println(tid.getText());
						try {

							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("WishList.fxml"));
							Parent root = (Parent) loader.load();
							
							WishListController fc = loader.getController();
							fc.setMemberid(member_id);
							fc.setTargetid(tid.getText());
							fc.init();

							BorderPane bp = (BorderPane) name.getScene().getRoot();
							bp.setCenter(root);
							//bp.setCenter(root);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
				ImageView bfimg = (ImageView) bfriend.lookup("#image");
				name.setText(bean.getName());
				tid.setText(bean.getId());

				try {
					fis = new FileInputStream("./images/" + bean.getImage_path().toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				bis = new BufferedInputStream(fis);
				bfimg.setImage(new Image(bis, 150, 150, false, false));
				

				bfriendlist.getItems().add(bfriend);
			}

			// 접속 계정자의 친구 목록 불러오기
			List<MemberVO> friendsInfo = memberdao.getFriendList(data);

			for (MemberVO bean : friendsInfo) {
				HBox friend = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
				Label name = (Label) friend.lookup("#name");
				ImageView fimg = (ImageView) friend.lookup("#image");
				name.setText(bean.getName());

				try {
					fis = new FileInputStream("./images/" + bean.getImage_path().toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				bis = new BufferedInputStream(fis);
				fimg.setImage(new Image(bis, 150, 150, false, false));

				friendlist.getItems().add(friend);
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
