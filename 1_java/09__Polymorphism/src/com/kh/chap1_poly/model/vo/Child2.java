package com.kh.chap1_poly.model.vo;

public class Child2 extends Parent {
	// 필드
	private char ch;
	
	// 생성자
	public Child2() {}; // 기본생성자
	public Child2(int num1, int num2, char ch) { // 매개변수 있는 생성자
		super(num1, num2);
		this.ch = ch;
	}
	
	// 메소드
	
	public char getCh() {
		return ch;
	}
	
	public void setCh(char ch) {
		this.ch = ch;
	}
	
	public void printChild2() {
		System.out.println("Child2 객체의 printChild2() 메소드 호출");
	}




}
