package com.kosa.funding.model;

public class MoneyRangeVO {
	
	private int code;
	private String member_id;
	private int fund_money;
	
	public MoneyRangeVO() {}
	
	public MoneyRangeVO(int c, String m_id, int fm)
	{
		this.code = c;
		this.member_id = m_id;
		this.fund_money = fm;
	}
	
	
	public int getCode() {
		return code;
	}
	public String getMemberId() {
		return member_id;
	}
	public int getFundMoney() {
		return fund_money;
	}
	
	public void setCode(int c)
	{
		this.code = c;
	}
	
	public void setMemberID(String m_id)
	{
		this.member_id = m_id;
	}
	
	public void setFundMoney(int fm)
	{
		this.fund_money = fm;
	}
	
}
