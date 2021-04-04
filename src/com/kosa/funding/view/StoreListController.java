package com.kosa.funding.view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kosa.funding.model.ProductDAO;
import com.kosa.funding.model.ProductVO;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class StoreListController implements Initializable {
	
	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;
	@FXML
	private ImageView imageView3;
	@FXML
	private ImageView imageView4;
	@FXML
	private ImageView imageView5;
	@FXML
	private ImageView imageView6;
	@FXML
	private ScrollPane scrollPane_content;
	@FXML
	private StackPane sp;
	@FXML
	private Label imageLabel1;
	@FXML
	private Label imageLabel2;
	@FXML
	private Label imageLabel3;
	@FXML
	private Label imageLabel4;
	@FXML
	private Label imageLabel5;
	@FXML
	private Label imageLabel6;
	
	private String member_id;
	
	public void setMemberid(String data) {
		this.member_id = data;
	}

	int page_num = 1;
	ArrayList<ProductVO> list = new ArrayList<ProductVO>();
	ArrayList<ImageView> list_img_view = new ArrayList<ImageView>();
	ArrayList<Label> list_img_label = new ArrayList<Label>();
	FileInputStream fis;

	public void init() {
		
		list_img_view.add(imageView1);
		list_img_view.add(imageView2);
		list_img_view.add(imageView3);
		list_img_view.add(imageView4);
		list_img_view.add(imageView5);
		list_img_view.add(imageView6);

		list_img_label.add(imageLabel1);
		list_img_label.add(imageLabel2);
		list_img_label.add(imageLabel3);
		list_img_label.add(imageLabel4);
		list_img_label.add(imageLabel5);
		list_img_label.add(imageLabel6);
		ProductDAO dao = new ProductDAO();
		list = dao.list();
		print_prod_list();

		imageView1.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {

				int prod_num = (page_num * 6 - 6);
				show_detail(prod_num);

			}
		});

		imageView2.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 6 - 5);
				show_detail(prod_num);
			}
		});

		imageView3.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 6 - 4);
				show_detail(prod_num);
			}
		});

		imageView4.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 6 - 3);
				show_detail(prod_num);
			}
		});

		imageView5.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 6 - 2);
				show_detail(prod_num);
			}
		});

		imageView6.setOnMousePressed(new EventHandler() {
			@Override
			public void handle(Event evnet) {
				int prod_num = (page_num * 6 - 1);
				show_detail(prod_num);

			}
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	};

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
		if (page_num * 6 < list.size()) {
			System.out.println("다음 상품 목록 보기");
			page_num += 1;
			print_prod_list();

		}
	}

	private void show_detail(int prod_num) {
		System.out.println(prod_num + "번째 상품을 클릭하셨습니다");
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("StoreProductOverview.fxml"));
			AnchorPane me = (AnchorPane) loader.load();
			StoreProductOverviewController spc = loader.getController();
			
			/*
			 * AnchorPane me = (AnchorPane) FXMLLoader
			 * .load(getClass().getResource("StoreProductOverview.fxml"));
			 */
			ImageView prod_img = (ImageView) me.lookup("#productImage");
			fis = new FileInputStream("./images/" + list.get(prod_num).getImagePath().toString());
			BufferedInputStream bis = new BufferedInputStream(fis);
			prod_img.setImage(new Image(bis, 250, 250, false, false));

			Label pname = (Label) me.lookup("#productName");
			pname.setText(list.get(prod_num).getName());
			Label pprice = (Label) me.lookup("#productPrice");
			pprice.setText(Integer.toString(list.get(prod_num).getPrice()));
			Label pdesc = (Label) me.lookup("#productDescription");
			pdesc.setText(list.get(prod_num).getDescription());
			
			spc.setMemberid(member_id);
			spc.setProductCode(list.get(prod_num).getCode());
			
			StackPane root = sp;
			root.getChildren().add(me);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void print_prod_list() {
		try {
			for (int i = page_num * 6 - 6; i < page_num * 6; i++) {
				if (i >= list.size()) {
					list_img_view.get(i % 6).setImage(null);
					list_img_label.get(i % 6).setText(null);
				} else {
					System.out.println(list.get(i).getCode());
					System.out.println(list.get(i).getName());
					System.out.println(list.get(i).getPrice());
					System.out.println(list.get(i).getImagePath());
					System.out.println(list.get(i).getDescription());

					fis = new FileInputStream("./images/" + list.get(i).getImagePath().toString());
					BufferedInputStream bis = new BufferedInputStream(fis);
					list_img_view.get(i % 6).setImage(new Image(bis, 190, 190, false, false));
					list_img_label.get(i % 6).setText(list.get(i).getName() + " (" + list.get(i).getPrice() + "원)");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
