package com.kh.chap1_poly.model.vo;

public class Child2 extends Parent {
	// �ʵ�
	private char ch;
	
	// ������
	public Child2() {}; // �⺻������
	public Child2(int num1, int num2, char ch) { // �Ű����� �ִ� ������
		super(num1, num2);
		this.ch = ch;
	}
	
	// �޼ҵ�
	
	public char getCh() {
		return ch;
	}
	
	public void setCh(char ch) {
		this.ch = ch;
	}
	
	public void printChild2() {
		System.out.println("Child2 ��ü�� printChild2() �޼ҵ� ȣ��");
	}




}