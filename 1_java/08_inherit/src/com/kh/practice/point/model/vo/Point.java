package com.kh.practice.point.model.vo;

public class Point {
	/*
	com.kh.practice.point.model.vo.Point
	- x : int
	- y : int
	+ Point() : 기본생성자
	+ Point(x:int, y:int) : 매개변수 생성자
	+ getter/setter() 
	+ toString() : String : 필드에 담긴 데이터를 반환하는 메소드
	*/
	private int x;
	private int y;
	
	public Point() {
		// TODO Auto-generated constructor stub
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
	
}
