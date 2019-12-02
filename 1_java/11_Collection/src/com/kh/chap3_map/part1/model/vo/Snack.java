package com.kh.chap3_map.part1.model.vo;

public class Snack {
	private String name;
	private int price;
	private int cal;
	
	public Snack() {
		// TODO Auto-generated constructor stub
	}

	public Snack(String name, int price, int cal) {
		super();
		this.name = name;
		this.price = price;
		this.cal = cal;
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

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	@Override
	public String toString() {
		return "Snack [name=" + name + ", price=" + price + ", cal=" + cal + "]";
	}
	
	

}
