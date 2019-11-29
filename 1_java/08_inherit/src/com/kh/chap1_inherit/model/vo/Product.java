package com.kh.chap1_inherit.model.vo;

public class Product {
	
	private String brand;
	private String name;
	private String pCode;
	private int price;
	
	public Product() {
		super();
		// 현재 클래스가 객체로 생성될 때 객체 내부에 부모의 기본적인 객체를 생성하라는 구문 
		// 원래 JVM이 자동으로 생성시켜주지만 이렇게 수동으로 추가하는 것도 가능함
		
	} // 기본생성자
	
	public Product(String brand, String name, String pCode, int price) {
		super(); // 이것도 JVM이 자동으로 생성시켜줌
		
		this.brand = brand;
		this.name = name;
		this.pCode = pCode;
		this.price = price;
		
	}
	
	// get/setter 빨리 만들기
	// alt + shift + s --> r
	
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
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String inform() {
		return brand + ", " + name + ", " + pCode + ", " + price;
	}
	
	// final 키워드 확인용 메소드
	public void print() {
		System.out.println("오버라이딩 해봐라.");
	}
	// public final void인 경우 Computer 클래스에서 해당 메소드 오버라이딩 불가능!
	

}
