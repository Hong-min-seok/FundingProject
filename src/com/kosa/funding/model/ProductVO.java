package com.kosa.funding.model;

public class ProductVO {
	private int code;
	private String name;
	private int price;
	private String image_path;
	private String description;
	
	public ProductVO() {
	}

	public ProductVO(int code, String name, int price, String image_path) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.image_path = image_path;
	}

	public ProductVO(int code, String name, int price, String image_path, String description) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.image_path = image_path;
		this.description = description;
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
