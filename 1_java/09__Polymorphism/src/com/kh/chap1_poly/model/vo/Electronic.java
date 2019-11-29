package com.kh.chap1_poly.model.vo;

public class Electronic {
	
	private String brand;
	private String name;
	private int price;
	
	
	// ctrl + space ---> enter
	public Electronic() {
		
	}
	 
	// alt + shift + s ---> o
	public Electronic(String brand, String name, int price) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
	}
	
	// alt + shift + s ---> r (space로 체크 후 엔터)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() { //toString 메소드 자체가 Object 클래스의 메소드이므로  오버라이딩한 것임
		return brand + ", " + name + ", " + price;
	}
	
	
}
