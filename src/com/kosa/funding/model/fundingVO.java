

package com.kosa.funding.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class fundingVO {
	private SimpleStringProperty fund_money;
	private SimpleStringProperty member_id;

	
	public fundingVO() {
		this.fund_money = new SimpleStringProperty();
		this.member_id = new SimpleStringProperty();
	}

	public fundingVO(int fund_money, String member_id) {
		this.fund_money = new SimpleStringProperty(Integer.toString(fund_money));
		this.member_id = new SimpleStringProperty(member_id);
	}


	public String getFund_Money() {
		return fund_money.get();
	}

	public String getMember_ID() {
		return member_id.get();
	}
	
	public void setFundMoney(int fund_money) {
		this.fund_money.set(Integer.toString(fund_money));
	}

	public void setMemberID(String member_id) {
		this.member_id.set(member_id);
	}
	
    public StringProperty fund_moneyProperty() {
        return fund_money;
    }
    public StringProperty member_idProperty() {
        return member_id;
    }

}