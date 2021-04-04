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
		
		//member�� ���̵�, ��й�ȣ �� set
		member.setId(LoginidField.getText());
		member.setPassword(LoginpasswordField.getText());

		// Dao�� login�Լ� ȣ��
		MemberDAO memberDao = new MemberDAO();
		int loginResult = memberDao.login(member);
		
		// 1 = �α��� ����, 0 = �������� �ʴ� ���̵�, 2 = Ʋ�� ��й�ȣ
		System.out.println(loginResult);
		
		if(loginResult == 1) {
			System.out.println("�α��� ����");
			//�α��� ������ -> ģ������������� ��ȯ
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
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
		}else {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
	}
	
	@FXML
	public void JoinPageAction (ActionEvent event){
		try {
			// ȸ�������������� ȭ����ȯ
				 Parent join = FXMLLoader.load(getClass().getResource("JoinLayout.fxml"));
			     Scene scene = new Scene(join);
			     Stage primaryStage = (Stage)LoginJoinbtn.getScene().getWindow(); // ���� ������ ��������
			     primaryStage.setScene(scene);
			     System.out.println("LoginJoinController : ȸ���������� ȭ�� ��ȯ");    
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		
	}
}
