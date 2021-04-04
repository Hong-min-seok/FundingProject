package com.kosa.funding.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kosa.funding.model.MemberDAO;
import com.kosa.funding.model.MemberVO;
import com.kosa.funding.model.TrioClass;
import com.kosa.funding.model.fundingDAO;
import com.kosa.funding.model.fundingVO;
import com.kosa.funding.model.myProductDAO;
import com.kosa.funding.model.myProductVO;

import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class WishListController implements Initializable {
	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;
	@FXML
	private ImageView imageView3;
	@FXML
	private ImageView imageView4;
	@FXML
	private ImageView my_profile;

	@FXML
	private Label imageLabel1;
	@FXML
	private Label imageLabel2;
	@FXML
	private Label imageLabel3;
	@FXML
	private Label imageLabel4;

	@FXML
	private Label Label_id;

	@FXML
	private Label Label_name;

	@FXML
	private Label Label_point;

	@FXML
	private Label Label_funding_money;

	@FXML
	private StackPane sp;

	@FXML
	private AnchorPane prod_detail;

	int page_num = 1;

	ArrayList<myProductVO> list = new ArrayList<myProductVO>();
	ArrayList<fundingVO> funding_list = new ArrayList<fundingVO>();
	ArrayList<TrioClass> funding_trio_list = new ArrayList<TrioClass>();
	ArrayList<ImageView> list_img_view = new ArrayList<ImageView>();
	ArrayList<Label> list_img_label = new ArrayList<Label>();
	FileInputStream fis;

	ObservableList<fundingVO> observableList;

	static private String member_id;
	static private String target_id;
	static private int wish_code;

	public void setMemberid(String data) {
		this.member_id = data;
	}

	public void setTargetid(String data) {
		this.target_id = data;
	}

	public void setWishcode(int data) {
		this.wish_code = data;
	}

	public void init() {
		list_img_view.add(imageView1);
		list_img_view.add(imageView2);
		list_img_view.add(imageView3);
		list_img_view.add(imageView4);

		list_img_label.add(imageLabel1);
		list_img_label.add(imageLabel2);
		list_img_label.add(imageLabel3);
		list_img_label.add(imageLabel4);

		print_prod_list();


		imageView1.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 4 - 4);
				System.out.println(prod_num);
				show_detail(prod_num);
			}
		});

		imageView2.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 4 - 3);
				System.out.println(prod_num);
				show_detail(prod_num);
			}
		});

		imageView3.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 4 - 2);
				System.out.println(prod_num);
				show_detail(prod_num);
			}
		});

		imageView4.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 4 - 1);
				System.out.println(prod_num);
				show_detail(prod_num);
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	};

	private void show_detail(int prod_num) {
		fundingDAO dao_fund = new fundingDAO();
		TrioClass tc = new TrioClass();

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("WishListProductOverview.fxml"));
			Parent me = (Parent) loader.load();

			tc = dao_fund.list(list.get(prod_num).getWishCode());
			funding_list = tc.getFundingVO();

			for (int j = 0; j < funding_list.size(); j++) {

				try {
					HBox me1 = (HBox) FXMLLoader.load(getClass().getResource("wishlistdetailitem.fxml"));
					Label friend_id = (Label) me1.lookup("#ID");
					friend_id.setText(funding_list.get(j).getMember_ID());
					Label friend_money = (Label) me1.lookup("#Money");
					friend_money.setText(funding_list.get(j).getFund_Money());
					ListView funding_info = (ListView) me.lookup("#funding_info");
					funding_info.getItems().add(me1);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			ImageView prod_img = (ImageView) me.lookup("#productImage");
			fis = new FileInputStream("./images/" + list.get(prod_num).getImagePath().toString());
			BufferedInputStream bis = new BufferedInputStream(fis);
			prod_img.setImage(new Image(bis, 250, 250, false, false));
			Label pname = (Label) me.lookup("#productName");
			pname.setText(list.get(prod_num).getName());
			Label pprice = (Label) me.lookup("#productPrice");
			pprice.setText("가격 : " + Integer.toString(list.get(prod_num).getPrice()));
			Label pdesc = (Label) me.lookup("#productDescription");
			pdesc.setText(list.get(prod_num).getDescription());

			tc = dao_fund.list(list.get(prod_num).getWishCode());

			Label pfund = (Label) me.lookup("#productFunding");
			//pfund.setText("펀딩받은 금액 : " + tc.getSum());

			WishListProductOverviewController pc = loader.getController();
			pc.setMemberid(this.member_id);
			pc.setTargetid(this.target_id);
			pc.userCheck();
			pc.setWishCode(list.get(prod_num).getWishCode());
			pc.setProdCode(list.get(prod_num).getCode());
			pc.setProductvo(list.get(prod_num));

			Button backbtn = (Button) me.lookup("#back_list");
			AnchorPane prodL = (AnchorPane) me.lookup("#prod_detail");
			backbtn.setOnAction(vnt -> {
				print_prod_list();
				StackPane root = sp;//(StackPane) backbtn.getScene().getRoot();
				root.getChildren().remove(prodL);
			});

			StackPane root = sp;
			root.getChildren().add(me);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void handleBtnPrevAction() {
		if (page_num > 1) {
			System.out.println("이전 상품 목록 보기");
			page_num -= 1;
			print_prod_list();
		}
	}

	@FXML
	public void handleBtnNextAction() throws FileNotFoundException {
		if (page_num * 4 < list.size()) {
			System.out.println("다음 상품 목록 보기");
			page_num += 1;
			print_prod_list();

		}
	}
	
	public void print_prod_list() {
		fundingDAO dao_fund = new fundingDAO();
		TrioClass tc = new TrioClass();
		myProductDAO dao = new myProductDAO();
		MemberDAO dao_mem = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo = dao_mem.profile_info(target_id);
		int mypoint = dao_mem.profile_info(member_id).getPoint();
		list = dao.list(target_id);

		BufferedInputStream bis;
		
		try {
			if (vo.getImage_path() != null) {
				fis = new FileInputStream("./images/" + vo.getImage_path().toString());
				bis = new BufferedInputStream(fis);
				my_profile.setImage(new Image(bis, 220, 220, false, false));
			}
			else
			{
				fis = new FileInputStream("./images/defaultprofile.png");
				bis = new BufferedInputStream(fis);
				my_profile.setImage(new Image(bis, 220, 220, false, false));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Label_point.setText("내 포인트 : " + mypoint);
		Label_funding_money.setText("총 펀딩포인트 : " + Integer.toString(vo.getTotal_funding_money()));
		Label_id.setText(this.target_id);
		Label_name.setText(vo.getName().toString() + "님의 위시리스트");

		try {
			for (int i = page_num * 4 - 4; i < page_num * 4; i++) {
				if (i >= list.size()) {
					list_img_view.get(i % 4).setImage(null);
					list_img_label.get(i % 4).setText(null);
				} else {
					fis = new FileInputStream("./images/" + list.get(i).getImagePath().toString());
					bis = new BufferedInputStream(fis);
					tc = dao_fund.list(list.get(i).getWishCode());
					list_img_view.get(i % 4).setImage(new Image(bis, 220, 220, false, false));
					list_img_label.get(i % 4).setText(list.get(i).getName() + " (" + list.get(i).getPrice() + "원)\r"
							+ "펀딩된 포인트 : " + tc.getSum() + "원");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
