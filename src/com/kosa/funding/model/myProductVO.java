package com.kosa.funding.model;

public class myProductVO {
	private int wish_code;
	private int code;
	private String name;
	private int price;
	private String image_path;
	private String description;
	
	public myProductVO() {
	}

	public myProductVO(int wish_code,  int code, String name, int price, String image_path) {
		this.wish_code = wish_code;
		this.code = code;
		this.name = name;
		this.price = price;
		this.image_path = image_path;
	}

	public myProductVO(int wish_code, int code, String name, int price, String image_path, String description) {
		this.wish_code = wish_code;
		this.code = code;
		this.name = name;
		this.price = price;
		this.image_path = image_path;
		this.description = description;
	}
	
	public int getWishCode() {
		return wish_code;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getImagePath() {
		return image_path;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
