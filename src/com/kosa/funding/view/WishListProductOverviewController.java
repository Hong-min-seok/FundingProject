package com.kosa.funding.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kosa.funding.model.AddFundingjoinDAO;
import com.kosa.funding.model.TrioClass;
import com.kosa.funding.model.fundingDAO;
import com.kosa.funding.model.fundingVO;
import com.kosa.funding.model.myProductDAO;
import com.kosa.funding.model.myProductVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class WishListProductOverviewController implements Initializable {

	@FXML
	private Button back_list;

	@FXML
	private AnchorPane prod_detail;

	@FXML
	private AnchorPane funding_money_info;

	@FXML
	private Button fundingBtn;
	
	@FXML
	private Button orderBtn;

	@FXML
	private Button PopupSumit;

	@FXML
	private Label productFunding;

	@FXML
	private TextField PopupPointFeild;

	@FXML
	private ListView<HBox> funding_info;

	private Popup popup;

	myProductVO productvo;

	static int wish_code;
	static String member_id;
	static String target_id;
	static int prod_code;
	static int point = 0;

	int sum = 0;
	private static int result = -1;

	public void setMemberid(String data) {
		this.member_id = data;
	}
	
	public void setTargetid(String data) {
		this.target_id = data;
	}

	public void setProdCode(int code) {
		System.out.println("��ǰ �ڵ� ����");
		System.out.println(code);
		this.prod_code = code;
	}

	public void setWishCode(int code) {
		System.out.println("���ø���Ʈ �ڵ� ����");
		System.out.println(code);
		this.wish_code = code;
	}

	public void setProductvo(myProductVO data) {
		this.productvo = data;
	}
	
	public void userCheck() {
		if (this.member_id.equals(target_id)) {
			fundingBtn.setVisible(false);
		} else {
			orderBtn.setVisible(false);
		}
	}

	ArrayList<myProductVO> list = new ArrayList<myProductVO>();
	ArrayList<fundingVO> funding_list = new ArrayList<fundingVO>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("wishlistproductoverview �ʱ�ȭ��");

	}

	@FXML
	public void handleBtnBackAction(ActionEvent event) {
		StackPane root = (StackPane) prod_detail.getParent();
		root.getChildren().remove(prod_detail);
	}

	@FXML
	public void handleBtnFundingAction(ActionEvent event) {

		System.out.println("�ݵ��ϱ�");
		this.popup = new Popup();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("WishlistpopUpLayout.fxml"));
			Button submit = (Button) parent.lookup("#PopupSumit");
			TextField tf = (TextField) parent.lookup("#PopupPointFeild");
			Text lblMessage = (Text) parent.lookup("#PopupMessage");

			submit.setOnAction(vnt -> {
				lblMessage.setText("����Ʈ�� �Է��ϼ���.");
				point = Integer.parseInt(tf.getText());
				AddFundingjoinDAO afdao = new AddFundingjoinDAO();
				result = afdao.add_fundingjoin(point, wish_code, member_id.toString());
				if (result == 1) {
					popup.hide();
					addFunding();
				} else {
					lblMessage.setText("������ �������� �ʽ��ϴ�. �Է°��� Ȯ���ϼ���.");
				}
			});
			popup.getContent().add(parent);
			popup.setAutoHide(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		popup.show(fundingBtn.getScene().getWindow());
	}

	@FXML
	public void handleBtnOrderAction(ActionEvent event) {
		// �ֹ��ϱ� ��ư Ŭ���� �̺�Ʈ �߰�
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("OrderOverview.fxml"));
			Parent me = (Parent) loader.load();

			OrderOverviewController oc = loader.getController();
			oc.setMemberid(member_id);
			oc.setProductInfo(productvo);
			oc.setWishCode(wish_code);
			oc.init();

			StackPane root = (StackPane) prod_detail.getParent();
			root.getChildren().add(me);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFunding() {
		System.out.println("addfunding");

		System.out.println(funding_info.getItems().size());
		if (funding_info.getItems().size() > 0) {
			for (int i = funding_info.getItems().size() - 1; i >= 0; i--) {
				funding_info.getItems().remove(i); // ListView�� ��� ����
			}
		}

		ArrayList<myProductVO> list = new ArrayList<myProductVO>();
		ArrayList<fundingVO> funding_list = new ArrayList<fundingVO>();
		myProductDAO dao = new myProductDAO();
		fundingDAO dao_fund = new fundingDAO();
		TrioClass tc = new TrioClass();

		list = dao.list(member_id);
		tc = dao_fund.list(wish_code);
		funding_list = tc.getFundingVO();

		for (int j = 0; j < funding_list.size(); j++) {

			try {

				HBox me1 = (HBox) FXMLLoader.load(getClass().getResource("wishlistitem.fxml"));
				Label friend_id = (Label) me1.lookup("#ID");
				friend_id.setText(funding_list.get(j).getMember_ID());
				Label friend_money = (Label) me1.lookup("#Money");
				friend_money.setText(funding_list.get(j).getFund_Money());
				funding_info.getItems().add(me1);
				System.out.println(funding_list.get(j).getMember_ID() + " " + funding_list.get(j).getFund_Money());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		productFunding.setText("�ݵ����� �ݾ� : " + tc.getSum());
	}

}
