package com.kh.practice.point.model.vo;

public class Circle {
	/*
	- radius : int
	+ Circle() : 기본생성자
	+ Circle(x:int, y:int, radius:int) : 매개변수 생성자
	+ getter/setter()
	+ toString() : String : 필드에 담긴 데이터를 반환하는 메소드
	*/
	
	private int radius;
	
	public Circle() {
		// TODO Auto-generated constructor stub
	}

	public Circle(int radius) {
		super();
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}
	
}
