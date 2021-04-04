package com.kosa.funding.view;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kosa.funding.model.MemberAgeDAO;
import com.kosa.funding.model.MoneyRangeDAO;
import com.kosa.funding.model.MoneyRangeVO;
import com.kosa.funding.model.OrdersRangeDAO;
import com.kosa.funding.model.OrdersRangeVO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class chartController implements Initializable {
	@FXML
	private PieChart pieChart;

	@FXML
	private BarChart barChart;

	@FXML
	private AreaChart areaChart;

	MoneyRangeVO MRvo = new MoneyRangeVO();
	ArrayList<MoneyRangeVO> MRlist = new ArrayList<MoneyRangeVO>();

	OrdersRangeVO ORvo = new OrdersRangeVO();
	ArrayList<OrdersRangeVO> ORlist = new ArrayList<OrdersRangeVO>();

	static String member_id;
	
	public void setMemberid(String data) {
		this.member_id = data;
	}

	public void init() {
		
		MoneyRangeDAO MRdao = new MoneyRangeDAO();
		OrdersRangeDAO ORdao = new OrdersRangeDAO();
		System.out.println("init에서 멤버아이디 설정 : " + member_id);
		MRlist = MRdao.list(member_id);
		ORlist = ORdao.order_range(member_id);
		
		//자신의 월별 주문현황
		make_barchart();
		make_pichart();
		make_areachart();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void make_pichart() {
		int[] age_range = new int[8];
		MemberAgeDAO MAdao = new MemberAgeDAO();
		int age;
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		String id;
		
		System.out.println("MRlist size " + MRlist.size() );
		for(int i = 0; i<MRlist.size(); i++)
		{
			id = MRlist.get(i).getMemberId();
			age = MAdao.birth_age(id);
			age_range[(age/10 >= 7)? 7:age/10] += 1;
		}
		for(int i =0; i<8; i++)
		{
			String age_info = (i<1)? "10대 이하 ("+age_range[i]+")":i*10+"대 ("+age_range[i]+")";
			age_info = (i>= 7)? "70대 이상 ("+age_range[i]+")":age_info;
			list.add(new PieChart.Data(age_info, age_range[i]));
			System.out.println(age_info + " " + age_range[i]);
		}
		pieChart.setData(list);

	}

	public void make_barchart() {

		XYChart.Series series1 = new XYChart.Series<String, Integer>();
		series1.setName("주문량");
		
		for (int i = 0; i < ORlist.size(); i++) {
			series1.getData().add(new XYChart.Data<String, Integer>(ORlist.get(i).getMonth(), ORlist.get(i).getCount()));
		}
		barChart.getData().add(series1);
	}
	
	
	public void make_areachart() {
		int[] money_range = new int[6];
		MemberAgeDAO MAdao = new MemberAgeDAO();
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		int money;
		int idx;
		String money_info; 
		
		for(int i = 0; i<MRlist.size(); i++)
		{
			money = MRlist.get(i).getFundMoney();
			System.out.println(money);
			if(money/50000 >= 5)
			{
				idx = 5;
			}
			else
			{
				idx = money/50000;
			}
			money_range[idx] += 1;
		}
		
		
		
		XYChart.Series series1 = new XYChart.Series<String, Integer>();
		series1.setName("금액별 인원");
		
		for (int i = 0; i < 6; i++) {
	         money_info = (i*5 == 25)? "25만원~":((i==0)?0:i)*5+"만원~"+((i==0)?1:i+1)*5+"만원";
	         System.out.println(money_info);
	         System.out.println(money_range[i]);
	         series1.getData().add(new XYChart.Data<String, Integer>(money_info, money_range[i]));
	         System.out.println(i + " " + money_range[i]);
	      }
		areaChart.getData().add(series1);
		

	}



}
