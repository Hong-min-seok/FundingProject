package com.kosa.funding.view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kosa.funding.model.MemberDAO;
import com.kosa.funding.model.MemberVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	// Login
	
	@FXML private AnchorPane ap;
	
	@FXML private TextField LoginidField;
	
	@FXML private TextField LoginpasswordField;
	
	@FXML private Button LoginLoginbtn; 
	
	@FXML private Button LoginJoinbtn; 
	
	
	int LoginResult;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void LoginAction(){
		MemberVO member = new MemberVO();
		
		//member에 아이디, 비밀번호 값 set
		member.setId(LoginidField.getText());
		member.setPassword(LoginpasswordField.getText());

		// Dao의 login함수 호출
		MemberDAO memberDao = new MemberDAO();
		int loginResult = memberDao.login(member);
		
		// 1 = 로그인 성공, 0 = 존재하지 않는 아이디, 2 = 틀린 비밀번호
		System.out.println(loginResult);
		
		if(loginResult == 1) {
			System.out.println("로그인 성공");
			//로그인 성공시 -> 친구목록페이지로 전환
			try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("Frame.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				
				FrameController fc = loader.getController();
				fc.setMemberid(LoginidField.getText());
				fc.init();

				Stage stage = (Stage)LoginJoinbtn.getScene().getWindow();
				stage.setScene(scene);

			    
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(loginResult == 0){
			System.out.println("존재하지 않는 아이디입니다.");
		}else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}
	
	@FXML
	public void JoinPageAction (ActionEvent event){
		try {
			// 회원가입페이지로 화면전환
				 Parent join = FXMLLoader.load(getClass().getResource("JoinLayout.fxml"));
			     Scene scene = new Scene(join);
			     Stage primaryStage = (Stage)LoginJoinbtn.getScene().getWindow(); // 현재 윈도우 가져오기
			     primaryStage.setScene(scene);
			     System.out.println("LoginJoinController : 회원가입으로 화면 전환");    
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		
	}
}
