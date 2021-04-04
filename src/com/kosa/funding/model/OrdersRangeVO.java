package com.kosa.funding.model;

public class OrdersRangeVO {
	private int count;
	private String month;
	
	public OrdersRangeVO() {}
	
	public OrdersRangeVO(int c, String m)
	{
		this.count = c;
		this.month = m;
	}
	
	
	public int getCount() {
		return count;
	}
	public String getMonth() {
		return month;
	}
	

}
