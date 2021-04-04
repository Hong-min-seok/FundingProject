package com.kosa.funding.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kosa.funding.model.AddFundingjoinDAO;
import com.kosa.funding.model.MemberDAO;
import com.kosa.funding.model.MemberVO;
import com.kosa.funding.model.OrderDAO;
import com.kosa.funding.model.TrioClass;
import com.kosa.funding.model.fundingDAO;
import com.kosa.funding.model.myProductVO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class OrderOverviewController implements Initializable {
	@FXML
	private Label productName;

	@FXML
	private Label productPrice;

	@FXML
	private Label totalFundingMoney;

	@FXML
	private Label point;

	@FXML
	private Label OrderPrice;

	@FXML
	private ImageView productImage;

	@FXML
	private TextField usingPoint;

	@FXML
	private Button confirmusingpoint;

	@FXML
	private Button confirmorder;
	
	private Popup popup;

	private int productcode;

	private String productname;

	private int productprice;

	private String productimagepath;

	private int totalfundingmoney;

	private int orderprice;

	private String member_id;

	private int wish_code;

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}

	public void setMemberid(String member_id) {
		this.member_id = member_id;
	}

	public void setProductcode(int data) {
		this.productcode = data;
	}

	public void setWishCode(int code) {
		this.wish_code = code;
		fundingDAO dao_fund = new fundingDAO();
		TrioClass tc = new TrioClass();
		tc = dao_fund.list(code);
		this.totalfundingmoney = Integer.parseInt(tc.getSum());
		System.out.println(this.totalfundingmoney);
	}

	public void setProductInfo(myProductVO data) {
		this.productcode = data.getCode();
		this.productname = data.getName();
		this.productprice = data.getPrice();
		this.productimagepath = data.getImagePath();
	}

	@FXML
	private void UsePoint(ActionEvent event) {
		int up = Integer.parseInt(usingPoint.getText()); // 입력한 돈
		int mypoint = Integer.parseInt(point.getText()); // 내 포인트

		orderprice = productprice - totalfundingmoney;
		
		if (up <= mypoint) {
			if (up > orderprice) {
				usingPoint.setText(Integer.toString(orderprice));
				OrderPrice.setText("0");
				
				try {
					this.popup = new Popup();
					Parent parent = FXMLLoader.load(getClass().getResource("OrderpopUpLayout.fxml"));
					Label lblMessage = (Label) parent.lookup("#PopupMessage");
					Button submit = (Button) parent.lookup("#PopupSumit");
					submit.setOnAction(vnt -> {popup.hide();});
					
					lblMessage.setText("펀딩 금액이 상품 가격을 초과했습니다.");
					popup.getContent().add(parent);
					popup.setAutoHide(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				popup.show(confirmusingpoint.getScene().getWindow());
				
				
			} else {
				OrderPrice.setText(Integer.toString(productprice - totalfundingmoney - up));
			}

		} else {
			System.out.println("내 보유 포인트가 부족합니다.");
			usingPoint.setText("0");
			OrderPrice.setText(Integer.toString(productprice - totalfundingmoney));
			
			try {
				this.popup = new Popup();
				Parent parent = FXMLLoader.load(getClass().getResource("OrderpopUpLayout.fxml"));
				Label lblMessage = (Label) parent.lookup("#PopupMessage");
				Button submit = (Button) parent.lookup("#PopupSumit");
				submit.setOnAction(vnt -> {popup.hide();});
				
				lblMessage.setText("보유 포인트가 부족합니다.");
				popup.getContent().add(parent);
				popup.setAutoHide(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			popup.show(confirmusingpoint.getScene().getWindow());
		}
	}

	@FXML
	private void ConfirmOrder(ActionEvent event) {
		System.out.println("주문하기");

		MemberDAO memberdao = new MemberDAO();

		int spend_point = Integer.parseInt(usingPoint.getText());

		memberdao.substractFundingmonyPoint(member_id, productcode, totalfundingmoney, spend_point);

		OrderDAO orderdao = new OrderDAO();

		orderdao.insertOrder(member_id, productcode, wish_code);

		try {
			// 주문완료페이지로 화면전환
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("OrderFinish.fxml"));
			Parent login = (Parent) loader.load();
			OrderFinishedController oc = loader.getController();
			oc.setMemberid(member_id);

			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) confirmorder.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() {

		productPrice.setText(Integer.toString(productprice));
		productName.setText(productname);

		FileInputStream fis;
		try {
			fis = new FileInputStream("./images/" + productimagepath.toString());
			BufferedInputStream bis = new BufferedInputStream(fis);
			productImage.setImage(new Image(bis, 200, 200, false, false));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		totalFundingMoney.setText("-" + Integer.toString(totalfundingmoney));
		OrderPrice.setText(Integer.toString(productprice - totalfundingmoney));

		MemberDAO memberdao = new MemberDAO();
		MemberVO member = new MemberVO();
		member = memberdao.getMemberPointFundingMoney(member_id);

		point.setText(Integer.toString(member.getPoint()));

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
