package com.kh.chap1_poly.model.vo;

public class Parent {
	
	//필드
	private int num1;
	private int num2;
	
	
	//생성자
	public Parent() {} //기본생성자
	
	public Parent(int num1, int num2) { //매개변수 있는 생성자
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}
	
	public int getNum2() {
		return num2;
	}
	
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public void print() {
		System.out.println("Parent 객체의 print() 호출됨.");
	}

}
