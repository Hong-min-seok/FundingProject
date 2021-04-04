package com.kosa.funding.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import com.kosa.funding.model.MemberDAO;
import com.kosa.funding.model.MemberVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoinController implements Initializable {

	@FXML
	private TextField JoinIdField;

	@FXML
	private TextField JoinPasswordField;

	@FXML
	private TextField JoinNameField;

	@FXML
	private TextField JoinBirthFieldYYYY;

	@FXML
	private TextField JoinBirthFieldMM;

	@FXML
	private TextField JoinBirthFieldDD;

	@FXML
	private TextField JoinAddressField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void JoinJoinAction(ActionEvent event) {
		try {

			// memberVO에 데이터 set
			MemberVO member = new MemberVO();

			member.setId(JoinIdField.getText());
			member.setPassword(JoinPasswordField.getText());
			member.setName(JoinNameField.getText());
			member.setBirthday(
					JoinBirthFieldYYYY.getText() + '/' + JoinBirthFieldMM.getText() + '/' + JoinBirthFieldDD.getText());
			member.setAddress(JoinAddressField.getText());

			// Dao의 Join함수 호출
			MemberDAO memberDao = new MemberDAO();
			memberDao.Join(member);

			Parent login = FXMLLoader.load(getClass().getResource("LoginLayout.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) JoinIdField.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void JoinIdChekcAction() {

	}

}
