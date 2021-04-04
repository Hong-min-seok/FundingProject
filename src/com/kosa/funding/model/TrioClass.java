package com.kosa.funding.model;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;


public class TrioClass {

	private ArrayList<fundingVO> funding_list;
	private SimpleStringProperty sum;
	private SimpleStringProperty code;
	
	public TrioClass() {
		this.sum = new SimpleStringProperty();
		this.code = new SimpleStringProperty();
	}
	
	public TrioClass(int code, ArrayList<fundingVO> al, int sum)
	{
		this.code = new SimpleStringProperty(Integer.toString(code));
		this.funding_list = al;
		this.sum = new SimpleStringProperty(Integer.toString(sum));
	}
	
	public String getSum()
	{
		return sum.get();
	}
	public ArrayList<fundingVO> getFundingVO()
	{
		return funding_list;
	}
	public String getCode()
	{
		return code.get();
	}
	
	public void setSum(int sum)
	{
		this.sum.set(Integer.toString(sum));
	}
	public void setCode(int code)
	{
		this.code.set(Integer.toString(code));
	}
	public void setFundingVO(ArrayList<fundingVO> al)
	{
		this.funding_list = al;
	}
}
