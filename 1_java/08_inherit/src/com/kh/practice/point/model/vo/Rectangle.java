package com.kh.practice.point.model.vo;

public class Rectangle extends Point {
	/*
	- width : int
	- height : int
	+ Rectangle() : 기본생성자
	+ Rectangle(x:int, y:int, width:int, height:int) : 매개변수 생성자
	+ getter/setter()
	+ toString() : String : 필드에 담긴 데이터를 반환하는 메소드
	*/
	
	private int width;
	private int height;
	
	public Rectangle() {
	}

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}
	
	
	
	
}
